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
package fr.espaceadh.adherents.service;

import fr.espaceadh.adherents.dao.AdherentsDAO;
import fr.espaceadh.adherents.dto.AdherentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel33700 https://github.com/emmanuel33700/espace_adherents
 */
@Service
@Transactional(readOnly = true)
public class AdherentServiceImpl implements AdherentService{
    
    @Autowired
    private Environment env;

    @Autowired
    protected AdherentsDAO adherentsDAO;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int creerAdherent(AdherentDto adherentDto) {
        long idAdherent = adherentsDAO.creerAdherent(adherentDto);
        
        if (idAdherent != 0) return 0;
        else return 1;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdherentDto recupererAdherent(Long idAdh) {
        return adherentsDAO.getAdherentByID(idAdh);
    }
    
    
}
