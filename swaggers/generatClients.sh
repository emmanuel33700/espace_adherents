#!/bin/bash

#generate authorization
rm -r ./../front-end/website/src/api/generated/authorization/
mkdir ./../front-end/website/src/api/generated/authorization/
ng-openapi-gen --input  resource-authorization.yaml --output ./../front-end/website/src/api/generated/authorization/


#generate resource-adherents
rm -r ./../front-end/website/src/api/generated/adherents/
mkdir ./../front-end/website/src/api/generated/adherents/
ng-openapi-gen --input  resource-adherents.yaml --output ./../front-end/website/src/api/generated/adherents/


#generate resource-utilitaire
rm -r ./../front-end/website/src/api/generated/utilitaire/
mkdir ./../front-end/website/src/api/generated/utilitaire/
ng-openapi-gen --input  resource-utilitaire.yaml --output ./../front-end/website/src/api/generated/utilitaire/
