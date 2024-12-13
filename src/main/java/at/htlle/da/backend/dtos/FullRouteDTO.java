package at.htlle.da.backend.dtos;

import java.time.LocalDateTime;

public class FullRouteDTO {
    private Long id;
    private String start;
    private String end;
    private String movementType;
    private Double lengthKm;
    private Double emissions;

    public FullRouteDTO(Long id, String start, String end, String movementType, Double lengthKm, LocalDateTime timestamp, Double emissions) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.movementType = movementType;
        this.lengthKm = lengthKm;
        this.emissions = emissions;
    }

    public Double getEmissions() {
        return emissions;
    }

    public void setEmissions(Double emissions) {
        this.emissions = emissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public Double getLengthKm() {
        return lengthKm;
    }

    public void setLengthKm(Double lengthKm) {
        this.lengthKm = lengthKm;
    }
}
