package org.pd.pdhcapi.service;

import org.pd.pdhc.models.Employee;

public interface EmployeeRestService<T> extends BaseRestService<T> {
    public int createEmployee(Employee employee);
}
