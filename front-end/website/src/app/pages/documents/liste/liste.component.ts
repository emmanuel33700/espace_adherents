import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {
  NbDialogService,
  NbSortDirection,
  NbSortRequest, NbToastrService,
  NbTreeGridDataSource,
  NbTreeGridDataSourceBuilder,
} from '@nebular/theme';
import {LoggerService} from '../../../@core/utils';
import {DialogAjoutDocsComponent} from './dialog-ajout-docs/dialog-ajout-docs.component';
import {DialogAjoutRepertoireComponent} from './dialog-ajout-repertoire/dialog-ajout-repertoire.component';
import {DocumentationService} from '../../../../api/generated/utilitaire/services/documentation.service';
import {ArborescenceDocuments} from '../../../../api/generated/utilitaire/models/arborescence-documents';
import {DownloadService} from '../../../@core/utils/download.service';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {environment} from '../../../../environments/environment';

interface TreeNode<T> {
  data: T;
  children?: TreeNode<T>[];
  expanded?: boolean;
}

interface FSEntry {
  nom: string;
  auteur: string;
  description: string;
  kind: string;
  items?: number;
  id: number;
  lienFichier: string;
}


@Component({
  selector: 'ngx-tree-grid',
  templateUrl: './liste.component.html',
  styleUrls: ['./liste.component.scss'],
})
export class ListeComponent implements OnInit {

  customColumn = 'nom';
  actionColumn = 'action';
  defaultColumns = ['auteur', 'description', 'items'];
  allColumns = [this.customColumn, ...this.defaultColumns, this.actionColumn];

  dataSource: NbTreeGridDataSource<FSEntry>;

  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;


  // Droit de réaliser des actions sur les documents ou l'arboresence
  droitEditionDocument = false;

  // Formulaire de création du répertoire
  repertoireForm: any = {};

  // Formulaire de création du document
  documentForm: any = {};

  // Information de l'adhérent courant (Qui utilise l'IHM)
  adherent: Adherent = JSON.parse(localStorage.getItem('adherent'));

  url_documents: string = environment.url_document;

  constructor(private dataSourceBuilder: NbTreeGridDataSourceBuilder<FSEntry>,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private dialogService: NbDialogService,
              private documentationService: DocumentationService,
              private downloads: DownloadService) {
    this.dataSource = this.dataSourceBuilder.create(this.data);
  }


  updateSort(sortRequest: NbSortRequest): void {
    this.sortColumn = sortRequest.column;
    this.sortDirection = sortRequest.direction;
  }

  getSortDirection(column: string): NbSortDirection {
    if (this.sortColumn === column) {
      return this.sortDirection;
    }
    return NbSortDirection.NONE;
  }

  private data: TreeNode<FSEntry>[] = [];

  getShowOn(index: number) {
    const minWithForMultipleColumns = 400;
    const nextColumnStep = 100;
    return minWithForMultipleColumns + (nextColumnStep * index);
  }


  /**
   * Initialisation
   */
  ngOnInit(): void {
    const role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if (role !== 'ADHERENT') {
      this.droitEditionDocument = true;
    }

    // Récupérer arborescence
    let arborescenceDocuments: ArborescenceDocuments[] = [];
    this.documentationService.getArboresence({})
      .subscribe(
        (data) => {
          arborescenceDocuments = data;
          this.data = this.creerArborescenceDocumentsData(arborescenceDocuments);
          this.loggerService.info(JSON.stringify(this.data));
          this.dataSource = this.dataSourceBuilder.create(this.data);

          this.loggerService.info(JSON.stringify(arborescenceDocuments));
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('fini');
        });


  }

  /**
   * Creer l'Arborescence à affichier
   * @param arborescenceDocuments
   */
  private creerArborescenceDocumentsData(arborescenceDocuments: ArborescenceDocuments[]): TreeNode<FSEntry>[] {
    let lstTreeNode: TreeNode<FSEntry>[];
    lstTreeNode = [];

    arborescenceDocuments.forEach((value, index) => {
      let treeNode: TreeNode<FSEntry>;
      treeNode = this.transformeModelToFsEntry(value);

      if (value.enfants != null && !this.isEmptyUnderkill(value.enfants)) {
        treeNode.children = [];

        const lstTreeNode2: TreeNode<FSEntry>[] = this.creerArborescenceDocumentsData(value.enfants);
        treeNode.children = lstTreeNode2;
      }

      lstTreeNode.push(treeNode);
    });
    return lstTreeNode;
  }

