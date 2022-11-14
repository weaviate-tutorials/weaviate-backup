const weaviate = require('weaviate-client');

const wClassNames = ["Book", "Author"];

var client = weaviate.client({
    scheme: "http",
    host: "localhost:8090"
});

for (wClassName of wClassNames) {
    client.schema
        .classDeleter()
        .withClassName(wClassName)
        .do()
        .then(res => {
            console.log(res);
        })
        .catch(err => {
            console.error(err)
        });
}