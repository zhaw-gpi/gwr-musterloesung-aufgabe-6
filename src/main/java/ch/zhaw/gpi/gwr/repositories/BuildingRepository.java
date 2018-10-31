package ch.zhaw.gpi.gwr.repositories;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository-Klasse für Gebäude-Entität, welche CRUD-Operationen auf die dahinterliegende Datenbank kapselt
 * 
 * @author scep
 */
public interface BuildingRepository extends JpaRepository<BuildingEntity, Integer>{
    
    /**
     * Methode, um alle Gebäude an einer Adresse zu erhalten
     * 
     * @param dplz4     PLZ
     * @param strName   Strasse
     * @param deinr     Hausnummer
     * @return          Liste aller Gebäude an einer Adresse (normalerweise eines)
     */
    public List<BuildingEntity> findByDplz4AndStrNameAndDeinr(Integer dplz4, String strName, String deinr);
}

