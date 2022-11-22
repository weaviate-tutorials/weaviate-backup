package main

import (
	"context"
	"encoding/json"
	"fmt"
	"os"

	"github.com/semi-technologies/weaviate-go-client/v4/weaviate"
	"github.com/semi-technologies/weaviate/entities/models"
)

func main() {
	hostW1 := "localhost:8080"
	dataPath := "data.json"

	ctx := context.Background()
	client := weaviate.New(weaviate.Config{Scheme: "http", Host: hostW1})

	var data Data
	contents, err := os.ReadFile(dataPath)
	if err != nil {
		fmt.Println(err)
		return
	}
	err = json.Unmarshal(contents, &data)
	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println("\nImporting Data - Author")
	responseAuthors, err := client.Batch().ObjectsBatcher().
		WithObjects(data.Authors...).
		Do(ctx)
	if err != nil {
		fmt.Println(err)
		return
	}
	asJsonAuthors, _ := json.MarshalIndent(responseAuthors, "", "  ")
	fmt.Printf("%s\n", asJsonAuthors)

	fmt.Println("\nImporting Data - Book")
	responseBooks, err := client.Batch().ObjectsBatcher().
		WithObjects(data.Books...).
		Do(ctx)
	if err != nil {
		fmt.Println(err)
		return
	}
	asJsonBooks, _ := json.MarshalIndent(responseBooks, "", "  ")
	fmt.Printf("%s\n", asJsonBooks)
}

type Data struct {
	Authors []*models.Object `json:"authors"`
	Books   []*models.Object `json:"books"`
}
