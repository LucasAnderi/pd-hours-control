package org.pd.pdhc.database.dao;

import org.pd.pdhc.models.dto.ReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportDao<T> extends BaseDao<T>{
    public List<ReportDTO> findEspentHoursofEmployeesBySquadAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate);
    public int getTotalSpentHoursBySquadAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate);


}
