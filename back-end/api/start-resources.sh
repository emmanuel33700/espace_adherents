#!/usr/bin/env bash
set -euo pipefail

PROJECT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
PROFILE="${SPRING_PROFILES_ACTIVE:-dev}"
BUILD=true
PIDS=()
MODULES=(authorization-server resource-adherents resource-authorization resource-utilitaire)
ENV_FILE="${ENV_FILE:-$PROJECT_DIR/.env}"
[[ "$ENV_FILE" = /* ]] || ENV_FILE="$PWD/$ENV_FILE"
export ENV_FILE
LOGGING_CONFIG="${LOGGING_CONFIG:-file:$PROJECT_DIR/logback.xml}"
export LOGGING_CONFIG
LOG_DIR="$PROJECT_DIR/logs"

usage() {
    cat <<'HELP'
Usage: ./start-resources.sh [--no-build] [--profile NOM]

Compile puis lance le serveur OAuth et les trois API. Ctrl+C les arrête ensemble.
  --no-build      Utiliser les JAR déjà générés.
  --profile NOM   Profil Spring actif (dev par défaut).
  -h, --help      Afficher cette aide.

Variables facultatives :
  JAVA_HOME            JDK 21 ou supérieur.
  MVN                  Chemin de l'exécutable Maven.
  OAUTH_PORT           8081 par défaut.
  ADHERENTS_PORT       8082 par défaut.
  AUTHORIZATION_PORT   8083 par défaut.
  UTILITAIRE_PORT       8084 par défaut.
  ENV_FILE             Fichier .env commun (par défaut : .env à la racine de l’API).

Copier .env.example en .env à la racine de l’API et renseigner les valeurs.
Les ports sont lus dans ce fichier, ou dans les variables du processus.
Les logs sont écrits dans target/resources-run/<nom-api>.log.
PostgreSQL doit être démarré séparément.
HELP
}

while [[ $# -gt 0 ]]; do
    case "$1" in
        --no-build) BUILD=false; shift ;;
        --profile)
            [[ $# -ge 2 && -n "$2" ]] || { echo 'Profil manquant.' >&2; exit 2; }
            PROFILE="$2"; shift 2 ;;
        -h|--help) usage; exit 0 ;;
        *) echo "Argument inconnu : $1" >&2; usage >&2; exit 2 ;;
    esac
done

JAVA_BIN="${JAVA_HOME:+$JAVA_HOME/bin/}java"
command -v "$JAVA_BIN" >/dev/null 2>&1 || { echo 'Java introuvable. Configurer JAVA_HOME vers un JDK 21.' >&2; exit 1; }
JAVA_VERSION="$("$JAVA_BIN" -version 2>&1 | head -n 1)"
if [[ ! "$JAVA_VERSION" =~ \"([0-9]+)\. ]] || (( BASH_REMATCH[1] < 21 )); then
    echo "Un JDK 21 ou supérieur est requis : $JAVA_VERSION" >&2; exit 1
fi
export JAVA_HOME="$("$JAVA_BIN" -XshowSettings:properties -version 2>&1 | sed -n 's/^[[:space:]]*java.home = //p')"
JAVA_BIN="$JAVA_HOME/bin/java"

if $BUILD; then
    MAVEN_BIN="${MVN:-mvn}"
    if [[ -z "${MVN:-}" ]] && ! command -v "$MAVEN_BIN" >/dev/null 2>&1; then
        MAVEN_BIN='/Applications/IntelliJ IDEA.app/Contents/plugins/maven-plugin/lib/maven3/bin/mvn'
    fi
    [[ -x "$MAVEN_BIN" ]] || command -v "$MAVEN_BIN" >/dev/null 2>&1 || {
        echo 'Maven introuvable. Configurer MVN avec le chemin de son exécutable.' >&2; exit 1;
    }
    (cd "$PROJECT_DIR" && "$MAVEN_BIN" -B -pl authorization-server,resource-adherents,resource-authorization,resource-utilitaire -am -DskipTests package)
fi

for module in "${MODULES[@]}"; do
    [[ -f "$PROJECT_DIR/$module/target/$module.jar" ]] || {
        echo "JAR absent : $module. Relancer sans --no-build." >&2; exit 1;
    }
done

mkdir -p "$LOG_DIR/console"


cleanup() {
    trap - EXIT INT TERM
    if [[ ${#PIDS[@]} -gt 0 ]]; then
        echo 'Arrêt des services…'
        kill "${PIDS[@]}" 2>/dev/null || true
        for pid in "${PIDS[@]}"; do wait "$pid" 2>/dev/null || true; done
    fi
}
trap cleanup EXIT
trap 'exit 130' INT
trap 'exit 143' TERM

for i in "${!MODULES[@]}"; do
    module="${MODULES[$i]}"
    java_args=("-jar" "target/$module.jar"
        "--spring.profiles.active=$PROFILE"
        "--logging.config=$LOGGING_CONFIG"
        "--logging.file.path=$LOG_DIR")
    (
        cd "$PROJECT_DIR/$module"
        exec "$JAVA_BIN" "${java_args[@]}"
    ) > "$LOG_DIR/console/$module.log" 2>&1 &
    PIDS+=("$!")
    echo "Lancement de $module (PID $!) — configuration : $ENV_FILE"
    echo "  Log : $LOG_DIR/$module.log — console : $LOG_DIR/console/$module.log"
done

echo 'Ctrl+C pour arrêter le serveur OAuth et les trois API.'
while true; do
    for i in "${!PIDS[@]}"; do
        if ! kill -0 "${PIDS[$i]}" 2>/dev/null; then
            echo "${MODULES[$i]} s'est arrêté. Consulter $LOG_DIR/console/${MODULES[$i]}.log" >&2
            exit 1
        fi
    done
    sleep 1
done
