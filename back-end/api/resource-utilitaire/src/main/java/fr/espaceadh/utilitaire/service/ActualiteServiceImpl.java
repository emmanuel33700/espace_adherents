package fr.espaceadh.utilitaire.service;

import fr.espaceadh.utilitaire.dao.ActualiteDao;
import fr.espaceadh.utilitaire.dto.ActualiteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class ActualiteServiceImpl implements  ActualiteService{

    @Autowired
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActualiteServiceImpl.class);

    @Autowired
    private ActualiteDao  actualiteDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean ajouterNouvelleActualite(ActualiteDto actualiteDto) {
        return actualiteDao.ajouterNouvelleActualite(actualiteDto);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Collection<ActualiteDto> recupererActualiteActive() {
        return actualiteDao.recupererActualiteActive();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean supprimerActualite(int idActualite) {
        return actualiteDao.supprimerActualite(idActualite);
    }
}
