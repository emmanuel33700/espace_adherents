/*
 * Copyright (C) 2021 emmanuel33700 https://github.com/emmanuel33700/espace_adherents
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

import fr.espaceadh.utilitaire.dto.SaisonDto;
import java.util.Collection;

/**
 *
 * @author emmanuel
 */
public interface SaisonDao {
    
        
    /**
     * Récupérer la liste des saisons
     * @return 
     */
    Collection<SaisonDto> getLstSaison();
    
    
    /**
     * Récupérer la saison courante
     * @return 
     */
    SaisonDto getSaisonCourant();
    
    /**
     * Positionner la saison courante
     * @param idSaison
     * @return 
     */
    boolean setSaisonCourante(int idSaison);
    
    /**
     * retirer la saison courante
     * @return 
     */
    boolean retirerSaisonCourante();
    
}
