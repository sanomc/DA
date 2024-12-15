package at.htlle.da.backend.entities.calculations;

import at.htlle.da.backend.entities.EmissionsCalculator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class EnergyConsumption {

    @Id
    private String type;
    private Double emissionsPerKwh;


    public EnergyConsumption() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getEmissionsPerKwh() {
        return emissionsPerKwh;
    }

    public void setEmissionsPerKwh(Double emissionsPerKwh) {
        this.emissionsPerKwh = emissionsPerKwh;
    }

}
