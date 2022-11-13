import weaviate

w2 = weaviate.Client("http://localhost:8090")

result = w2.backup.get_restore_status(
  backup_id='my-very-first-backup',
  backend='filesystem',
)

print(result)
