package org.pd.pdhcapi.controller;


import org.pd.pdhc.models.Squad;
import org.pd.pdhcapi.service.SquadRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/squad")
@CrossOrigin(origins = "*")
public class SquadRestController {

    @Autowired
    private SquadRestService squadService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createSquad(@RequestBody Squad squad) {
        int squadId = squadService.create(squad);
        if (squadId != -1) {
            return new ResponseEntity<>(squadId, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
