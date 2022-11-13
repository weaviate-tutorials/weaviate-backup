import weaviate

w1 = weaviate.Client("http://localhost:8080")

result = w1.backup.get_create_status(
  backup_id='my-very-first-backup',
  backend='filesystem',
)

print(result)
