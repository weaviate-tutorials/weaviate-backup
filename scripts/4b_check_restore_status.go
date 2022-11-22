package main

import (
	"context"
	"encoding/json"
	"fmt"

	"github.com/semi-technologies/weaviate-go-client/v4/weaviate"
	"github.com/semi-technologies/weaviate-go-client/v4/weaviate/backup"
)

func main() {
	hostW2 := "localhost:8090"

	ctx := context.Background()
	client := weaviate.New(weaviate.Config{Scheme: "http", Host: hostW2})

	fmt.Println("\nChecking restore status");
	restoreStatusResponse, err := client.Backup().RestoreStatusGetter().
		WithBackupID("my-very-first-backup").
		WithBackend(backup.BACKEND_FILESYSTEM).
		Do(ctx)
	if err != nil {
		fmt.Println(err)
		return
	}
	asJson, _ := json.MarshalIndent(restoreStatusResponse, "", "  ")
	fmt.Printf("%s\n", asJson)
}
