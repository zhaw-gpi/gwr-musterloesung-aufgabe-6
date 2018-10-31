package ch.zhaw.gpi.gwr.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entitäts-Klasse für Gebäude
 * 
 * @author scep
 */
@Entity(name="Gebaeude")
public class BuildingEntity implements Serializable {
    // Eidgenössischer Gebäudeidentifikator
    @Id
    private int egid;

    // Postleitzahl
    @NotNull
    @Min(value = 1000)
    @Max(value = 9999)
    private int dplz4;

    // Strassenname
    @NotNull
    @Size(max = 60)
    private String strName;

    // Hauseingangsnummer
    @NotNull
    @Size(min = 1, max = 12)
    private String deinr;

    // GETTER und SETTER
    public int getEgid() {
        return egid;
    }

    public void setEgid(int egid) {
        this.egid = egid;
    }

    public int getDplz4() {
        return dplz4;
    }

    public void setDplz4(int dplz4) {
        this.dplz4 = dplz4;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getDeinr() {
        return deinr;
    }

    public void setDeinr(String deinr) {
        this.deinr = deinr;
    }

    
}