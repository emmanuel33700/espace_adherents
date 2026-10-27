#!/bin/bash
set -euo pipefail


#generate the keys
rm privatekey.jks 


keytool -genkeypair -alias privatekey  -keyalg RSA  -keypass mypass -keystore privatekey.jks  -storepass mypass

rm ../../api/privatekey.jks
cp privatekey.jks ../../api/privatekey.jks


# Export Public Key
rm publickey.txt
keytool -exportcert -rfc -alias privatekey -keystore privatekey.jks | openssl x509 -pubkey -noout > publickey.txt



rm ../../api/publickey.txt
cp publickey.txt ../../api/publickey.txt