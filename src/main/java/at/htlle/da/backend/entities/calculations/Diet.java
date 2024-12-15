package at.htlle.da.backend.entities.calculations;

import at.htlle.da.backend.entities.EmissionsCalculator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Diet {

    @Id
    private String dietType;
    private Double emissionsPerWeek;

    @OneToMany(mappedBy = "diet")
    private List<EmissionsCalculator> emissions;


    public Diet() {
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public Double getEmissionsPerWeek() {
        return emissionsPerWeek;
    }

    public void setEmissionsPerWeek(Double emissionsPerWeek) {
        this.emissionsPerWeek = emissionsPerWeek;
    }

    public List<EmissionsCalculator> getEmissions() {
        return emissions;
    }

    public void setEmissions(List<EmissionsCalculator> emissions) {
        this.emissions = emissions;
    }
}
