package org.pd.pdhc.database.dao;
import org.pd.pdhc.models.dto.ReportDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReportDao<T> extends BaseDao<T>{

    public List<ReportDTO> getSpentHoursByMembersAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate);
    public List<Map<String, Object>> getTotalSpentHoursBySquadAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate);


}
