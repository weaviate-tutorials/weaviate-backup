#!/bin/bash
set -e

# Check backup status
echo -e "\nChecking restore status"

# Syntax: /v1/backups/{backend}/{backup_id}/restore
curl http://localhost:8090/v1/backups/filesystem/my-very-first-backup/restore