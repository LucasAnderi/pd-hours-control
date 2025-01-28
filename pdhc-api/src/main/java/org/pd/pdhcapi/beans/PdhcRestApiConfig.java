package org.pd.pdhcapi.beans;


import org.pd.pdhc.database.dao.EmployeeDao;
import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.database.dao.SquadDao;
import org.pd.pdhc.database.dao.impl.EmployeeDaoImpl;
import org.pd.pdhc.database.dao.impl.ReportDaoImpl;
import org.pd.pdhc.database.dao.impl.SquadDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdhcRestApiConfig {

    @Bean
    public ReportDao reportDao() {return new ReportDaoImpl(); }

    @Bean
    public EmployeeDao employeeDao() {return new EmployeeDaoImpl(); }

    @Bean
    public SquadDao squadDao() {return new SquadDaoImpl(); }

}
