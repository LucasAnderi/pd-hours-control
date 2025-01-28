package org.pd.pdhc.database.dao.impl;


import org.pd.pdhc.database.connection.ConnectionFactory;
import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.models.Report;
import org.pd.pdhc.models.dto.ReportDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ReportDaoImpl implements ReportDao<Report> {
    @Override
    public List<Report> find() {
        List<Report> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM report;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Report report = new Report();

                report.setId(resultSet.getInt("id"));

                report.setDescription(resultSet.getString("description"));

                report.setSpentHours(resultSet.getInt("spenthours"));

                report.setEmployeeId(resultSet.getInt("squadid"));

                items.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return items;
    }


    @Override
    public int create(Report entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO report(description,";
        sql += " spentHours, employeeId";
        sql += " VALUES(?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getSpentHours());
            preparedStatement.setInt(4, entity.getEmployeeId());



            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return id;
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }
    }

    //retorna as horas gastas de cada membro da squad
    @Override
    public List<ReportDTO> findEspentHoursofEmployeesBySquadAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<ReportDTO> reports = new ArrayList<>();

        String sql = """
            SELECT 
                e.name AS employee_name,
                s.name AS squad_name,
                r.spentHours,
                r.createdAt
            FROM 
                Reports r
            JOIN 
                Employees e ON r.employeeId = e.id
            JOIN 
                Squads s ON e.squadId = s.id
            WHERE 
                s.id = ?
                AND r.createdAt BETWEEN ? AND ?
            ORDER BY 
                r.createdAt ASC
        """;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // Setando os parâmetros
            preparedStatement.setLong(1, squadId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReportDTO report = new ReportDTO();

                // Montando o objeto Report a partir do ResultSet
                report.setEmployeeName(resultSet.getString("employee_name"));
                report.setSquadName(resultSet.getString("squad_name"));
                report.setSpentHours(resultSet.getInt("spentHours"));
                report.setCreatedAt(resultSet.getTimestamp("createdAt").toLocalDateTime());

                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return reports;
    }
    // retorna total de horas da squad
    @Override
    public int getTotalSpentHoursBySquadAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int totalSpentHours = 0;

        String sql = """
            SELECT 
                SUM(r.spentHours) AS total_hours
            FROM 
                Reports r
            JOIN 
                Employees e ON r.employeeId = e.id
            WHERE 
                e.squadId = ?
                AND r.createdAt BETWEEN ? AND ?
        """;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // Setando os parâmetros
            preparedStatement.setInt(1, squadId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalSpentHours = resultSet.getInt("total_hours");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return totalSpentHours;
    }
}