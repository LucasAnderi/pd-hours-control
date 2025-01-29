package org.pd.pdhc.database.dao;
import org.pd.pdhc.models.dto.ReportDTO;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ReportDao<T> extends BaseDao<T>{

    public List<ReportDTO> getSpentHoursByMembersAndPeriod(int squadId, String startDate, String endDate);
    public List<Map<String, Object>> getReportsBySquadAndPeriod(int squadId, String startDate, String endDate);


}
