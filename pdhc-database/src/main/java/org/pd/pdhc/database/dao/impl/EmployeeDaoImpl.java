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
    public List<Employee> find() {
        List<Employee> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM employee ;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Employee employee = new Employee();

                employee.setId(resultSet.getInt("id"));

                employee.setName(resultSet.getString("name"));

                employee.setEstimatedHours(resultSet.getInt("estimatedhours"));

                employee.setSquadId(resultSet.getInt("squadid"));

                items.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return items;
    }

    @Override
    public int create(Employee entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO employee(name,";
        sql += " estimatedHours, squadId";
        sql += " VALUES(?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getEstimatedHours());
            preparedStatement.setInt(4, entity.getSquadId());



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
