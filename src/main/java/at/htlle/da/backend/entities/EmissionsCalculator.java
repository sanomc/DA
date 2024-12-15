package at.htlle.da.backend.entities;

import at.htlle.da.backend.entities.calculations.Diet;
import at.htlle.da.backend.entities.calculations.EnergyConsumption;
import at.htlle.da.backend.entities.calculations.Waste;
import jakarta.persistence.*;

@Entity
public class EmissionsCalculator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double trafficEmissions;

    private Double energyConsumption;

    @ManyToOne
    @JoinColumn(name = "diet", nullable = false)
    private Diet diet;

    private Double waste;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;

    private String calendarWeek;

    private Double totalEmissions;

    public EmissionsCalculator() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getTrafficEmissions() {
        return trafficEmissions;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getCalendarWeek() {
        return calendarWeek;
    }

    public void setCalendarWeek(String calendarWeek) {
        this.calendarWeek = calendarWeek;
    }

    public void setTrafficEmissions(Double trafficEmissions) {
        this.trafficEmissions = trafficEmissions;
    }

    public Double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public Double getWaste() {
        return waste;
    }

    public void setWaste(Double waste) {
        this.waste = waste;
    }

    public Double getTotalEmissions() {
        return totalEmissions;
    }

    public void setTotalEmissions(Double totalEmissions) {
        this.totalEmissions = totalEmissions;
    }
}
