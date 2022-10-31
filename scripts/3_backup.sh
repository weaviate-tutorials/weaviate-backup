#!/bin/bash
set -e

# Create backup
## Author
echo -e "\nCreating Backup"

curl \
-X POST \
-H "Content-Type: application/json" \
-d '{
     "id": "my-very-first-backup"
    }' \
http://localhost:8080/v1/backups/filesystem