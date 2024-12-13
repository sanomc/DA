package at.htlle.da.backend.entities;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Route {

    @Id
    private Long id;

    private String start;
    private String end;

    @ManyToOne
    @JoinColumn(name = "name")
    private Type movementType;

    @ManyToOne
    @JoinColumn(name = "email")
    private UserEntity user;

    private Double lengthKm;

    private LocalDateTime timestamp;

    private Double emissions;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Type getMovementType() {
        return movementType;
    }

    public void setMovementType(Type movementType) {
        this.movementType = movementType;
    }

    public Double getLengthKm() {
        return lengthKm;
    }

    public void setLengthKm(Double lengthKm) {
        this.lengthKm = lengthKm;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Double getEmissions() {
        return emissions;
    }

    public void setEmissions(Double emissions) {
        this.emissions = emissions;
    }
}
