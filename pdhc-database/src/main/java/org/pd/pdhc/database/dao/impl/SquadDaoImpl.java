package org.pd.pdhc.database.dao.impl;

import org.pd.pdhc.database.connection.ConnectionFactory;
import org.pd.pdhc.database.dao.SquadDao;
import org.pd.pdhc.models.Squad;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class SquadDaoImpl implements SquadDao<Squad>{
    @Override
    public List<Squad> find() {
        List<Squad> items = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM squad;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Squad squad = new Squad();

                squad.setId(resultSet.getInt("id"));

                squad.setName(resultSet.getString("description"));

                items.add(squad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return items;
    }

    @Override
    public int create(Squad entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

        String sql = "INSERT INTO squad(name) VALUES (?,?)";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(2, entity.getName());




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

