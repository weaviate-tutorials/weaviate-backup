#!/bin/bash
set -e

# Create schema
## Author
echo -e "\nCreating Schema - Author"

curl \
-X POST \
-H "Content-Type: application/json" \
-d '{
    "class": "Author",
    "description": "Fantasy Writers",
    "properties": [
        {
            "name": "name",
            "dataType": ["string"],
            "description": "Their name"
        },
        {
            "name": "website",
            "dataType": ["string"],
            "description": "Their website address"
        },
        {
            "name": "genres",
            "dataType": ["text[]"],
            "description": "Genres they write"
        }
    ]
}' \
http://localhost:8080/v1/schema

## Book
echo -e "\nCreating Schema - Book"

curl \
-X POST \
-H "Content-Type: application/json" \
-d '{
    "class": "Book",
    "description": "Popular books",
    "properties": [
        {
            "name": "title",
            "dataType": ["string"],
            "description": "Their name"
        },
        {
            "name": "written_by",
            "dataType": ["Author"],
            "description": "Cross ref to the writer"
        }
    ]
}' \
http://localhost:8080/v1/schema

