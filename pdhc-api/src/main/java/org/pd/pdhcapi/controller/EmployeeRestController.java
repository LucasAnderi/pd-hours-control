package org.pd.pdhcapi.controller;

import org.pd.pdhc.models.Employee;
import org.pd.pdhc.models.Squad;
import org.pd.pdhcapi.service.EmployeeRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeRestController {

    @Autowired
    private final EmployeeRestService employeeService;

    public EmployeeRestController(EmployeeRestService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createEmployee(@RequestBody Employee employee) {

        int employeeId = employeeService.create(employee);
        if(employeeId != -1){
            return new ResponseEntity<>(employeeId,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}