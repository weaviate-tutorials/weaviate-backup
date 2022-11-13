#!/bin/bash

# Delete Author and Book classes from Weaviate-2
curl -X DELETE http://localhost:8090/v1/schema/Author
curl -X DELETE http://localhost:8090/v1/schema/Book
