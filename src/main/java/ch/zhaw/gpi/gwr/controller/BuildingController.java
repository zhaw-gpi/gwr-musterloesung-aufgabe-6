package ch.zhaw.gpi.gwr.controller;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.iwi.gpi.gwr.AddresseExistenzType;
import ch.zhaw.iwi.gpi.gwr.AdresseType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ch.zhaw.gpi.gwr.repositories.BuildingRepository;

/**
 * Implementation für den GWR WebService für alle Requests im Zusammenhang mit Gebäuden
 * 
 * Diese Klasse enthält die eigentliche Implementation der Web Service-Operationen,
 * wobei jede Funktion einer gleich lautenden Web Service-Operation entspricht.
 * Implementiert ist momentan nur eine Methode (Adressprüfung)
 * 
 * @author scep
 */
@Component
public class BuildingController {
    
    @Autowired
    private BuildingRepository gwrRepository;
    
    /**
     * Implementation der Web Service-Operation Adresspruefung
     * 
     * Sucht über eine Repository-Methode nach einem Gebäude an einer bestimmten
     * Adresse. Wird es gefunden, wird einem neuen AdresseExistenzType der Wert
     * true zugewiesen, ansonsten false.
     * 
     * @param address
     * @return 
     */
    public AddresseExistenzType checkAddressExistence(AdresseType address){
        // Datenbank-Suche nach der Kombination aus den drei Variablen-Werten durchführen
        List<BuildingEntity> gefundeneGebaeude = gwrRepository.findByDplz4AndStrNameAndDeinr(address.getDPLZ4(), address.getSTRNAME(), address.getDEINR());
        
        // Exists-Variable setzen in Abhängigkeit davon, ob ein Gebäude gefunden wurde
        Boolean exists;
        exists = gefundeneGebaeude.size() == 1;
        
        // Ein neues AddresseExistenzType-Objekt wird instanziert
        AddresseExistenzType addresseExistenz = new AddresseExistenzType();
        
        // Die Eigenschaft EXISTS wird auf den entsprechenden Prozessvariablen-Inhalt gesetzt 
        addresseExistenz.setEXISTS(exists);
        
        // Das AddresseExistenzType-Objekt wird zurück gegeben
        return addresseExistenz;
    }
}
