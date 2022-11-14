const weaviate = require("weaviate-client");

const client = weaviate.client({
  scheme: 'http',
  host: 'localhost:8090',
});

client.backup.restoreStatusGetter()
  .withBackend("filesystem")
  .withBackupId("my-very-first-backup")
  .do()
  .then(console.log)
  .catch(console.error)