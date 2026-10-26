/* tslint:disable */
/* eslint-disable */
import { Document } from '../models/document';
export interface ArborescenceDocuments {
  enfants?: Array<ArborescenceDocuments>;
  parent?: Document;
}