  /**
   * Transforme le model de document retourné par l'API en FSEntry
   * @param value
   */
  private transformeModelToFsEntry(value: ArborescenceDocuments): TreeNode<FSEntry> {
    let kind = 'dir';
    let nomPrenomAuteur = '-';
    let nbElntEnfant: number = 0;
    if (value.parent.nomFichier != null) {
      kind = 'doc';
      nomPrenomAuteur = value.parent.prenomNomAuteur;
    } else {
      if (value.enfants != null && !this.isEmptyUnderkill(value.enfants)) {
        nbElntEnfant = value.enfants.length;
      }
    }

    const valueTreeNodeAdd: TreeNode<FSEntry> = {
      data: {
        nom: value.parent.libelleCourt,
        auteur: nomPrenomAuteur,
        items: nbElntEnfant,
        description: value.parent.libelleLong,
        kind: kind,
        id: value.parent.id,
        lienFichier: value.parent.nomFichier,
      },
    };
    return valueTreeNodeAdd;
  }

  /**
   * Ajouter un répertoire
   * @param idRepParent
   */
  ajouterRepertoire(idRepertoireParent: number) {
    this.loggerService.info('ajouterRepertoire ' + idRepertoireParent);

    let repertoireForm: any;
    repertoireForm = {};

    this.dialogService.open(DialogAjoutRepertoireComponent, {
      context: {
        idRepertoireRatachement: idRepertoireParent,
      },
    }).onClose.subscribe(data => {
      repertoireForm = data;
      this.loggerService.debug('Non du repertoire ajout ' + repertoireForm.repertoire);
      this.loggerService.debug('Détail du repertoire ajout ' + repertoireForm.repertoireDetail);

      this.ajouterElement(this.data, idRepertoireParent, 'dir', repertoireForm.repertoire,
        '-'
        , repertoireForm.repertoireDetail, repertoireForm.id, null);
      this.dataSource = this.dataSourceBuilder.create(this.data);

    });


  }

  /**
   * Recherche répertoire à supprimer et le supprimer en local
   * @param valueTreeNode
   * @param idRepertoireParent
   */
  private supprimerElement(valueTreeNode: TreeNode<FSEntry>[], idElement: number): boolean {

    if (valueTreeNode != null && !this.isEmptyUnderkill(valueTreeNode)) {
      const constTreeMode: TreeNode<FSEntry>[] = valueTreeNode;
      constTreeMode.forEach((value, index) => {

        const constFSentry: FSEntry = value.data;
        if (idElement === constFSentry.id) {
          this.loggerService.debug('====> Document à supprimer trouvé ' + constFSentry.nom);
          constTreeMode.splice(index, 1);
          return true;
        }

        const result: boolean = this.supprimerElement(value.children, idElement);
        if (value.children != null && !this.isEmptyUnderkill(value.children)) {
          value.data.items = value.children.length;
        }
      });
    }
    return true;
  }

  /**
   * Vérifier si une liste est vide
   * @param obj
   */
  private isEmptyUnderkill(obj: any) {
    return Object.keys(obj).length === 0;
  }

