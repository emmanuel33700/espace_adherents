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
package fr.espaceadh.utilitaire.service;

import fr.espaceadh.utilitaire.dao.ListeDiffusionDAO;
import fr.espaceadh.utilitaire.dto.GroupeDiffusionDto;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author emmanuel
 */
@Service
@Transactional(readOnly = true)
public class ListeDiffusionServiceImpl implements ListeDiffusionService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(ListeDiffusionServiceImpl.class);
    
    @Autowired
    private Environment env;

    @Autowired
    protected ListeDiffusionDAO listeDiffusionDAO;
    
    @Override
    public boolean addListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        return listeDiffusionDAO.addListeDiffusion(groupeDiffusionDto);
    }

    @Override
    public boolean updateListeDiffusion(GroupeDiffusionDto groupeDiffusionDto) {
        return listeDiffusionDAO.updateListeDiffusion(groupeDiffusionDto);
    }

    @Override
    public boolean deleteListeDiffusion(long idGroupeDiffusion) {
        this.listeDiffusionDAO.deleteInscriptionListeDiffusion(idGroupeDiffusion);
        return listeDiffusionDAO.deleteListeDiffusion(idGroupeDiffusion);
    }

    @Override
    public Collection<GroupeDiffusionDto> getListeListeDiffusion() {
        return listeDiffusionDAO.getListeListeDiffusion();
    }
    
    
}
