# Configuration commune aux quatre JAR

Un seul fichier `.env`, à la racine de l'API, fournit les paramètres des quatre
services. Chaque `application.properties` embarqué référence ses variables.
Les bibliothèques `lib-*` utilisent la configuration du service qui les embarque.

Pour une nouvelle installation :

```bash
cp .env.example .env
chmod 600 .env
```

Renseigner les identifiants PostgreSQL, OAuth, Mailjet, les chemins et les URL.
Le `.env` local est ignoré par Git et reste à l'extérieur des JAR.
Les anciens `.env` par service sont remplacés par ce fichier commun.

Les paramètres identiques sont partagés. Ceux qui diffèrent portent un préfixe
`AUTHORIZATION_SERVER_`, `ADHERENTS_`, `AUTHORIZATION_` ou `UTILITAIRE_`.
Les connexions PostgreSQL restent configurables séparément pour chaque service.
Les ports sont `OAUTH_PORT`, `ADHERENTS_PORT`, `AUTHORIZATION_PORT` et `UTILITAIRE_PORT`.

## Lancement local

```bash
./start-resources.sh
# Ou, pour les JAR déjà compilés :
./start-resources-simple.sh
```

Les deux scripts transmettent le même chemin absolu `ENV_FILE` aux quatre JAR.
Les ports sont lus depuis le `.env` commun. Le profil se choisit au lancement
(`SPRING_PROFILES_ACTIVE`, ou `--profile` dans `start-resources.sh`).

Pour utiliser un fichier commun situé ailleurs :

```bash
ENV_FILE=/etc/espace-adherents/.env ./start-resources.sh --no-build --profile prod
```

## Lancement des JAR déployés

Fournir le même `ENV_FILE` à chaque processus Java :

```bash
ENV_FILE=/etc/espace-adherents/.env SPRING_PROFILES_ACTIVE=prod \
java -jar /opt/espace-adherents/resource-authorization.jar
```

Sans `ENV_FILE`, Spring charge `.env` dans le répertoire de travail du processus.
Pour lancer directement un JAR depuis la racine de l'API, ce défaut convient.
Depuis un autre répertoire, préciser le chemin absolu du fichier commun.

Les variables d'environnement du processus remplacent les valeurs du fichier.
Les arguments Spring (`--server.port=8083`, par exemple) restent prioritaires.
L'import est facultatif pour permettre de fournir toute la configuration par
l'environnement. Sans fichier, toutes les variables nécessaires doivent être définies.
Une modification du `.env` nécessite un redémarrage, sans recompilation du JAR.

## Format et compilation

Le `.env` est lu au **format Java Properties** : `NOM=valeur`, sans `export`
ni guillemets. Les espaces internes, `#`, `$` et `=` dans les valeurs sont littéraux ;
les antislashs doivent être doublés (`\\`). Ne pas charger ce fichier avec `source`.

Le `.env.example` ne contient pas de mots de passe. Renseigner les valeurs vides
pour les fonctions utilisées.

## Keystore externe

Les fichiers `mytest.jks` et `privatekey.jks` se trouvent à la racine de l'API,
à côté du `.env`. Ils sont ignorés par Git et exclus des JAR. Le `.env` choisit
le fichier utilisé pour signer les JWT :

```properties
JKS_KEYSTORE=mytest.jks
```

Un chemin relatif est résolu depuis le dossier du fichier `ENV_FILE` (ou du
`.env` par défaut). Un chemin absolu est également accepté, par exemple
`JKS_KEYSTORE=/etc/espace-adherents/privatekey.jks`. Le chargement se fait
uniquement depuis le système de fichiers. Conserver `JKS_KEYPASS` et `JKS_ALIAS`
correspondant au keystore choisi. Déployer ce fichier avec le `.env` et limiter
ses permissions, par exemple `chmod 600 *.jks`.

## Clés publiques externes

Les trois services de ressources partagent le fichier `public.txt` à la racine
de l'API, sélectionné dans le `.env` par `KEY_PUBLIC=public.txt`. Un chemin
relatif est résolu depuis le dossier du `.env`, comme pour le keystore ; un
chemin absolu est également accepté. Déployer cette clé avec le `.env`.

Les anciennes copies identiques des trois services sont regroupées en un seul
fichier. `publickey.txt` contient une autre clé et reste disponible à la racine
pour les configurations qui l'utilisent. Ces deux fichiers sont exclus des JAR.

Les ressources Maven excluent les `.env*`, les `*.jks` et les `application-*.properties`
des archives. Après migration, utiliser `clean package` pour supprimer les
anciennes copies de configuration dans `target/classes`.
