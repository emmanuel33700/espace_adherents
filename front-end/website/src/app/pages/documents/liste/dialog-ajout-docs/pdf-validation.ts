/**
 * Contrôles préliminaires côté navigateur, contournables par un client HTTP.
 * Le serveur doit analyser la structure PDF et exécuter un antivirus avant publication.
 * La recherche de noms PDF ci-dessous ne décompresse pas les objets et n'est pas un antivirus.
 */
export async function validatePdfFile(file: File): Promise<string[]> {
  const errors: string[] = [];

  if (!/\.pdf$/i.test(file.name)) {
    errors.push('Le fichier doit avoir une extension .pdf.');
  }
  // Certains navigateurs ne renseignent pas le type MIME : la signature reste contrôlée.
  if (file.type && file.type.toLowerCase() !== 'application/pdf') {
    errors.push('Le type du fichier ne correspond pas à un document PDF.');
  }
  if (file.size === 0) {
    errors.push('Le fichier est vide.');
  } else if (file.size > 5000000) {
    errors.push('Le fichier dépasse la taille maximale de 5 Mo.');
  }
  if (errors.length) {
    return errors;
  }

  let bytes: ArrayBuffer;
  try {
    bytes = await file.arrayBuffer();
  } catch {
    return ['Impossible de lire le fichier. Veuillez le sélectionner à nouveau.'];
  }

  const content = new TextDecoder('latin1').decode(bytes);
  if (!/^%PDF-(?:1\.[0-7]|2\.0)(?:\r\n|\r|\n)/.test(content)) {
    return ['Le contenu du fichier ne possède pas une signature PDF reconnue.'];
  }
  if (!/%%EOF[\x00\x09\x0a\x0c\x0d\x20]*$/.test(content)) {
    return ['Le fichier PDF semble incomplet ou contient des données après sa fin.'];
  }

  // Décodage des noms échappés, par exemple /Java#53cript.
  const pdfNames = content.match(/\/[^\x00\x09\x0a\x0c\x0d\x20()<>\[\]{}\/%]+/g) || [];
  const forbiddenNames = new Set([
    '/JavaScript', '/JS', '/AA', '/OpenAction', '/Launch', '/EmbeddedFile', '/EmbeddedFiles',
    '/RichMedia', '/XFA', '/SubmitForm', '/ImportData', '/GoToR', '/GoToE',
  ]);
  for (const name of pdfNames) {
    const decodedName = name.replace(/#([0-9a-f]{2})/gi, (_, hex) => String.fromCharCode(parseInt(hex, 16)));
    if (decodedName === '/Encrypt') {
      return ['Les PDF chiffrés ou protégés par mot de passe ne sont pas acceptés.'];
    }
    if (forbiddenNames.has(decodedName)) {
      return ['Le PDF contient des actions, des scripts ou des pièces jointes non autorisés.'];
    }
  }

  return [];
}
