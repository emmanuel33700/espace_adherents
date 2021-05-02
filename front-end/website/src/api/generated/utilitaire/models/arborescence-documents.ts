/* tslint:disable */
import { Document } from './document';
export interface ArborescenceDocuments {
  enfants?: Array<ArborescenceDocuments>;
  parent?: Document;
}
