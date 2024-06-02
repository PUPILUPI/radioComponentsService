#!/bin/bash

# Установить начальный пароль
neo4j-admin dbms set-initial-password aA5914190

# Запустить Neo4j в фоне
neo4j start

# Дождаться, пока Neo4j полностью запустится
echo "Waiting for Neo4j to start..."
until cypher-shell -u neo4j -p aA5914190 "RETURN 1" > /dev/null 2>&1; do
    sleep 1
done

# Выполнить скрипт инициализации
cypher-shell -u neo4j -p aA5914190 -f /var/lib/neo4j/import/init.cypher

# Держать контейнер активным
tail -f /logs/neo4j.log
