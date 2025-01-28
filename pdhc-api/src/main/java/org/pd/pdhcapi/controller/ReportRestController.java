package org.pd.pdhcapi.controller;


import org.pd.pdhc.models.Report;

import org.pd.pdhc.models.dto.ReportDTO;
import org.pd.pdhcapi.service.ReportRestService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*")
public class ReportRestController {

    @Autowired
    private final ReportRestService reportService;

    public ReportRestController(ReportRestService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Integer> createReport(@RequestBody Report report) {
        int reportId = reportService.create(report);
        if (reportId != -1) {
            return new ResponseEntity<>(reportId, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/spent-hours-by-member")
    public List<ReportDTO> getSpentHoursByMembers(@RequestBody ReportDTO reportDTO) {
        return reportService.getSpentHoursByMembers(reportDTO.getSquadId(), reportDTO.getStartDate(), reportDTO.getEndDate());
    }


    @PostMapping("/total-hours-by-squad")
    public Map<String, Object> getTotalSpentHoursBySquad(@RequestBody ReportDTO reportDTO) {
        int totalSpentHours = reportService.getTotalSpentHoursBySquad(reportDTO.getSquadId(), reportDTO.getStartDate(), reportDTO.getEndDate());


        Map<String, Object> response = new HashMap<>();
        response.put("totalSpentHours", totalSpentHours);
        return response;
    }


    @PostMapping("/average-hours-by-squad")
    public Map<String, Object> getAverageSpentHoursBySquad(@RequestBody ReportDTO reportDTO) {

        return reportService.getAverageSpentHoursBySquad(
                reportDTO.getSquadId(),
                reportDTO.getStartDate().toString(),
                reportDTO.getEndDate().toString()
        );
    }

}


