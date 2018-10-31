package ch.zhaw.gpi.gwr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse für die GWR-SpringBoot-Applikation
 * 
 * @author scep
 */
@SpringBootApplication
public class GwrApplication {
    public static void main(String[] args){
        SpringApplication.run(GwrApplication.class, args);
    }
}
