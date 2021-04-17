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

import fr.espaceadh.utilitaire.dto.DocumentDto;
import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface DocumentService {
    
    
    /**
     * Récuépérer l'arboressence des documents
     * @param typeAuthority
     * @return 
     */
    public Collection<DocumentDto> getArboresenceDocuments(int typeAuthority);
    
    /**
     * Crééer un document
     * @param document
     * @return 
     */
    public boolean creerDocument(DocumentDto document);
    
}
