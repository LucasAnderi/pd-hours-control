package org.pd.pdhc.models.dto;

import java.sql.Date;



public class ReportDTO {
    private String employeeName;
    private String squadName;
    private int totalSpentHours;
    private int squadId;
    private String startDate;
    private String endDate;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}