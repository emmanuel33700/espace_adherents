#!/usr/bin/env bash
set -e

# Chemins absolus des archives déjà compilées.
OAUTH_ARCHIVE="/Users/emmanuelchenais/Documents/programmation/espace_adherents/back-end/api/authorization-server/target/authorization-server.jar"
ADHERENTS_ARCHIVE="/Users/emmanuelchenais/Documents/programmation/espace_adherents/back-end/api/resource-adherents/target/resource-adherents.jar"
AUTHORIZATION_ARCHIVE="/Users/emmanuelchenais/Documents/programmation/espace_adherents/back-end/api/resource-authorization/target/resource-authorization.jar"
UTILITAIRE_ARCHIVE="/Users/emmanuelchenais/Documents/programmation/espace_adherents/back-end/api/resource-utilitaire/target/resource-utilitaire.jar"

cd -- "$(dirname -- "${BASH_SOURCE[0]}")"
API_DIR="$PWD"
ENV_FILE="${ENV_FILE:-$API_DIR/.env}"
[[ "$ENV_FILE" = /* ]] || ENV_FILE="$PWD/$ENV_FILE"
export ENV_FILE
LOGGING_CONFIG="${LOGGING_CONFIG:-file:$API_DIR/logback.xml}"
export LOGGING_CONFIG
JAVA_BIN="${JAVA_HOME:+$JAVA_HOME/bin/}java"
LOG_DIR="$API_DIR/logs"
PIDS=()

# Les archives du projet sont des JAR exécutables Spring Boot.
for archive in "$OAUTH_ARCHIVE" "$ADHERENTS_ARCHIVE" "$AUTHORIZATION_ARCHIVE" "$UTILITAIRE_ARCHIVE"; do
    if [[ ! -f "$archive" ]]; then
        echo "Archive absente : $archive" >&2
        exit 1
    fi
done

mkdir -p "$LOG_DIR/console"


stop() {
    trap - EXIT INT TERM
    kill "${PIDS[@]}" 2>/dev/null || true
    wait 2>/dev/null || true
}
trap stop EXIT
trap 'exit 130' INT
trap 'exit 143' TERM

start() {
    local module="$1" archive="$2"
    (
        cd "$module"
        exec "$JAVA_BIN" -jar "$archive" \
            "--spring.profiles.active=${SPRING_PROFILES_ACTIVE:-dev}" \
            "--logging.config=$LOGGING_CONFIG" \
            "--logging.file.path=$LOG_DIR"
    ) > "$LOG_DIR/console/$module.log" 2>&1 &
    PIDS+=("$!")
    echo "$module — configuration : $ENV_FILE"
}

start authorization-server "$OAUTH_ARCHIVE"
start resource-adherents "$ADHERENTS_ARCHIVE"
start resource-authorization "$AUTHORIZATION_ARCHIVE"
start resource-utilitaire "$UTILITAIRE_ARCHIVE"
echo "Logs : $LOG_DIR/ — Ctrl+C pour arrêter."
wait
