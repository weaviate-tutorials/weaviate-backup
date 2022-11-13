import weaviate

w1 = weaviate.Client("http://localhost:8080")

# Create Author class
class_obj = {
    "class": "Author",
    "description": "Fantasy Writers",
    "properties": [
        {
            "name": "name",
            "dataType": ["string"],
            "description": "The author's name",
        },
        {
            "name": "website",
            "dataType": ["string"],
            "description": "Their website address",
        },
        {
            "name": "genres",
            "dataType": ["text[]"],
            "description": "Genres they write",
        },        
    ]
}
w1.schema.create_class(class_obj)

# Create Book class
class_obj = {
    "class": "Book",
    "description": "Popular books",
    "properties": [
        {
            "name": "title",
            "dataType": ["string"],
            "description": "Title of the book",
        },
        {
            "name": "written_by",
            "dataType": ["Author"],
            "description": "Author of the book",
        },        
    ]
}
w1.schema.create_class(class_obj)
