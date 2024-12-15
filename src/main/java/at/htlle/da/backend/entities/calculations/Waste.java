package at.htlle.da.backend.entities.calculations;

import at.htlle.da.backend.entities.EmissionsCalculator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Entity
public class Waste {
    @Id
    private String type;
    private Double emissionsPerKg;

    public Waste() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getEmissionsPerKg() {
        return emissionsPerKg;
    }

    public void setEmissionsPerKg(Double emissionsPerKg) {
        this.emissionsPerKg = emissionsPerKg;
    }

}
