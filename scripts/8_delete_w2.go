package main

import (
	"context"
	"fmt"

	"github.com/semi-technologies/weaviate-go-client/v4/weaviate"
)

func main() {
	hostW2 := "localhost:8090"

	ctx := context.Background()
	client := weaviate.New(weaviate.Config{Scheme: "http", Host: hostW2})

	fmt.Println("\nDeleting all schemas")
	err := client.Schema().AllDeleter().Do(ctx)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println("Deleted")
}
