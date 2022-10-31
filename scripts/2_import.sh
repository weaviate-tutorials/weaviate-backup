#!/bin/bash

# Import sample data
## Authors
echo -e "\nImporting Data - Author"

curl \
-X POST \
-H "Content-Type: application/json" \
-d '{
  "objects": [{
    "class": "Author",
    "id": "36ddd591-2dee-4e7e-a3cc-a00000003389",
    "properties": {
        "name": "Stephen King",
        "website": "http://www.stephenking.com",
        "genres": ["Horror", "Mystery", "Literature", "Fiction"]
    }
  }, {
    "class": "Author",
    "id": "36ddd591-2dee-4e7e-a3cc-a00059559145",
    "properties": {
        "name": "Adrienne Young",
        "website": "http://adrienneyoungbooks.com/",
        "genres": ["Fantasy", "Young Adult", "Fiction"]
    }
  }, {
    "class": "Author",
    "id": "36ddd591-2dee-4e7e-a3cc-a00014584166",
    "properties": {
        "name": "Kerri Maniscalco",
        "website": "https://www.kerrimaniscalco.com/",
        "genres": ["Young Adult", "Historical Fiction", "Mystery"]
    }
  }]
}' \
http://localhost:8080/v1/batch/objects

## Books
echo -e "\nImporting Data - Book"

curl \
-X POST \
-H "Content-Type: application/json" \
-d '{
  "objects": [{
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000012",
    "properties": {
        "title": "The Shining",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00000003389"
        }]
    }
  }, {
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000021",
    "properties": {
        "title": "Spells for Forgetting",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00059559145"
        }]
    }
  }, {
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000022",
    "properties": {
        "title": "Fable",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00059559145"
        }]
    }
  }, {
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000023",
    "properties": {
        "title": "Namesake",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00059559145"
        }]
    }
  }, {
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000031",
    "properties": {
        "title": "Kingdom of the Wicked",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00014584166"
        }]
    }
  }, {
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000032",
    "properties": {
        "title": "Kingdom of the Cursed",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00014584166"
        }]
    }
  }, {
    "class": "Book",
    "id": "36ddd591-2dee-4e7e-a3cc-b00000000033",
    "properties": {
        "title": "Kingdom of the Feared",
        "written_by": [{
          "beacon": "weaviate://localhost/36ddd591-2dee-4e7e-a3cc-a00014584166"
        }]
    }
  }]
}' \
http://localhost:8080/v1/batch/objects
