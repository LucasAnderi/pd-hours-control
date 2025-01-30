package org.pd.pdhcapi.service.impl;

import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.models.dto.ReportDTO;
import org.pd.pdhc.models.dto.ReportMemberDTO;
import org.pd.pdhcapi.service.ReportRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    @Override
    public List<ReportMemberDTO> getSpentHoursByMembers(int squadId, String startDate, String endDate) {
        return reportDao.getSpentHoursByMembersAndPeriod(squadId, startDate, endDate);
    }

    @Override
    public Map<String, Object> getTotalSpentHoursBySquad(int squadId, String startDate, String endDate) {

        // Chama o DAO para obter os reports no período
        List<Map<String, Object>> reports = reportDao.getReportsBySquadAndPeriod(squadId, startDate, endDate);

        // Somar todas as horas gastas
        int totalSpentHours = reports.stream()
                .mapToInt(row -> (int) row.get("spenthours"))
                .sum();

        // Preparar o resultado
        Map<String, Object> result = new HashMap<>();
        result.put("totalSpentHours", totalSpentHours);

        return result;
    }

    @Override
    public Map<String, Object> getAverageSpentHoursBySquad(int squadId, String startDate, String endDate) {
        // Formatar as datas em string para comparação

        // Buscar todos os reports no período
        List<Map<String, Object>> reports = reportDao.getReportsBySquadAndPeriod(squadId, startDate, endDate);

        // Converter as datas para LocalDate para calcular numero de dias entre startData e
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        // Calcular o número de dias corridos entre as datas
        long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate) + 1;

        // Somar todas as horas gastas
        int totalSpentHours = reports.stream()
                .mapToInt(row -> (int) row.get("spenthours"))
                .sum();

        // Calcular a média de horas gastas por dia
        double averageSpentHours = daysBetween > 0 ? (double) totalSpentHours / daysBetween : 0;

        // Preparar o resultado
        Map<String, Object> result = new HashMap<>();
        result.put("squadId", squadId);
        result.put("totalSpentHours", totalSpentHours);
        result.put("averageSpentHoursPerDay", averageSpentHours);
        result.put("daysInPeriod", daysBetween);

        System.out.println("Total spent hours: " + totalSpentHours);
        return result;
    }



}





