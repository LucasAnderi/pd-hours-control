package org.pd.pdhcapi.service;

import org.pd.pdhc.models.dto.ReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRestService<T> extends BaseRestService<T> {
    public List<ReportDTO> findHoursSpentByEmployeewithSquadIdandPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate );
    public int getTotalSpentHoursBySquad(int squadId, LocalDateTime startDate, LocalDateTime endDate);
}
