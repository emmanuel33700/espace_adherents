package fr.espaceadh.utilitaire.dao;

import fr.espaceadh.utilitaire.dto.ActualiteDto;
import fr.espaceadh.utilitaire.dto.EvenementSyntheseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

@Repository
public class ActualiteDaoImpl extends JdbcDaoSupport implements ActualiteDao{


    private static final Logger LOGGER = LoggerFactory.getLogger(ActualiteDaoImpl.class);

    @Autowired
    public ActualiteDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    @Override
    public boolean ajouterNouvelleActualite(ActualiteDto actualiteDto) {
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO public.t_information(  ");
        query.append("           id_information, information, date_enregistrement, fk_id_adherent, fk_id_type_authority)");
        query.append(" VALUES (?, ?, now(), ?, ?) ");


        int nbCreation;
        nbCreation = this.getJdbcTemplate().update(query.toString(),
                this.recupererIdInformation(),
                actualiteDto.getLibelleActualite(),
                actualiteDto.getIdAdherent(),
                actualiteDto.getIdAuthority()
        );

        if (nbCreation == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la creation d'une actualité : nombre de ligne crée {} ", nbCreation);
        }
        return false;
    }

    /**
     * recupérer un nouvel id information
     * @return id infroamtion
     */
    private long recupererIdInformation(){
        StringBuilder query = new StringBuilder()
                .append(" select nextval('seq_information') ");

        final long idActualite = this.getJdbcTemplate().queryForObject(query.toString(),  Long.class);

        return  idActualite;
    }

    @Override
    public Collection<ActualiteDto> recupererActualiteActive() {

        Collection<ActualiteDto> lst;

        StringBuilder query = new StringBuilder();
        query.append(" select id_information, information, date_enregistrement, fk_id_adherent, fk_id_type_authority ");
        query.append(" from t_information");

        lst = this.getJdbcTemplate().query(query.toString(), new ActualiteMapper());


        return lst;

    }

    @Override
    public boolean supprimerActualite(int idActualite) {
        StringBuilder query = new StringBuilder();
        query.append(" DELETE FROM t_information WHERE id_information = ? ");

        int nbSupression;
        nbSupression = this.getJdbcTemplate().update(query.toString(),
                idActualite
        );

        if (nbSupression == 1) {
            return true;
        } else {
            LOGGER.error("Erreur lors de la suppression d'une actualité ", idActualite);
        }
        return false;
    }

    public static final class ActualiteMapper implements RowMapper<ActualiteDto> {

        @Override
        public ActualiteDto mapRow(ResultSet resultSet, int i) throws SQLException {
            ActualiteDto dto = new ActualiteDto();

            dto.setId(resultSet.getInt("id_information"));
            dto.setLibelleActualite(resultSet.getString("information"));
            dto.setDateCreation(new Date(resultSet.getTimestamp("date_enregistrement").getTime()));
            dto.setIdAdherent(resultSet.getInt("fk_id_adherent"));
            dto.setIdAuthority(resultSet.getInt("fk_id_type_authority"));

            return dto;


        }
    }
    }