  /**
   * Demander la supression d'un répertoire
   * @param dialog
   * @param idRepertoire
   */
  supprimerRepertoire(idRepertoire: number) {
    this.loggerService.info('supprimerRepertoire ' + idRepertoire);
    if (confirm('Etes vous sur de supprimer le répertoire')) {

      /*
       * Appel de l'API
       */
      this.documentationService.delDossier({idDossier: idRepertoire})
        .subscribe(
          (data) => {
            this.loggerService.debug(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(JSON.stringify(error));
            this.toastrService.danger(
              'Erreur technique lors de la suppression du répertoire',
              'Erreur ');
          },
          () => {
            this.loggerService.info('Suppression répertoire fini');
          },
        );


      this.supprimerElement(this.data, idRepertoire);
      this.dataSource = this.dataSourceBuilder.create(this.data);
    }
  }


  /**
   * Ajouter un fichier à un répertoire
   * @param idRepertoireParent
   */
  ajouterFichier(idRepertoireParent: number) {
    let documentForm: any;
    documentForm = {};
    this.loggerService.info('ajouterFichier ' + idRepertoireParent);
    this.dialogService.open(DialogAjoutDocsComponent, {
      context: {
        idRepertoireRatachement: idRepertoireParent,
      },
    }).onClose.subscribe(data => {
      documentForm = data;
      this.loggerService.debug('Non du document ajout ' + documentForm.fichier);
      this.loggerService.debug('Détail du document ajout ' + documentForm.fichierDetail);

      this.ajouterElement(this.data, idRepertoireParent, 'doc', documentForm.fichier,
        this.adherent.prenom + ' ' + this.adherent.nom
        , documentForm.fichierDetail, documentForm.id, documentForm.nomFichier);
      this.dataSource = this.dataSourceBuilder.create(this.data);

    });

  }

  /**
   * Editer un répertoire
   * @param idRepertoire
   */
  editerRepertoire(idRepertoire: number) {
    this.loggerService.info('editerRepertoire ' + idRepertoire);
  }

  /**
   * Supprimer un fichier
   * @param id
   */
  supprimerFichier(idFichier: number) {
    this.loggerService.info('supprimerFichier ' + idFichier);
    if (confirm('Etes vou sur de supprimer le fichier')) {


      /*
       * Appel de l'API
       */
      this.documentationService.delFichier({idFichier: idFichier})
        .subscribe(
          (data) => {
            this.loggerService.debug(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(JSON.stringify(error));
            this.toastrService.danger(
              'Erreur technique lors de la suppression du répertoire',
              'Erreur ');
          },
          () => {
            this.loggerService.debug('Suppression répertoire fini');
          },
        );

      this.supprimerElement(this.data, idFichier);
      this.dataSource = this.dataSourceBuilder.create(this.data);
    }
  }


  /**
   * Editer un fichier
   * @param idFichier
   */
  editerFichier(idFichier: number) {
    this.loggerService.info('editerFichier ' + idFichier);

  }
  /**
   * Ajouter un élément dans l'arboresence
   * @param valueTreeNode
   * @param idElementParent
   * @param kind
   * @param nonDocument
   * @param auteur
   * @param description
   * @param idElement
   * @param lienFichier
   */
  ajouterElement(valueTreeNode: TreeNode<FSEntry>[], idElementParent: number, kind: string, nonDocument: string,
                 auteur: string, description: string, idElement: number, lienFichier: string) {


    if (valueTreeNode != null && !this.isEmptyUnderkill(valueTreeNode)) {
      const constTreeMode: TreeNode<FSEntry>[] = valueTreeNode;
      constTreeMode.forEach((value, index) => {

        const constFSentry: FSEntry = value.data;
        this.loggerService.debug('Ajouter un élément si ' + idElementParent + ' = ' + constFSentry.id);

        if (idElementParent === constFSentry.id) {
          this.loggerService.debug(' ************ Ajouter un élément car ' + idElementParent + ' = ' + constFSentry.id);
          // Il y a déjà des éléments dans le répertoire parent
          if (value.children != null) {
            this.loggerService.debug('Creation des éléments enfants ');
            value.data.items = value.children.length + 1;
            const valueTreeNodeAdd: TreeNode<FSEntry> = {
              data: {
                nom: nonDocument,
                auteur: auteur,
                description: description,
                kind: kind,
                items: 0,
                id: idElement,
                lienFichier: lienFichier,
              },
            };
            value.children.push(valueTreeNodeAdd);
          } else { // Il n'y a pas d'élément dans le répertoire parent
            value.data.items = 1;
            value.children = [
              {
                data: {
                  nom: nonDocument,
                  auteur: auteur,
                  description: description,
                  kind: kind,
                  items: 0,
                  id: idElement,
                  lienFichier: lienFichier,
                },
              },
            ];
          }
          return true;
        }
        this.ajouterElement(value.children, idElementParent, kind, nonDocument, auteur, description
          , idElement, lienFichier);
      });
    }

  }


}

@Component({
  selector: 'ngx-fs-icon',
  template: `
    <nb-tree-grid-row-toggle [expanded]="expanded" *ngIf="isDir(); else fileIcon">
    </nb-tree-grid-row-toggle>
    <ng-template #fileIcon>
      <nb-icon icon="file-text-outline"></nb-icon>
    </ng-template>
  `,
})
export class FsIconComponent {
  @Input() kind: string;
  @Input() expanded: boolean;

  isDir(): boolean {
    return this.kind === 'dir';
  }
}
