package Dao;

import DBUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This {@code DBReader} class represents a reader who read appointed data from database.
 * <p>
 * <p> DBReader is one reader in package Dao which can reader data from database, it retrieve
 * data according the restrict param.
 * <p> The {@code DBReader} include a method for
 *
 * @author Hans
 * @see
 */

public class DBReader {
    public ObservableList<String[]> readData(String fromDay, String toDay, float magnitude, String region) {
        ObservableList<String[]> dbdata = FXCollections.observableArrayList();

        String sql;
        Connection connection = null;
        try {
            DBUtil dbutil = new DBUtil();
            Statement statement = null;
            ResultSet resultSet = null;
            connection = dbutil.getConnection();
            if (region == null || region.equals("-- World Wide --")) {
                sql =
                        "SELECT id,UTC_date,latitude,longitude,depth,magnitude,region " +
                                "FROM quakes " +
                                "WHERE UTC_date >= datetime('" + fromDay + "') AND UTC_date < datetime('" + toDay + "','+1 day') " +
                                "AND magnitude >= " + magnitude + " " +
                                "ORDER BY UTC_date DESC";
            } else {
                sql =
                        "SELECT id,UTC_date,latitude,longitude,depth,magnitude,region " +
                                "FROM quakes " +
                                "WHERE UTC_date >= datetime('" + fromDay + "') AND UTC_date < datetime('" + toDay + "','+1 day') " +
                                "AND magnitude >= " + magnitude + " " +
                                "AND region = \"" + region + "\" " +
                                "ORDER BY UTC_date DESC";
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String[] DBquake = new String[7];
                DBquake[0] = resultSet.getInt("id") + "";
                DBquake[1] = resultSet.getString("UTC_date");
                DBquake[2] = resultSet.getFloat("latitude") + "";
                DBquake[3] = resultSet.getFloat("longitude") + "";
                DBquake[4] = resultSet.getInt("depth") + "";
                DBquake[5] = resultSet.getFloat("magnitude") + "";
                DBquake[6] = resultSet.getString("region");
                dbdata.add(DBquake);
            }
            dbutil.closeDBResource(connection, statement, resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close(); // <-- This is important
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dbdata;
    }
}
