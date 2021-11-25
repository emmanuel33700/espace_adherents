#!/bin/bash


#generate the keys
rm privatekey.jks 


keytool -genkeypair -alias privatekey  -keyalg RSA  -keypass mypass -keystore privatekey.jks  -storepass mypass

rm ../../api/authorization-server/src/main/resources/privatekey.jks
cp privatekey.jks ../../api/authorization-server/src/main/resources/privatekey.jks


# Export Public Key
rm publickey.txt
keytool -list -rfc --keystore privatekey.jks | openssl x509 -inform pem -pubkey | grep  -B9 'END PUBLIC KEY' > publickey.txt



rm ../../api/resource-adherents/src/main/resources/publickey.txt
cp publickey.txt ../../api/resource-adherents/src/main/resources/publickey.txt


rm ../../api/resource-authorization/src/main/resources/publickey.txt
cp publickey.txt ../../api/resource-authorization/src/main/resources/publickey.txt


rm ../../api/resource-utilitaire/src/main/resources/publickey.txt
cp publickey.txt ../../api/resource-utilitaire/src/main/resources/publickey.txt
