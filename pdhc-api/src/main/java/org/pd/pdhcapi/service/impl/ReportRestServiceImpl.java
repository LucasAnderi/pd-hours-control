package org.pd.pdhcapi.service.impl;

import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.models.dto.ReportDTO;
import org.pd.pdhcapi.service.ReportRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportRestServiceImpl implements ReportRestService{

    @Autowired
    private ReportDao reportDao;

    @Override
    public int create(Object entity) {
        return reportDao.create(entity);
    }
    public List<ReportDTO> getSpentHoursByMembers(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        return reportDao.getSpentHoursByMembersAndPeriod(squadId, startDate, endDate);
    }

    @Override
    public int getTotalSpentHoursBySquad(int squadId, LocalDateTime startDate, LocalDateTime endDate) {

        LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00");
        LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59");

        // Chama o DAO para recuperar os dados
        List<Map<String, Object>> totalHoursData = reportDao.getTotalSpentHoursBySquadAndPeriod(squadId, start, end);

        // Calcula o número de dias corridos entre as datas
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate()) + 1;

        // Soma as horas gastas
        double totalSpentHours = totalHoursData.stream()
                .mapToDouble(row -> (double) row.get("totalSpentHours"))
                .sum();

        return (int) totalSpentHours;
    }


    @Override
    public Map<String, Object> getAverageSpentHoursBySquad(int squadId, String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate + "T00:00:00");
        LocalDateTime end = LocalDateTime.parse(endDate + "T23:59:59");

        // Chama o DAO para recuperar os dados
        List<Map<String, Object>> totalHoursData = reportDao.getTotalSpentHoursBySquadAndPeriod(squadId, start, end);

        // Calcula o número de dias corridos entre as datas
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate()) + 1;

        // Soma as horas gastas
        double totalSpentHours = totalHoursData.stream()
                .mapToDouble(row -> (double) row.get("totalSpentHours"))
                .sum();

        // Calcula a média
        double averageSpentHours = totalSpentHours / daysBetween;


        Map<String, Object> result = new HashMap<>();
        result.put("squadId", squadId);
        result.put("averageSpentHoursPerDay", averageSpentHours);
        result.put("totalSpentHours", totalSpentHours);
        result.put("daysInPeriod", daysBetween);

        return result;
    }



}





