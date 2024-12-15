package at.htlle.da.backend.dtos;

public class LeaderboardDTO {
    private String username;
    private double totalEmissions;

    public LeaderboardDTO(String username, double totalEmissions) {
        this.username = username;
        this.totalEmissions = totalEmissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalEmissions() {
        return totalEmissions;
    }

    public void setTotalEmissions(double totalEmissions) {
        this.totalEmissions = totalEmissions;
    }
}