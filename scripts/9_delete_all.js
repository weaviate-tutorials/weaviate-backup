const weaviate = require('weaviate-client');

const host_urls = ["localhost:8080", "localhost:8090"];
const wClassNames = ["Book", "Author"];

for (host_url of host_urls) {
    var client = weaviate.client({
        scheme: "http",
        host: host_url
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
}