package main

import (
	"context"
	"encoding/json"
	"fmt"

	"github.com/semi-technologies/weaviate-go-client/v4/weaviate"
	"github.com/semi-technologies/weaviate-go-client/v4/weaviate/backup"
)

func main() {
	hostW1 := "localhost:8080"

	ctx := context.Background()
	client := weaviate.New(weaviate.Config{Scheme: "http", Host: hostW1})

	fmt.Println("\nCreating Backup")
	createResponse, err := client.Backup().Creator().
		WithBackupID("my-very-first-backup").
		WithBackend(backup.BACKEND_FILESYSTEM).
		Do(ctx)
	if err != nil {
		fmt.Println(err)
		return
	}
	asJson, _ := json.MarshalIndent(createResponse, "", "  ")
	fmt.Printf("%s\n", asJson)
}
