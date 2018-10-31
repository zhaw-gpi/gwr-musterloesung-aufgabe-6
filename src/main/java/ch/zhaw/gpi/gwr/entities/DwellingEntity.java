package ch.zhaw.gpi.gwr.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entitäts-Klasse für Wohnungen
 * 
 * @author scep
 */
@Entity(name="Wohnung")
public class DwellingEntity implements Serializable {
    // Wohnungsnummer
    @Id
    @NotNull
    @Size(min = 1, max = 12)
    private String whgNr;

    // Stockwerk
    @NotNull
    @Min(value = 3100)
    @Max(value = 3419)
    private int wStwk;

    // Mehrgeschossigkeit (1 = ja, 2 = nein)
    @NotNull
    @Min(value = 1)
    @Max(value = 2)
    private int wMehrG;

    // Lage auf dem Stockwerk
    @NotNull
    @Size(min = 3, max = 40)
    private String wBez;

    // Anzahl Zimmer
    @NotNull
    @Min(value = 1)
    @Max(value = 99)
    private int wAZim;

    // Referenz auf das Gebäude
    @ManyToOne
    @JoinColumn(name = "EGID")
    private BuildingEntity building;

    // GETTER und SETTER
    public String getwhgNr() {
        return this.whgNr;
    }

    public void setwhgNr(String whgNr) {
        this.whgNr = whgNr;
    }

    public int getwStwk() {
        return this.wStwk;
    }

    public void setwStwk(int wStwk) {
        this.wStwk = wStwk;
    }

    public int getwMehrG() {
        return this.wMehrG;
    }

    public void setwMehrG(int wMehrG) {
        this.wMehrG = wMehrG;
    }

    public String getwBez() {
        return this.wBez;
    }

    public void setwBez(String wBez) {
        this.wBez = wBez;
    }

    public int getwAZim() {
        return this.wAZim;
    }

    public void setwAZim(int wAZim) {
        this.wAZim = wAZim;
    }

    public BuildingEntity getGebaeude() {
        return this.building;
    }

    public void setGebaeude(BuildingEntity building) {
        this.building = building;
    }

}