package org.pd.pdhcapi.service;


import org.pd.pdhc.models.dto.ReportDTO;
import org.pd.pdhc.models.dto.ReportMemberDTO;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ReportRestService<T> extends BaseRestService<T> {
    public List<ReportMemberDTO> getSpentHoursByMembers(int squadId, String startDate, String endDate);
    Map<String, Object> getTotalSpentHoursBySquad(int squadId, String startDate, String endDate);
    Map<String, Object> getAverageSpentHoursBySquad(int squadId, String startDate, String endDate);
}
