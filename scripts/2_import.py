import weaviate
import json
import os

w1 = weaviate.Client("http://localhost:8080")

if os.path.exists("data.json"):
    datapath = "data.json"
elif os.path.exists("../data.json"):
    datapath = "../data.json"
else:
    print("Data file not found! Please run the script from within the repository")
    quit()

with open(datapath, "r") as f:
    data = json.load(f)

# Configure batching
w1.batch.configure(
    batch_size=100,
    timeout_retries=3,
    callback=weaviate.util.check_batch_result,
)

# Add 'Author' objects
for author in data['authors']:
    w1.batch.add_data_object(
        author['properties'], author['class'], author['id']
    )
w1.batch.flush()

# Add 'Book' objects
for book in data['books']:
    w1.batch.add_data_object(
        book['properties'], book['class'], book['id']
    )
w1.batch.flush()

# Query W1
objs = w1.data_object.get()['objects']
print(json.dumps(objs, indent=4))
