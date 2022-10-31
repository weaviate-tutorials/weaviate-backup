#!/bin/bash
set -e

# Restore backup
echo -e "\nRestoring Backup - Author class only"

curl \
-X POST \
-H "Content-Type: application/json" \
-d '{
     "id": "my-very-first-backup",
     "include": ["Author"]
    }' \
http://localhost:8090/v1/backups/filesystem/my-very-first-backup/restore