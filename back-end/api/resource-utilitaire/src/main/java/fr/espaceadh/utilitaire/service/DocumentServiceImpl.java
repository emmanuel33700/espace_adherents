/*
 * Copyright (C) 2019 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.espaceadh.utilitaire.service;

import fr.espaceadh.utilitaire.dao.DocumentDao;
import fr.espaceadh.utilitaire.dto.DocumentDto;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel
 */
@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);
    
    @Autowired
    private Environment env;
        
        
    @Autowired
    protected DocumentDao documentDao;

    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<DocumentDto> getArboresenceDocuments(int typeAuthority) {
        
        Collection<DocumentDto> lstDocument = documentDao.getListDocuments(-1);
        
        if (lstDocument != null || !lstDocument.isEmpty()) {
            this.constructionArborescence(lstDocument);
            
            this.calculNiveauArborescence(lstDocument, 0);
            
        }
        
        return lstDocument;
        
    }

    /**
     * Construction de l'arboresence. Attention, nodification de l'objet passé en parametre
     * @param arboresence
     * @return 
     */
    private boolean constructionArborescence(Collection<DocumentDto> arboresence)  {

         if (arboresence != null && !arboresence.isEmpty()) {
             
             for (DocumentDto doc : arboresence) {
                 LOGGER.debug("Recherche des dossiers fils du dossier {}", doc.getIdDocument());
                 
                 Collection<DocumentDto> lstDocumentTmp = documentDao.getListDocuments(doc.getIdDocument());
                 
                 if (lstDocumentTmp != null && !lstDocumentTmp.isEmpty()) {
                     doc.setDocumentsFils(lstDocumentTmp);
                     
                     LOGGER.debug("On Descend dans l'arboréscence ");
                     this.constructionArborescence(lstDocumentTmp);
                 }
                 
             }
             LOGGER.debug("On remonte dans l'arborescence ");
         }
         else {
             LOGGER.debug("Pas de document présent");
             return false;
         }
         
         return true;

    }
    
    /**
     * Calculer le niveau des dossiers dans l'arboresence
     * @param arboresence
     * @param niveau
     * @return 
     */
    private boolean calculNiveauArborescence(Collection<DocumentDto> arboresence, int niveau) {

        if (arboresence != null && !arboresence.isEmpty()) {
            for (DocumentDto doc : arboresence) {
                doc.setNiveau(niveau);
                
                if (doc.getDocumentsFils() != null && !doc.getDocumentsFils().isEmpty()){
                    this.calculNiveauArborescence(doc.getDocumentsFils(), niveau+1);
                }
            }
            LOGGER.debug("On remonte dans l'arborescence ");
        }
         else {
             LOGGER.debug("Pas de document présent ");
             return false;
         }        
        return true;
    }
    
    /**
     * Créer un document
     * @param document
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean creerDocument(DocumentDto document) {
        return documentDao.creerDocument(document);
    }
    
}
