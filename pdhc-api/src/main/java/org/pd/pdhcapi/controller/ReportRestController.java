package org.pd.pdhcapi.controller;


import org.pd.pdhc.models.Report;
import org.pd.pdhcapi.service.ReportRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*")
public class ReportRestController {

    @Autowired
    private final ReportRestService reportService;

    public ReportRestController(ReportRestService reportService) {
        this.reportService = reportService;
    }



    @GetMapping("/total-hour-by-squad")
    public ResponseEntity<Integer> getTotalSpentHoursBySquad(
                                                             @RequestParam int squadId,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        int totalHours = reportService.getTotalSpentHoursBySquad(squadId, startDate, endDate);
        return ResponseEntity.ok(totalHours);
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
}


