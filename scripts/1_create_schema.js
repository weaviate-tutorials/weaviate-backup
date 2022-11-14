const weaviate = require("weaviate-client");

const client = weaviate.client({
    scheme: "http",
    host: "localhost:8080"
});

// Create Author class
var authorObj = {
    "class": "Author",
    "description": "Fantasy Writers",
    "properties": [
        {
            "name": "name",
            "dataType": ["string"],
            "description": "The author's name",
        },
        {
            "name": "website",
            "dataType": ["string"],
            "description": "Their website address",
        },
        {
            "name": "genres",
            "dataType": ["text[]"],
            "description": "Genres they write",
        },
    ]
}   

// Create Book class
var bookObj = {
    "class": "Book",
    "description": "Popular books",
    "properties": [
        {
            "name": "title",
            "dataType": ["string"],
            "description": "Title of the book",
        },
        {
            "name": "written_by",
            "dataType": ["Author"],
            "description": "Author of the book",
        },
    ]
}

client
    .schema
    .classCreator()
    .withClass(authorObj)
    .do()
    .then(res => {
        console.log(res);
        client
        .schema
        .classCreator()
        .withClass(bookObj)
        .do()
        .then(res => {
            console.log(res)
        })
        .catch(err => {
            console.error(err)
        });          
    })
    .catch(err => {
        console.error(err)
    }); 
   