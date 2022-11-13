import weaviate
import json

w2 = weaviate.Client("http://localhost:8090")

result = w2.backup.restore(
    backup_id='my-very-first-backup',
    backend='filesystem',
)

print(json.dumps(result, indent=4))
