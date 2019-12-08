#!/bin/bash


#generate the keys
keytool -genkeypair -alias mytest 
                    -keyalg RSA 
                    -keypass mypass 
                    -keystore mytest.jks 
                    -storepass mypass


# Export Public Key
keytool -list -rfc --keystore mytest.jks | openssl x509 -inform pem -pubkey


