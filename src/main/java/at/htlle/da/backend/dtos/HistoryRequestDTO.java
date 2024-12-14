package at.htlle.da.backend.dtos;

import java.time.LocalDate;

public class HistoryRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;

    public HistoryRequestDTO() {
    }

    public HistoryRequestDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}