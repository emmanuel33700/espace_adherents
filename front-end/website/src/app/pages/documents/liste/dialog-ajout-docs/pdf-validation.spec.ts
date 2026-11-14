import {validatePdfFile} from './pdf-validation';

describe('validatePdfFile', () => {
  const pdf = (body = '', name = 'document.pdf', type = 'application/pdf') =>
    new File([`%PDF-1.7\n${body}\n%%EOF\n`], name, {type});

  it('accepte les contrôles préliminaires pour un PDF sans contenu actif visible', async () => {
    expect(await validatePdfFile(pdf())).toEqual([]);
  });

  it('accepte une extension majuscule et un type MIME absent', async () => {
    expect(await validatePdfFile(pdf('', 'document.PDF', ''))).toEqual([]);
  });

  it('refuse une extension différente malgré le type MIME PDF', async () => {
    expect((await validatePdfFile(pdf('', 'document.exe'))).length).toBeGreaterThan(0);
  });

  it('refuse un type MIME différent malgré une extension PDF', async () => {
    expect((await validatePdfFile(pdf('', 'document.pdf', 'text/html'))).length).toBeGreaterThan(0);
  });

  it('refuse un fichier vide', async () => {
    expect((await validatePdfFile(new File([], 'document.pdf'))).length).toBeGreaterThan(0);
  });

  it('refuse les fichiers de plus de 5 Mo avant de les lire', async () => {
    const file = new File([new Uint8Array(5000001)], 'document.pdf');
    const read = spyOn(file, 'arrayBuffer');
    expect((await validatePdfFile(file)).length).toBeGreaterThan(0);
    expect(read).not.toHaveBeenCalled();
  });

  it('refuse un fichier HTML renommé en PDF', async () => {
    const file = new File(['<html>%%EOF'], 'document.pdf', {type: 'application/pdf'});
    expect((await validatePdfFile(file)).length).toBeGreaterThan(0);
  });

  it('refuse une fin de fichier manquante ou des données ajoutées après la fin', async () => {
    for (const content of ['%PDF-1.7\n', '%PDF-1.7\n%%EOF\n<html>']) {
      expect((await validatePdfFile(new File([content], 'document.pdf'))).length).toBeGreaterThan(0);
    }
  });

  it('refuse les scripts, actions et pièces jointes visibles, y compris les noms échappés', async () => {
    for (const name of ['/JavaScript', '/JS', '/Java#53cript', '/#4aS', '/OpenAction',
      '/AA', '/Launch', '/EmbeddedFile', '/EmbeddedFiles', '/RichMedia', '/XFA',
      '/SubmitForm', '/ImportData', '/GoToR', '/GoToE']) {
      expect((await validatePdfFile(pdf(`<<${name} 1 0 R>>`))).length).toBeGreaterThan(0);
    }
  });

  it('refuse les documents chiffrés', async () => {
    expect((await validatePdfFile(pdf('/Encrypt 1 0 R'))).length).toBeGreaterThan(0);
  });

  it('distingue un nom PDF complet de son préfixe', async () => {
    expect(await validatePdfFile(pdf('/JSuffix /Launcher'))).toEqual([]);
  });

  it('retourne une erreur lisible quand la lecture échoue', async () => {
    const file = pdf();
    spyOn(file, 'arrayBuffer').and.returnValue(Promise.reject(new Error('Lecture impossible')));
    expect((await validatePdfFile(file)).length).toBeGreaterThan(0);
  });
});
