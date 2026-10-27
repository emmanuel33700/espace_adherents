# Java 21 et Spring Boot 3

Le parent Spring Boot passe à **3.5.16**, avec compilation `--release 21`.
La version Maven de l'application (`2.7.18`) reste distincte de celle de Spring Boot.
Les versions de Spring Framework, Spring Security 6, Spring Authorization Server,
Jackson, PostgreSQL, Tomcat et des outils de test proviennent du BOM Spring Boot.
Springfox est remplacé par springdoc 2.8.17 ; Mailjet passe à 6.0.1.
Les API Servlet, Validation et Annotation utilisent Jakarta. ThreeTen est remplacé
par `java.time` et les dépendances inutilisées c3p0 et ical4j sont retirées.

## Construction

Configurer `JAVA_HOME` sur un JDK 21, puis exécuter :

```sh
mvn clean verify
```

Les tests par défaut n'appellent ni Mailjet ni un serveur OAuth externe. Les tests
d'envoi de courriels et d'authentification distante portent désormais le suffixe
`LiveTest`, déjà exclu par défaut. `ClientResourceAuthorizationExample` conserve
l'ancien exemple manuel, qui ne contenait aucune méthode annotée `@Test`.
Les profils `integration` et `live` nécessitent les services et les propriétés
locaux décrits dans les fichiers `application-template.properties`.

## Authentification et compatibilité

Le module partagé `lib-security` porte le client OAuth moderne et la conversion
JWT. Les API vérifient la signature RSA avec le fichier `key.public` existant et
conservent les contrôles par scope, groupe et propriétaire des données.
Les champs `user_name`, `idAdherent`, `authorities` et `organization` restent
compatibles avec les jetons historiques. Les champs métier sont aussi présents
dans la réponse OAuth, utilisée par le frontend, y compris après renouvellement.

Spring Authorization Server remplace Spring Security OAuth. Il réutilise les
utilisateurs JDBC, les mots de passe BCrypt et la clé privée du keystore existant.
Les clients conservent leurs identifiants, secrets, scopes et durées de validité.
Le flux `password` est une extension de compatibilité, réservée au client desktop ;
les nouveaux clients peuvent utiliser le flux authorization code avec PKCE.
Le flux `client_credentials` conserve les scopes par défaut du client lorsque
aucun paramètre `scope` n'est envoyé. Le renouvellement et la révocation utilisent
les composants de Spring Authorization Server.

| Usage | Chemin relatif au serveur d'autorisation |
| --- | --- |
| Émission et renouvellement | `POST /oauth/token` |
| Autorisation | `/oauth/authorize` |
| Introspection standard OAuth | `POST /oauth/check_token` |
| Révocation standard OAuth | `POST /oauth/revoke` |
| Révocation historique du jeton courant | `DELETE /oauth/token` |
| Clé publique PEM historique | `GET /oauth/token_key` |
| Clés publiques JWK | `GET /oauth/jwks` |

L'introspection utilise désormais le format standard de Spring Authorization
Server. Les autorisations et les refresh tokens sont conservés en mémoire : un
redémarrage impose une nouvelle connexion et plusieurs instances nécessitent un
stockage partagé d'`OAuth2AuthorizationService`. Les API valident les access tokens
JWT localement ; un access token déjà émis reste donc accepté jusqu'à expiration,
comme avec l'ancien `JwtTokenStore`.

## Déploiement

- Ajouter `oauth2.issuer` dans la configuration effective du serveur d'autorisation,
  avec son URL publique complète, incluant `/authorization-server`. La valeur du
  template est destinée au développement local.
- Conserver les paramètres JDBC, clients OAuth et keystore des environnements,
  ainsi que `key.public` dans chaque API. La clé publique doit correspondre au
  keystore du serveur d'autorisation.
- Les trois API `resource-*` et le serveur OAuth `authorization-server` produisent
  des JAR exécutables avec Tomcat embarqué, à lancer avec Java 21 via `java -jar`.
- La documentation OpenAPI est disponible à `/v3/api-docs` et l'interface à
  `/swagger-ui.html`, sous le chemin de contexte de chaque application.

## Lancer le serveur OAuth et les trois API en local

Créer et renseigner `src/main/resources/application-dev.properties` dans chacun
des modules `authorization-server`, `resource-adherents`, `resource-authorization`
et `resource-utilitaire`,
à partir de son `application-template.properties`. Démarrer PostgreSQL
séparément, puis lancer depuis le répertoire `api` :

```sh
./start-resources.sh
```

Le script compile les dépendances et génère les JAR exécutables avant de lancer
les quatre processus, en commençant par OAuth. Les ports sont 8081 (OAuth),
8082 (adhérents), 8083 (API autorisation) et 8084 (utilitaire). Les chemins de contexte restent ceux des modules. `Ctrl+C` arrête
les quatre services ; l'arrêt inattendu d'un service entraîne l'arrêt des autres.
Les logs sont écrits dans `target/resources-run/<nom-api>.log`.

```sh
# Réutiliser les JAR déjà construits
./start-resources.sh --no-build

# Choisir le profil et un port
UTILITAIRE_PORT=8094 ./start-resources.sh --profile dev

# Utiliser des configurations externes, sans les inclure dans les JAR
CONFIG_DIR=/chemin/config ./start-resources.sh --no-build
```

Dans le dernier cas, placer les fichiers dans
`/chemin/config/authorization-server/application-dev.properties`,
`/chemin/config/resource-adherents/application-dev.properties`,
`/chemin/config/resource-authorization/application-dev.properties` et
`/chemin/config/resource-utilitaire/application-dev.properties`.
Utiliser `./start-resources.sh --help` pour les options et variables disponibles.

Le script simplifié lance les mêmes quatre JAR déjà compilés, sans Maven :

```sh
./start-resources-simple.sh
```

Ses chemins absolus sont définis en début de fichier. Il utilise le profil `dev`
et les ports 8081 à 8084. Les scripts démarrent les processus sans attendre une
vérification de disponibilité HTTP ; consulter les logs pour confirmer leur démarrage.
