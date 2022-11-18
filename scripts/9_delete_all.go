package main

import (
	"context"
	"fmt"

	"github.com/semi-technologies/weaviate-go-client/v4/weaviate"
)

func main() {
	hosts := map[string]string{
		"W1": "localhost:8080",
		"W2": "localhost:8090",
	}

	ctx := context.Background()
	for name, host := range hosts {
		client := weaviate.New(weaviate.Config{Scheme: "http", Host: host})

		fmt.Printf("\nDeleting all schemas in %s\n", name)
		err := client.Schema().AllDeleter().Do(ctx)
		if err != nil {
			fmt.Println(err)
		} else {
			fmt.Println("Deleted")
		}
	}
}
