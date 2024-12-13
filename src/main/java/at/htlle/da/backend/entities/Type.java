package at.htlle.da.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Type {
    @Id
    private String name;

    private Double gramsPerKilometer;
    @OneToMany(mappedBy = "movementType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Route> routes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGramsPerKilometer() {
        return gramsPerKilometer;
    }

    public void setGramsPerKilometer(Double gramsPerKilometer) {
        this.gramsPerKilometer = gramsPerKilometer;
    }
}
