package org.pd.pdhc.database.connection;

import java.sql.*;

public class ConnectionFactory {

    private static Connection connection = null;

    private static final String URL = "jdbc:postgresql://localhost:5432/pdhc_database";

    private static final String USER = "postgres";

    private static final String PASSWORD = "123";

    private ConnectionFactory() {

    }

    public static Connection getConnection() {
        try {

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) {
        closePreparedStatement(preparedStatement);
        closeResultSet(resultSet);
        closeConnection(connection);
    }

    public static void close(PreparedStatement preparedStatement, Connection connection) {
        closePreparedStatement(preparedStatement);
        closeConnection(connection);
    }

    private static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }
        try {
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement == null) {
            return;
        }

        try {
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
