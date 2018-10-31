package ch.zhaw.gpi.gwr.controller;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.gpi.gwr.repositories.BuildingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Geschäftslogik im Zusammenhang mit Gebäuden
 * 
 * @author scep
 */
@Component
public class BuildingController {
    // Verdrahten des BuildingRepository
    @Autowired
    private BuildingRepository buildingRepository;
    
    /**
     * Prüft, ob Gebäude zu einer Adresse existiert
     * 
     * @param strName   Strasse
     * @param deinr     Hausnummer
     * @param dplz4     Postleitzahl
     * @return          true, falls Gebäude existiert an angegebener Adresse
     */
    public boolean addressExists(String strName, String deinr, int dplz4){
        // Zur Adresse passendes Gebäude suchen
        List<BuildingEntity> foundBuildings = buildingRepository.findByDplz4AndStrNameAndDeinr(dplz4, strName, deinr);
        
        // Prüfen, ob genau ein Gebäude existiert
        if(foundBuildings != null && foundBuildings.size() == 1){
            // Falls ja, true zurück geben
            return true;
        } else {
            // Falls nicht, false zurück geben
            return false;
        }
    }
}
