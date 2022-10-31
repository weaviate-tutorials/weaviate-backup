#!/bin/bash
set -e

# Query W1
## Schema
echo -e "\nQuerying schema in W1"
curl http://localhost:8080/v1/schema

## Schema
echo -e "\nQuerying objects in W1"
curl http://localhost:8080/v1/objects

# Query W2
## Schema
echo -e "\nQuerying schema in W2"
curl http://localhost:8090/v1/schema

## Schema
echo -e "\nQuerying objects in W2"
curl http://localhost:8090/v1/objects