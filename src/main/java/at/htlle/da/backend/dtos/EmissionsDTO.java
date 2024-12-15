package at.htlle.da.backend.dtos;

import at.htlle.da.backend.entities.calculations.EnergyConsumption;
import at.htlle.da.backend.entities.calculations.Waste;

import java.util.List;
import java.util.Map;

public class EmissionsDTO {
    private Double trafficEmissions;
    private String diet;
    private Map<String, Double> waste;
    private Map<String, Double> energy;

    public EmissionsDTO() {
    }

    public Double getTrafficEmissions() {
        return trafficEmissions;
    }

    public void setTrafficEmissions(Double trafficEmissions) {
        this.trafficEmissions = trafficEmissions;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Map<String, Double> getWaste() {
        return waste;
    }

    public void setWaste(Map<String, Double> waste) {
        this.waste = waste;
    }

    public Map<String, Double> getEnergy() {
        return energy;
    }

    public void setEnergy(Map<String, Double> energy) {
        this.energy = energy;
    }
}
