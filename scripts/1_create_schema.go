package main

import (
	"context"
	"fmt"

	"github.com/semi-technologies/weaviate-go-client/v4/weaviate"
	"github.com/semi-technologies/weaviate/entities/models"
	"github.com/semi-technologies/weaviate/entities/schema"
)

func main() {
	hostW1 := "localhost:8080"

	author := &models.Class{
		Class:       "Author",
		Description: "Fantasy Writers",
		Properties: []*models.Property{
			{
				Name:        "name",
				Description: "The author's name",
				DataType:    []string{string(schema.DataTypeString)},
			},
			{
				Name:        "website",
				Description: "Their website address",
				DataType:    []string{string(schema.DataTypeString)},
			},
			{
				Name:        "genres",
				Description: "Genres they write",
				DataType:    []string{string(schema.DataTypeTextArray)},
			},
		},
	}

	book := &models.Class{
		Class:       "Book",
		Description: "Popular books",
		Properties: []*models.Property{
			{
				Name:        "title",
				Description: "Title of the book",
				DataType:    []string{string(schema.DataTypeString)},
			},
			{
				Name:        "written_by",
				Description: "Author of the book",
				DataType:    []string{"Author"},
			},
		},
	}

	ctx := context.Background()
	client := weaviate.New(weaviate.Config{Scheme: "http", Host: hostW1})
	classCreator := client.Schema().ClassCreator()

	fmt.Println("\nCreating Schema - Author")
	errAuthor := classCreator.WithClass(author).Do(ctx)
	if errAuthor != nil {
		fmt.Println(errAuthor)
		return
	}
	fmt.Println("Created")

	fmt.Println("\nCreating Schema - Book")
	errBook := classCreator.WithClass(book).Do(ctx)
	if errBook != nil {
		fmt.Println(errBook)
		return
	}
	fmt.Println("Created")
}
