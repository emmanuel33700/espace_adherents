/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
export const environment = {
  production: true,
  resource_adherents: 'https://api.jalle-astro.fr/resource-adherents',
  resource_authorization: 'https://api.jalle-astro.fr/resource-authorization',
  resource_utilitaire: 'https://api.jalle-astro.fr/resource-utilitaire',

  endpoint_authorisation_serveur: 'https://api.jalle-astro.fr/authorization-server/oauth/',
  as_clientId: 'espaceAdh',
  as_clientSecret: 'secret',


  // Lien des dépots des photos de profil (attention a mettre le / en fin d'url)
  url_photo_profil: 'https://api.jalle-astro.fr/partage/photosprofil/',

  // Lien vers les documents (attention a mettre le / en fin d'url)
  url_document: 'https://api.jalle-astro.fr/partage/documents/',
};
