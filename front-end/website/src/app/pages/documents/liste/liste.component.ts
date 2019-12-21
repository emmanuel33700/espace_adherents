import { Component, Input } from '@angular/core';
import { NbSortDirection, NbSortRequest, NbTreeGridDataSource, NbTreeGridDataSourceBuilder } from '@nebular/theme';

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
}

@Component({
  selector: 'ngx-tree-grid',
  templateUrl: './liste.component.html',
  styleUrls: ['./liste.component.scss'],
})
export class ListeComponent {
  customColumn = 'nom';
  defaultColumns = [ 'auteur', 'description', 'items' ];
  allColumns = [ this.customColumn, ...this.defaultColumns ];

  dataSource: NbTreeGridDataSource<FSEntry>;

  sortColumn: string;
  sortDirection: NbSortDirection = NbSortDirection.NONE;

  constructor(private dataSourceBuilder: NbTreeGridDataSourceBuilder<FSEntry>) {
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

  private data: TreeNode<FSEntry>[] = [
    {
      data: { nom: 'Projects', auteur: 'Dupond jean', items: 5, description: 'Administration de lasso', kind: 'dir' },
      children: [
        {
          data: {nom: 'Année 2018', auteur: 'Dupond jean', items: 5, description: 'Admin de lasso', kind: 'dir' },
          children: [
            {data: {nom: 'doc1.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
            {data: {nom: 'doc2.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
            {data: {nom: 'doc3.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
            {data: {nom: 'doc4.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
          ],
        },
        {
          data: {nom: 'Année 2019', auteur: 'Dupond jean', items: 5, description: 'Admin de lasso', kind: 'dir' },
          children: [
            {data: {nom: 'doc1.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
            {data: {nom: 'doc2.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
            {data: {nom: 'doc3.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
            {data: {nom: 'doc4.pdf', auteur: 'Dupond jean', description: 'Administration de lasso', kind: 'doc'}},
          ],
        },
      ],
    },
    {
      data: { nom: 'Ateliers', auteur: 'Dupond jean', items: 5, description: 'Atelier cadran  solaire', kind: 'dir' },
      children: [
        { data: { nom: 'doc5.pdf', auteur: 'Dupond jean', description: 'Atelier cadran  solaire', kind: 'doc' } },
        { data: { nom: 'doc6.pdf', auteur: 'Dupond jean',  description: 'Atelier cadran  solaire', kind: 'doc'  } },
        { data: { nom: 'doc7.pdf', auteur: 'Dupond jean',  description: 'Atelier cadran  solaire', kind: 'doc' } },
        { data: { nom: 'doc8.pdf', auteur: 'Dupond jean',  description: 'Atelier cadran  solaire', kind: 'doc' } },
      ],
    },
  ];

  getShowOn(index: number) {
    const minWithForMultipleColumns = 400;
    const nextColumnStep = 100;
    return minWithForMultipleColumns + (nextColumnStep * index);
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
