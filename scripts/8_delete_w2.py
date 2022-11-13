import weaviate

w2 = weaviate.Client("http://localhost:8090")
w2.schema.delete_all()
print(f"Schema deleted at http://localhost:8090")    
