package org.pd.pdhcapi.service.impl;

import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.models.Report;
import org.pd.pdhc.models.dto.ReportDTO;
import org.pd.pdhcapi.service.ReportRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportRestServiceImpl implements ReportRestService<Report>{

    @Autowired
    private ReportDao reportDao;

    @Override
    public int create(Report entity) {
        return reportDao.create(entity);
    }

    @Override
    public List<ReportDTO> findHoursSpentByEmployeewithSquadIdandPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        return reportDao.findEspentHoursofEmployeesBySquadAndPeriod(squadId, startDate, endDate);
    }

    @Override
    public int getTotalSpentHoursBySquad(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        return reportDao.getTotalSpentHoursBySquadAndPeriod( squadId ,startDate,endDate);
    }



}
