import weaviate

for client_url in ["http://localhost:8080", "http://localhost:8090"]:
    client = weaviate.Client(client_url)
    client.schema.delete_all()
    print(f"Schema deleted at {client_url}")    
