package org.pd.pdhc.models.dto;

import java.time.LocalDateTime;

public class ReportDTO {
    private String employeeName;
    private String squadName;
    private int spentHours;
    private LocalDateTime createdAt;

    public ReportDTO() {
        this.employeeName = employeeName;
        this.squadName = squadName;
        this.spentHours = spentHours;
        this.createdAt = createdAt;
    }

    // Getters e Setters
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

    public int getSpentHours() {
        return spentHours;
    }

    public void setSpentHours(int spentHours) {
        this.spentHours = spentHours;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

    }
}
