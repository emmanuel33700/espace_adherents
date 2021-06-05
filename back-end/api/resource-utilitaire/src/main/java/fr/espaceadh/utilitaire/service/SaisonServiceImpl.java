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
package fr.espaceadh.utilitaire.service;

import fr.espaceadh.utilitaire.dao.SaisonDao;
import fr.espaceadh.utilitaire.dto.SaisonDto;
import java.util.ArrayList;
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
public class SaisonServiceImpl implements SaisonService {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SaisonServiceImpl.class);
    
    @Autowired
    private Environment env;
    
    @Autowired
    protected SaisonDao saisonDao;
        

    /**
     * Récupérer la liste (partiel car uniquement celles proche de la saison courante) des saisons
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<SaisonDto> getLstSaison() {
        
        SaisonDto saisonCourantDto = this.saisonDao.getSaisonCourant();
        
        Collection<SaisonDto> lstSaisonComplete = this.saisonDao.getLstSaison();
        
        Collection<SaisonDto> lstSaisonFiltre = new ArrayList<>();
        for(SaisonDto dto : lstSaisonComplete) {
            // on récupère uniquement les saisons proches de la saison courante
            if( (dto.getId() >  saisonCourantDto.getId() -3 ) 
                    || (dto.getId() <  saisonCourantDto.getId() +3 ) 
                    ) {
                lstSaisonFiltre.add(dto);
            }
        }
        
        return lstSaisonFiltre;
    }

    /**
     * Recupérer la saison courante
     * @return 
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public SaisonDto getSaisonCourant() {
        return this.saisonDao.getSaisonCourant();
    }

    /**
     * CHanger de saison courante
     * @param idSaison
     * @return 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean changerSaisonCourant(int idSaison) {
        this.saisonDao.retirerSaisonCourante();
        this.saisonDao.setSaisonCourante(idSaison);
        // TODO demander la désactivation des comptes lors du changement de saison
        
        return true;
    }
    
}
