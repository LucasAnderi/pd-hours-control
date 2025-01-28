package org.pd.pdhcapi.controller;


import org.pd.pdhcapi.service.ReportRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
}


