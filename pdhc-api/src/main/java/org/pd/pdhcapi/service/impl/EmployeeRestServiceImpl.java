package org.pd.pdhcapi.service.impl;

import org.pd.pdhc.database.dao.EmployeeDao;
import org.pd.pdhc.models.Employee;
import org.pd.pdhcapi.service.EmployeeRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeRestServiceImpl implements EmployeeRestService {

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public int createEmployee(Employee employee) {
        return employeeDao.create(employee);
    }
}
