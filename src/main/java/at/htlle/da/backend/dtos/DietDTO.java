package at.htlle.da.backend.dtos;

public class DietDTO {
    private String dietType;
    private Double emissionsPerWeek;

    public DietDTO(String dietType, Double emissionsPerWeek) {
        this.dietType = dietType;
        this.emissionsPerWeek = emissionsPerWeek;
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
}
