package ch.zhaw.gpi.gwr.controller;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.gpi.gwr.entities.DwellingEntity;
import ch.zhaw.gpi.gwr.repositories.DwellingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Geschäftslogik im Zusammenhang mit Wohnungen
 * 
 * @author scep
 */
@Component
public class DwellingController {
    // Verdrahten des DwellingRepository
    @Autowired
    private DwellingRepository dwellingRepository;
    
    /**
     * Wohnungen eines Gebäudes zurück geben
     * 
     * @param buildingEntity    Das gesuchte Gebäude
     * @return                  null oder die Liste von Wohnungen in diesem Gebäude
     */
    public List<DwellingEntity> getWohnungenOfBuilding(BuildingEntity buildingEntity){
        // Zum Gebäude passende Wohnungen finden
        List<DwellingEntity> foundDwellings = dwellingRepository.findByBuilding(buildingEntity);
        
        // Prüfen, ob Liste vorhanden und nicht leer ist
        if(foundDwellings != null && !foundDwellings.isEmpty()){
            // Liste zurückgeben
            return foundDwellings;
        } else {
            // Null zurück geben
            return null;
        }
    }
}
