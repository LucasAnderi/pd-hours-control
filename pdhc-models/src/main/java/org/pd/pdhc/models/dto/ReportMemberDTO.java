package org.pd.pdhc.models.dto;

import java.sql.Timestamp;


public class ReportMemberDTO {
    private String employeeName;
    private String description;
    private int totalSpentHours;
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalSpentHours() {
        return totalSpentHours;
    }

    public void setTotalSpentHours(int totalSpentHours) {
        this.totalSpentHours = totalSpentHours;
    }


}