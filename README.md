# Plateforme de gestion des adhérents (Espace adhérents)

Si vous êtes responsable d’une association et que vous recherchez un logiciel gratuit open source permettant  de gérer vos adhésions, d’assurer une communication avec adhérents ou partager votre base documentaire. Ce logiciel devrait vous convenir.
Le logiciel est en cours de développement, les contributeurs sont les bienvenus

Site de démo consultable via l'url suivante : http://dev.jalle-astro.fr/

Le logiciel est composé de 2 parties :
- Back-end développé en java
- Front-end développé en Angular

## Partie back-end
Pre requis technique
- Java >= version 8
- Maven

### Construire le projet

```
cd back-end/api
```

```
mvn clean install
```

### Les modules back-end
- `authorization-server` - Serveur d'authorisation oauth2
- `resource-authorization` - API relative à la gestion des autorisations d'un adhérent
- `resource-adherents` - API relative à la gestion des adhérents
- `lib-mails` - Librairie permentant l'envoie et la mise en page de mail

chaque module est développé dans le wiki

Pour démarrer les modules, aller dans chaque module (hors lib-mails qui n'est pas une API) et exécuter la commande suivante
```
mvn spring-boot:run
```

## Partie front-end
Pre requis technique
- Node.js >= verison 8
- Npm >= version 5

### Construire le projet
Récupérer les dépendances
```
cd front-end/website
```
```
npm i
```

Démarrer l'application en développement
```
npm start
```

Packager l'appliation en production
```
npm run build:prod
```
