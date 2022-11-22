package main

import (
	"context"
	"encoding/json"
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

		fmt.Printf("\nQuerying schema in %s\n", name)
		schema, err := client.Schema().Getter().Do(ctx)
		if err != nil {
			fmt.Println(err)
		} else {
			asJson, _ := json.MarshalIndent(schema, "", "  ")
			fmt.Printf("%s\n", asJson)
		}

		fmt.Printf("\nQuerying objects in %s\n", name)
		objects, err := client.Data().ObjectsGetter().Do(ctx)
		if err != nil {
			fmt.Println(err)
		} else {
			asJson, _ := json.MarshalIndent(objects, "", "  ")
			fmt.Printf("%s\n", asJson)
		}
	}
}
