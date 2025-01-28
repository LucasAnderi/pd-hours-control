package org.pd.pdhcapi.service;


import org.pd.pdhc.models.dto.ReportDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReportRestService<T> extends BaseRestService<T> {
    public List<ReportDTO> getSpentHoursByMembers(int squadId, LocalDateTime startDate, LocalDateTime endDate);
    int getTotalSpentHoursBySquad(int squadId, LocalDateTime startDate, LocalDateTime endDate);
    Map<String, Object> getAverageSpentHoursBySquad(int squadId, String startDate, String endDate);
}
