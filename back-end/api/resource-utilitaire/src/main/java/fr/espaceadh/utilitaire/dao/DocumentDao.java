/*
 * Copyright (C) 2021 emmanuel
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
package fr.espaceadh.utilitaire.dao;

import fr.espaceadh.utilitaire.dto.DocumentDto;
import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface DocumentDao {
    
    /**
     * Recuper la list de document enfant à partir d'un document parent
     * @param idDossierParent
     * @return 
     */
    public Collection<DocumentDto> getListDocuments(long idDossierParent);
    
    
    /**
     * Créer un document
     * @param document
     * @return 
     */
    public boolean creerDocument(DocumentDto document);
    
    /**
     * Modifier un document
     * @param document
     * @return 
     */
    public boolean majDocument(DocumentDto document);
    
    /**
     * Supprimer un document
     * @param idDocument
     * @return 
     */
    public boolean suppDocument(long idDocument);
    
    
}
