package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getProperty;

public class DBUtils {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;


    //establish connection with db
    public static void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    getProperty("digitalbank.db.url"),
                    getProperty("digitalbank.db.username"),
                    getProperty("digitalbank.db.password"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //dinamically send select statementsand return list of map of all columns
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery) {
        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            //get metaData (info about your data)
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int column = 1; column <= columnCount; column++) {
                    rowMap.put(resultSetMetaData.getColumnName(column), resultSet.getObject(column));
                }
                dbResultList.add(rowMap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbResultList;
    }

    //insert and update data, return int of rows updated
    public static int runSQLUpdateQuery(String sqlQuery) {
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowsAffected;
    }

    //method to close connection
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //return the first row from the table as a list of maps
    public static List<Map<String, Object>> getFirstRowTable(String sqlQuery) {
        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery + " Limit 1");
            if (resultSet.next()) {
                Map<String, Object> mapOfRows = new HashMap<>();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    mapOfRows.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
                dbResultList.add(mapOfRows);
            }
        } catch (SQLException e) {
            System.out.println("error fetching first row: " + e.getMessage());
        }
        return dbResultList;
    }

    //return the last row from table as list of maps
    public static List<Map<String, Object>> getLastRowTable(String sqlQuery, String columnNameToOrderBy) {
        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            Map<String, Object> mapOfRows = new HashMap<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery + " order by " + columnNameToOrderBy + " desc limit 1");
            if (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    mapOfRows.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
                dbResultList.add(mapOfRows);
            }
        } catch (SQLException e) {
            System.out.println("error fetching last row: " + e.getMessage());
        }
        return dbResultList;
    }

    //return the nth row from table as list of maps
    public static List<Map<String, Object>> getAnyRowTable(String sqlQuery, int row) {
        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            Map<String, Object> mapOfRows = new HashMap<>();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery + " limit 1 offset " + (row - 1));
            if (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    mapOfRows.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
                dbResultList.add(mapOfRows);
            }
        } catch (
                SQLException e) {
            System.out.

                    println("error fetching " + row + " row: " + e.getMessage());
        }
        return dbResultList;
    }
}
