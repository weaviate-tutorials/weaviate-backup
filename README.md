# Setup

## Docker image

Spin up two Weaviate instances with:

```bash
docker-compose up -d
```

## Create schema
Run the `1_setup` bash script:

```bash
./scripts/1_setup.sh
```

## Test schema
Get schema from the first Weaviate instance. It should return the schema created in the previous step. 

### Terminal
Run the following scripts in the terminal.

```bash
curl http://localhost:8080/v1/schema | json_pp
```

Get schema from the second Weaviate instance. It should return an empty array of classes. 
```bash
curl http://localhost:8090/v1/schema | json_pp
```

### Browser
Or see your schema in the browser:

[Weaviate-1 Schema](http://localhost:8080/v1/schema)

[Weaviate-2 Schema](http://localhost:8090/v1/schema)

## Import sample data
Run the `2_import` bash script:

```bash
./scripts/2_import.sh
```

### Test imported data

#### Terminal

Run the following script in the terminal:
```bash
curl http://localhost:8080/v1/objects?class=Author | json_pp
```

```bash
curl http://localhost:8080/v1/objects?class=Book | json_pp
```

#### Browser

Alternatively you can see your data in the browser

[http://localhost:8080/v1/objects?class=Author](http://localhost:8080/v1/objects?class=Author)

[http://localhost:8080/v1/objects?class=Book](http://localhost:8080/v1/objects?class=Book)

# Clean up

Note, you can run the `9_delete_all` bash script, to remove all schemas and data:

```bash
./scripts/9_delete_all.sh
```
