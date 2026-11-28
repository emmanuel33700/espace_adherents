#!/bin/bash

# --- CONFIGURATION DES VARIABLES ---
DB_NAME_1="authorization"
DB_NAME_2="espaceadh"
PG_USER="jalleastro"
PG_PASSWORD="$$$$$$$"
# ----------------------------------

echo "Début de la configuration PostgreSQL (Gestion des éléments existants)..."

# Exécution des commandes SQL en basculant sur l'utilisateur 'postgres' via 'su'
su - postgres -c "psql" <<EOF
-- 1. Création des bases de données si elles n'existent pas
SELECT 'Création de la base $DB_NAME_1';
CREATE DATABASE "$DB_NAME_1" WITH ALLOW_CONNECTIONS = true;
\errverbose

SELECT 'Création de la base $DB_NAME_2';
CREATE DATABASE "$DB_NAME_2" WITH ALLOW_CONNECTIONS = true;
\errverbose

-- 2. Création de l'utilisateur de manière conditionnelle (évite l'erreur si existant)
DO \$\$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = '$PG_USER') THEN
      CREATE USER "$PG_USER" WITH PASSWORD '$PG_PASSWORD';
   ELSE
      ALTER USER "$PG_USER" WITH PASSWORD '$PG_PASSWORD';
   END IF;
END
\$\$;

-- 3. Configuration des droits sur la base 1
\c "$DB_NAME_1"
GRANT CONNECT ON DATABASE "$DB_NAME_1" TO "$PG_USER";
GRANT pg_read_all_data TO "$PG_USER";
GRANT pg_write_all_data TO "$PG_USER";

-- 4. Configuration des droits sur la base 2
\c "$DB_NAME_2"
GRANT CONNECT ON DATABASE "$DB_NAME_2" TO "$PG_USER";
GRANT pg_read_all_data TO "$PG_USER";
GRANT pg_write_all_data TO "$PG_USER";
EOF

if [ $? -eq 0 ]; then
    echo "Configuration terminée avec succès !"
    echo "Bases configurées : $DB_NAME_1, $DB_NAME_2"
    echo "Utilisateur configuré : $PG_USER"
else
    echo "Une erreur est survenue lors de l'exécution du script."
fi
