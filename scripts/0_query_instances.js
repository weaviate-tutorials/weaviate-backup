const weaviate = require("weaviate-client");

const host_urls = ["localhost:8080", "localhost:8090"];

for (host_url of host_urls) {
    var client = weaviate.client({
        scheme: "http",
        host: host_url
    });

    client.schema
        .getter()
        .do()
        .then(res => {
            console.log("Schema from", host_url);
            console.log(res);
        })
        .catch(err => {
            console.error(err)
        });

    client.data
        .getter()
        .do()
        .then(res => {
            console.log("Objects from", host_url)
            console.log(res);
        })
        .catch(err => {
            console.error(err)
        });
}
