package org.pd.pdhc.models.dto;

import java.time.LocalDateTime;

public class ReportDTO {
    private String employeeName;
    private String squadName;
    private int totalSpentHours;
    private int squadId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReportDTO() {}


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public int getTotalSpentHours() {
        return totalSpentHours;
    }

    public void setTotalSpentHours(int totalspentHours) {
        this.totalSpentHours = totalspentHours;
    }


    // Getters e Setters dos Novos Campos
    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}