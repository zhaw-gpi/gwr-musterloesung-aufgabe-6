package ch.zhaw.gpi.gwr.endpoint;

import ch.zhaw.gpi.gwr.controller.BuildingController;
import ch.zhaw.iwi.gpi.gwr.AddresseExistenzType;
import ch.zhaw.iwi.gpi.gwr.AdresseType;
import ch.zhaw.iwi.gpi.gwr.WohnungenAntwortType;
import ch.zhaw.gpi.gwr.controller.DwellingController;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Endpoint-Definition
 * Hier wird der eigentliche Web Service definert, was zur Laufzeit im
 * Hintergrund dazu führt, dass ein WSDL generiert wird und dass die Web service-
 * Operationen als Methoden bereit gestellt werden. Werden diese Operationen
 * aufgerufen, wird die jeweilige Methode aufgerufen, welche allerdings nur die
 * eigentliche Implementation aufruft, die in separaten Controllern stattfindet
 * Die @WebService-JAX-WS-Annotation definiert diese Klasse als Web Service-Klasse
 */
@WebService(name="Gebäude- und Wohnungsregister-Service", portName="GebaeudeUndWohnungsregisterServicePort", targetNamespace = "http://www.iwi.zhaw.ch/gpi/gwr")
public class GebaeudeUndWohnungsRegisterServiceEndpoint {
    
    /**
     * Die eigentliche Implementation soll getrennt von der Schnittstellen- 
     * Definition sein. Dies wird mit separaten Controller-Klassen gelöst.
     * Über @Autowired wird das zur Laufzeit verfügbare Controller-Objekt
     * in diesem Service als Variable eingefügt (Dependency Injection).
     */    
    @Autowired
    private BuildingController buildingController;
    
    @Autowired
    private DwellingController dwellingController;

    /**
     * Definition der Webservice-Operation Adresspruefung
     * Diese erwartet drei Adressparameter und gibt die Existenz als 
     * AddresseExistenzType zurück
     * Die @WebMethod-JAX-WS-Annotation definiert eine Webservice-Operation
     * @param address
     * @WebParam definiert die Bezeichnung der Nachrichten-Input-Parameter
     * @return 
     */
    @WebMethod(operationName="adressPruefungOperation")
    @WebResult(name="AdresspruefungAntwort")
    public AddresseExistenzType adresspruefung(@WebParam(name = "AdresspruefungAnfrage") AdresseType address) {
        // Ruft die Methode checkAddressExistence des Controllers auf und
        // gibt deren Resultat zurück an den SOAP-Client
        return buildingController.checkAddressExistence(address);
    }
    
    /**
     * Definition der Webservice-Operation WohnungenAnfrage
     * Diese erwartet drei Adressparameter und gibt entweder einen Fehler oder 
     * eine Liste von Wohnungen zurück
     * Die @WebMethod-JAX-WS-Annotation definiert eine Webservice-Operation
     * @param address
     * @WebParam definiert die Bezeichnung der Nachrichten-Input-Parameter
     * @return 
     */
    @WebMethod(operationName="wohnungenInGebaeudeOperation")
    @WebResult(name="WohnungenAntwort")
    public WohnungenAntwortType wohnungenAnfrage(@WebParam(name = "WohnungenAnfrage") AdresseType address) {
        // Ruft die Methode getDwellingsOfBuilding des Controllers auf und
        // gibt deren Resultat zurück an den SOAP-Client
        return dwellingController.getDwellingsOfBuilding(address);
    }

}
