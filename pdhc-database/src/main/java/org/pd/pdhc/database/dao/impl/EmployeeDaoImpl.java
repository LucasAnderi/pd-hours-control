package org.pd.pdhc.database.dao.impl;

import org.pd.pdhc.database.connection.ConnectionFactory;
import org.pd.pdhc.database.dao.EmployeeDao;
import org.pd.pdhc.models.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EmployeeDaoImpl implements EmployeeDao<Employee>{

    @Override
    public int create(Employee entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO employee (name, estimatedHours, squadId) VALUES (?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getEstimatedHours());
            preparedStatement.setInt(3, entity.getSquadId());



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


}
