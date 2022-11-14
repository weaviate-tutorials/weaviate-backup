const weaviate = require("weaviate-client");

const client = weaviate.client({
    scheme: "http",
    host: "localhost:8080"
});

const data = { "authors": [{ "class": "Author", "id": "36ddd591-2dee-4e7e-a3cc-a00000003389", "properties": { "name": "Stephen King", "website": "http://www.stephenking.com", "genres": ["Horror", "Mystery", "Literature", "Fiction"] } }, { "class": "Author", "id": "36ddd591-2dee-4e7e-a3cc-a00059559145", "properties": { "name": "Adrienne Young", "website": "http://adrienneyoungbooks.com/", "genres": ["Fantasy", "Young Adult", "Fiction"] } }, { "class": "Author", "id": "36ddd591-2dee-4e7e-a3cc-a00014584166", "properties": { "name": "Kerri Maniscalco", "website": "https://www.kerrimaniscalco.com/", "genres": ["Young Adult", "Historical Fiction", "Mystery"] } }], "books": [{ "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000012", "properties": { "title": "The Shining", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00000003389" }] } }, { "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000021", "properties": { "title": "Spells for Forgetting", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00059559145" }] } }, { "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000022", "properties": { "title": "Fable", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00059559145" }] } }, { "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000023", "properties": { "title": "Namesake", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00059559145" }] } }, { "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000031", "properties": { "title": "Kingdom of the Wicked", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00014584166" }] } }, { "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000032", "properties": { "title": "Kingdom of the Cursed", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00014584166" }] } }, { "class": "Book", "id": "36ddd591-2dee-4e7e-a3cc-b00000000033", "properties": { "title": "Kingdom of the Feared", "written_by": [{ "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00014584166" }] } }] }

// Prepare a batcher
let batcher = client.batch.objectsBatcher();
let counter = 0;

data.authors.forEach(author => {
    // Construct an object with a class, id, properties and vector
    const obj = {
        class: "Author",
        id: author.id,
        properties: {
            name: author.name,
            website: author.website,
            genres: author.genres
        },
    }

    // add the object to the batch queue
    batcher = batcher.withObject(obj);

    // When the batch counter reaches 20, push the objects to Weaviate
    if (counter++ == 20) {
        // flush the batch queue
        batcher
            .do()
            .then(res => {
                console.log(res)
            })
            .catch(err => {
                console.error(err)
            });

        // restart the batch queue
        counter = 0;
        batcher = client.batch.objectsBatcher();
    }
});

// Flush the remaining objects
batcher
    .do()
    .then(res => {
        console.log(res)
    })
    .catch(err => {
        console.error(err)
    });

data.books.forEach(book => {
    // Construct an object with a class, id, properties and vector
    const obj = {
        class: "Book",
        id: book.id,
        properties: {
            name: book.title,
            written_by: book.written_by,
        },
    }

    // add the object to the batch queue
    batcher = batcher.withObject(obj);

    // When the batch counter reaches 20, push the objects to Weaviate
    if (counter++ == 20) {
        // flush the batch queue
        batcher
            .do()
            .then(res => {
                console.log(res)
            })
            .catch(err => {
                console.error(err)
            });

        // restart the batch queue
        counter = 0;
        batcher = client.batch.objectsBatcher();
    }
});

// Flush the remaining objects
batcher
    .do()
    .then(res => {
        console.log(res)
    })
    .catch(err => {
        console.error(err)
    });