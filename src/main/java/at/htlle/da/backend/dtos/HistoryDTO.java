package at.htlle.da.backend.dtos;

import java.time.LocalDate;

public class HistoryDTO {
    private LocalDate date;
    private Double co2Emissions;

    public HistoryDTO() {
    }

    public HistoryDTO(LocalDate date, Double co2Emissions) {
        this.date = date;
        this.co2Emissions = co2Emissions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(Double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }
}