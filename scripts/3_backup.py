import weaviate
import json

w1 = weaviate.Client("http://localhost:8080")

result = w1.backup.create(
    backup_id='my-very-first-backup',
    backend='filesystem',
)

print(json.dumps(result, indent=4))
