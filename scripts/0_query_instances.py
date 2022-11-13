import weaviate
import json


def query_client(url, name):
    client = weaviate.Client(url)
    schema = client.schema.get()

    print(f"Schema from {name} at {url}:")
    print(json.dumps(schema, indent=4))

    objs = client.data_object.get()
    print(f"Objects from {name} at {url}:")
    print(json.dumps(objs, indent=4))
    return True


query_client("http://localhost:8080", "W1")
query_client("http://localhost:8090", "W2")
