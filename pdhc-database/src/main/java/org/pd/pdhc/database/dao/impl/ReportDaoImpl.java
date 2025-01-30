package org.pd.pdhc.database.dao.impl;

import org.pd.pdhc.database.connection.ConnectionFactory;
import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.models.Report;
import org.pd.pdhc.models.dto.ReportMemberDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportDaoImpl implements ReportDao<Report> {

    @Override
    public int create(Report entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;

        String sql = "INSERT INTO report(description, spenthours, employee_id) VALUES(?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setInt(2, entity.getSpentHours());
            preparedStatement.setInt(3, entity.getEmployeeId());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }
        return id;
    }

    @Override
    public List<ReportMemberDTO> getSpentHoursByMembersAndPeriod(int squadId, String startDate, String endDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ReportMemberDTO> resultList = new ArrayList<>();

        // Convertendo datas
        LocalDate localStartDate = LocalDate.parse(startDate);
        LocalDate localEndDate = LocalDate.parse(endDate);

        Timestamp convertedStartDate = Timestamp.valueOf(localStartDate.atStartOfDay());
        Timestamp convertedEndDate = Timestamp.valueOf(localEndDate.atTime(23, 59, 59));

        String sql = """
    SELECT e.name AS employee_name, r.description, r.spentHours, r.created_at 
    FROM report r
    JOIN employee e ON r.employee_id = e.id
    JOIN squad s ON e.squad_id = s.id
    WHERE s.id = ? AND r.created_at BETWEEN ? AND ? 
    ORDER BY e.name, r.created_at
    """;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, squadId);
            preparedStatement.setTimestamp(2, convertedStartDate);
            preparedStatement.setTimestamp(3, convertedEndDate);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReportMemberDTO report = new ReportMemberDTO();
                report.setEmployeeName(resultSet.getString("employee_name"));
                report.setDescription(resultSet.getString("description"));
                report.setTotalSpentHours(resultSet.getInt("spentHours"));
                report.setCreatedAt(resultSet.getTimestamp("created_at"));

                resultList.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return resultList;
    }
    @Override
    public List<Map<String, Object>> getReportsBySquadAndPeriod(int squadId, String startDate, String endDate) {

        List<Map<String, Object>> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // Converter String para LocalDateTime (meia-noite do dia informado) e depois para Timestamp
        LocalDate localStartDate = LocalDate.parse(startDate);
        LocalDate localEndDate = LocalDate.parse(endDate);

        Timestamp convertedStartDate = Timestamp.valueOf(localStartDate.atStartOfDay());
        Timestamp convertedEndDate = Timestamp.valueOf(localEndDate.atTime(23, 59, 59));

        String sql = """
        SELECT r.spenthours
        FROM report r
        JOIN employee e ON r.employee_id = e.id
        WHERE e.squad_id = ? AND r.created_at BETWEEN ? AND ?
    """;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, squadId);
            preparedStatement.setTimestamp(2, convertedStartDate);
            preparedStatement.setTimestamp(3, convertedEndDate);



            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("spenthours", resultSet.getInt("spenthours"));
                resultList.add(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return resultList;
    }
}