package at.htlle.da.backend.dtos;

public class EmissionsHistoryDTO {
    private String calendarWeek;
    private Double emissions;

    public EmissionsHistoryDTO(String calendarWeek, Double emissions) {
        this.calendarWeek = calendarWeek;
        this.emissions = emissions;
    }

    public String getCalendarWeek() {
        return calendarWeek;
    }

    public void setCalendarWeek(String calendarWeek) {
        this.calendarWeek = calendarWeek;
    }

    public Double getEmissions() {
        return emissions;
    }

    public void setEmissions(Double emissions) {
        this.emissions = emissions;
    }
}
