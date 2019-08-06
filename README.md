# Plateforme de gestion des adhérents (Espace adhérents)

Si vous êtes responsable d’une association et que vous recherchez un logiciel gratuit open source permettant  de gérer vos adhésions, d’assurer une communication avec adhérents ou partager votre base documentaire. Ce logiciel devrait vous convenir.
Le logiciel est en cours de développement, les contributeurs sont les bienvenus

## Construire le projet
```
mvn clean install
```

## Les modules back-end
- `authorization-server` - Serveur d'authorisation oauth2
- `resource-authorization` - API relative à la gestion des autorisations d'un adhérent
- `resource-adherents` - API relative à la gestion des adhérents
- `lib-mails` - Librairie permentant l'envoie et la mise en page de mail

chaque module est développé dans le wiki

Pour démarrer les modules, aller dans chaque module et exécuter la commande suivante
```
mvn spring-boot:run
```

## Les modules front-end
- `client-poc-authorization-code-angular` - Poc pour tester le serveur d'authorisation avec authorization-code

Pour démarrer le module front-end.

Naviguer dans le module angular  puis
```
mvn clean install
```

naviguer dans le dossier ressources
```
cd src/main/resources
```

et utiliser la commande suivante pour télécharger les dépendances
```
npm install
```

puis démarrer l'application
```
npm start
```
