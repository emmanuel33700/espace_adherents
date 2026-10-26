package fr.espaceadh.utilitaire.dao;

import fr.espaceadh.utilitaire.dto.ActualiteDto;

import java.util.Collection;

public interface ActualiteDao {

    /**
     * Ajouter une nouvelle actualité
     * @param actualiteDto
     * @return
     */
    boolean ajouterNouvelleActualite(ActualiteDto actualiteDto);

    /**
     * Recupérer la liste des actualité toujours active
     * @return
     */
    Collection<ActualiteDto> recupererActualiteActive();


    boolean supprimerActualite (int idActualite);
}
