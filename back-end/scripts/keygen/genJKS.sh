#!/bin/bash


#generate the keys
rm privatekey.jks 


keytool -genkeypair -alias privatekey  -keyalg RSA  -keypass mypass -keystore privatekey.jks  -storepass mypass

rm ../../api/privatekey.jks
cp privatekey.jks ../../api/privatekey.jks


# Export Public Key
rm publickey.txt
keytool -list -rfc --keystore privatekey.jks | openssl x509 -inform pem -pubkey | grep  -B9 'END PUBLIC KEY' > publickey.txt



rm ../../api/publickey.txt
cp publickey.txt ../../api/publickey.txt