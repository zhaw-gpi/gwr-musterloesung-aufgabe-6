package ch.zhaw.gpi.gwr;

import ch.zhaw.gpi.gwr.controller.BuildingController;
import ch.zhaw.gpi.gwr.controller.DwellingController;
import ch.zhaw.gpi.gwr.entities.BuildingEntity;
import ch.zhaw.gpi.gwr.entities.DwellingEntity;
import ch.zhaw.gpi.gwr.repositories.BuildingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author scep
 */
@Component
public class TempCommandLineRunner implements CommandLineRunner {
    
    @Autowired
    private BuildingController buildingController;
    
    @Autowired BuildingRepository buildingRepository;
    
    @Autowired
    private DwellingController dwellingController;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(buildingController.addressExists("Bahnhofstrasse", "1", 1595));
        System.out.println(buildingController.addressExists("Bahnhofstrasse", "100", 1595));
        
        BuildingEntity buildingEntity = buildingRepository.findById(1).get();
        
        List<DwellingEntity> dwellings = dwellingController.getWohnungenOfBuilding(buildingEntity);
        
        System.out.println(dwellings.get(0).getwhgNr());
    }
    
}
