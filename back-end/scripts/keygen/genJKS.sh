#!/bin/bash
set -euo pipefail
umask 077

SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
API_DIR="$(cd -- "$SCRIPT_DIR/../../api" && pwd)"

command -v keytool >/dev/null
command -v openssl >/dev/null

# Ne remplacer les fichiers existants qu'apres generation et validation.
WORK_DIR="$(mktemp -d "$SCRIPT_DIR/.keygen.XXXXXX")"
trap 'rm -rf -- "$WORK_DIR"' EXIT

# Generation interactive de l'identite du certificat, comme auparavant.
keytool -genkeypair -alias privatekey -keyalg RSA \
    -keypass mypass -storepass mypass -keystore "$WORK_DIR/privatekey.jks"

# Le mot de passe explicite evite toute invite pendant l'export.
keytool -exportcert -rfc -alias privatekey \
    -keystore "$WORK_DIR/privatekey.jks" -storepass mypass \
    -file "$WORK_DIR/certificate.pem"
openssl x509 -in "$WORK_DIR/certificate.pem" -pubkey -noout \
    > "$WORK_DIR/publickey.txt"

# Spring Security attend une cle publique X.509 au format PEM.
openssl pkey -pubin -in "$WORK_DIR/publickey.txt" -noout
if [[ "$(head -n 1 "$WORK_DIR/publickey.txt")" != '-----BEGIN PUBLIC KEY-----' ]]; then
    echo 'Format PEM de la cle publique invalide.' >&2
    exit 1
fi

cp -- "$WORK_DIR/privatekey.jks" "$SCRIPT_DIR/privatekey.jks"
cp -- "$WORK_DIR/publickey.txt" "$SCRIPT_DIR/publickey.txt"
cp -- "$WORK_DIR/privatekey.jks" "$API_DIR/privatekey.jks"
cp -- "$WORK_DIR/publickey.txt" "$API_DIR/publickey.txt"
echo "Keystore et cle publique PEM generes dans $SCRIPT_DIR et $API_DIR."
