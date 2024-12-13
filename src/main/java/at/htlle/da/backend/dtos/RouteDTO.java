package at.htlle.da.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RouteDTO {
    private String start;
    private String end;
    private String movementType;
    private Double lengthKm;

    public RouteDTO(String start, String end, String movementType, Double lengthKm, LocalDateTime timestamp) {
        this.start = start;
        this.end = end;
        this.movementType = movementType;
        this.lengthKm = lengthKm;
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
