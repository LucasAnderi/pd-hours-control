package org.pd.pdhc.database.dao.impl;


import org.pd.pdhc.database.connection.ConnectionFactory;
import org.pd.pdhc.database.dao.ReportDao;
import org.pd.pdhc.models.Report;
import org.pd.pdhc.models.dto.ReportDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
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

        // Corrigindo a query SQL
        String sql = "INSERT INTO report(description, totalspentHours, employeeId) VALUES(?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();

            // Desativando auto-commit para controle manual da transação
            connection.setAutoCommit(false);

            // Preparando a query e solicitando a recuperação da chave gerada
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Setando os valores nos placeholders
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setInt(2, entity.getSpentHours());
            preparedStatement.setInt(3, entity.getEmployeeId());

            // Executando a query
            preparedStatement.execute();

            // Recuperando a chave gerada automaticamente (ID)
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            // Confirmando a transação
            connection.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();

            // Revertendo a transação em caso de erro
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return id;
        } finally {
            // Fechando os recursos (preparedStatement, connection, resultSet)
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }
    }

    @Override
    public List<ReportDTO> getSpentHoursByMembersAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<ReportDTO> resultList = new ArrayList<>();

        String sql = """
        SELECT 
            e.name AS employee_name,
            SUM(r.spentHours) AS totalSpentHours
        FROM 
            report r
        JOIN 
            employee e ON r.employeeId = e.id
        JOIN 
            squad s ON e.squadId = s.id
        WHERE 
            s.id = ?
            AND r.createdAt BETWEEN ? AND ?
        GROUP BY 
            e.name
        ORDER BY 
            e.name
    """;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setLong(1, squadId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReportDTO employeeHours = new ReportDTO();
                employeeHours.setEmployeeName(resultSet.getString("employee_name"));
                employeeHours.setTotalspentHours(resultSet.getInt("totalSpentHours"));

                resultList.add(employeeHours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return resultList;
    }

    public List<Map<String, Object>> getTotalSpentHoursBySquadAndPeriod(int squadId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = """
            SELECT SUM(r.totalspentHours) AS totalSpentHours
            FROM report r
            JOIN employee e ON r.employeeId = e.id
            JOIN squad s ON e.squadId = s.id
            WHERE s.id = ?
            AND r.createdAt BETWEEN ? AND ?
            GROUP BY r.createdAt
        """;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, squadId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(endDate));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("totalSpentHours", resultSet.getDouble("totalSpentHours"));
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