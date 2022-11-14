const weaviate = require("weaviate-client");

const client = weaviate.client({
  scheme: 'http',
  host: 'localhost:8090',
});

client.backup.restorer()
  .withBackend("filesystem")
  .withBackupId("my-very-first-backup")
  .withWaitForCompletion(false)
  .do()
  .then(console.log)
  .catch(console.error)