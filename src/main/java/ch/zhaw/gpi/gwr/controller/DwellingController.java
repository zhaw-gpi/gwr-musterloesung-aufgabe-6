package ch.zhaw.gpi.gwr.controller;

import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.gpi.gwr.entities.DwellingEntity;
import ch.zhaw.iwi.gpi.gwr.AdresseType;
import ch.zhaw.iwi.gpi.gwr.FehlerType;
import ch.zhaw.iwi.gpi.gwr.WohnungType;
import ch.zhaw.iwi.gpi.gwr.WohnungenAntwortType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ch.zhaw.gpi.gwr.repositories.BuildingRepository;
import ch.zhaw.gpi.gwr.repositories.DwellingRepository;

/**
 * Implementation für den GWR WebService für alle Requests im Zusammenhang mit Wohnungen
 * 
 * Diese Klasse enthält die eigentliche Implementation der Web Service-Operationen,
 * wobei jede Funktion einer gleich lautenden Web Service-Operation entspricht.
 * Implementiert ist momentan nur eine Methode (Wohnungen zu einer Adresse erhalten)
 * 
 * @author scep
 */
@Component
public class DwellingController {
    
    // Verdrahten der Repositories
    @Autowired
    private BuildingRepository buildingRepository;
    
    @Autowired
    private DwellingRepository dwellingRepository;
    
    /**
     * Implementation der Web Service-Operation WohnungenAnfrage
     * 
     * Sucht über eine Repository-Methode nach einem Gebäude an einer bestimmten
     * Adresse. Wird es gefunden, wird für dieses Gebäude im Repository nach 
     * Wohnungen gesucht. Wurde mindestens eine Wohnung gefunden, werden die Wohnungen
     * in einem WohnungenAntwortType zurückgegeben, ansonsten ein entsprechener
     * Fehlertyp.
     * 
     * @param address
     * @return 
     */
    public WohnungenAntwortType getDwellingsOfBuilding(AdresseType address){
        // Wohnungs-Liste-Variable deklarieren
        List<DwellingEntity> gefundeneWohnungen;
        
        // Fehler-Variablen mit Default-Werten initialisieren
        Integer fehlerTyp = 0;
        String fehlerTypText = "";

        // Datenbank-Suche nach der Kombination aus den drei Variablen-Werten durchführen
        List<BuildingEntity> gefundeneGebaeude = buildingRepository.findByDplz4AndStrNameAndDeinr(address.getDPLZ4(), address.getSTRNAME(), address.getDEINR());
        
        // Wenn genau ein Gebäude zurück gegeben wurde ...
        if(gefundeneGebaeude.size() == 1){
            // Falls ja, Wohnungen zum Gebäude auslesen
            gefundeneWohnungen = dwellingRepository.findByBuilding(gefundeneGebaeude.get(0));
            
            // Falls keine Wohnung in der Liste ist...
            if(gefundeneWohnungen.isEmpty()){
                // ... Fehler setzen
                fehlerTyp = 1;
                fehlerTypText = "Keine Wohnungen an Adresse";
            }
        } else {
            // ... ansonsten Wohnungs-Liste-Variable auf Null setzen
            gefundeneWohnungen = null;
            
            // Fehler setzen
            fehlerTyp = 2;
            fehlerTypText = "Adresse nicht gefunden";
        }

        // Ein neues AddresseExistenzType-Objekt wird instanziert
        WohnungenAntwortType wohnungenAntwort = new WohnungenAntwortType();
        
        // Prüfen, ob ein Fehler aufgetreten ist
        if(fehlerTyp > 0){
            // Ein neues Fehlerobjekt erstellen
            FehlerType fehler = new FehlerType();
            
            // FehlerTyp und FehlerText auf die entsprechenden Prozessvariablen-Inhalte gesetzt
            fehler.setFehlerTyp(fehlerTyp);
            fehler.setFehlerText(fehlerTypText);

            // Die Fehlervariable der Antwort hinzufügen
            wohnungenAntwort.setFehler(fehler);
        } else {            
            // Jede DwellingEntity in wohnungen der wohnungenAntwort hinzufügen
            for(DwellingEntity wohnungEntity : gefundeneWohnungen){
                // Für jedes Wohnungs-Entity-Objekt ein WohnungType-Objekt erzeugen
                WohnungType wohnungType = new WohnungType();
                wohnungType.setWAZIM(wohnungEntity.getwAZim());
                wohnungType.setWBEZ(wohnungEntity.getwBez());
                wohnungType.setWHGNR(wohnungEntity.getwhgNr());
                wohnungType.setWMEHRG(wohnungEntity.getwMehrG());
                wohnungType.setWSTWK(wohnungEntity.getwStwk());
                
                // Das WohnungType-Objekt der Wohnungen-Liste in wohnungAntwort hinzufügen
                wohnungenAntwort.getWohnungen().add(wohnungType);
            }
        }
        
        // Das WohnungenAntwortType-Objekt wird zurück gegeben
        return wohnungenAntwort;
    }
}