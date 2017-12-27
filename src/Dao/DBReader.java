package Dao;

import DBUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This {@code DBReader} class represents a reader who read appointed data from database.
 *
 * <p> DBReader is one reader in package Dao which can reader data from database, it retrieve
 * data according the restrict param. The Class {@code DBReader} can revoke its method to return
 * a list contain all row data satisfy the appointed select sql statement. Finally we generate an
 * {@link javafx.collections.ObservableList ObservableList} contain all string[] and return.
 * <p> for example, the return ObservableList will contain many String[], a single String[] will
 * look like this:
 * <blockquote><pre>
 *     62138 "2017-10-15 00:05:47.1" 38.35 -122.38 7 2.1 "NORTHERN CALIFORNIA"
 * </pre></blockquote>
 * <p> and the return ObservableList will contain many such String[].
 * @author Hans
 * @see javafx.collections.ObservableList
 * @see java.sql.Connection
 */

public class DBReader implements FileReader{
    /**
     * return the selected row data in database.
     *
     * <p> incoming parameter serve as filtering. In the "try-catch" statement, because in the database,
     * all region column have a specific value except the region filter is "World Wide", so we use a "if-else"
     * statement to separate these two case.
     * <ul>
     *     <li>when the region == null or region.equals("-- World Wide --").</li>
     *     <li>there is a specific region incoming.<li/>
     * </ul>
     * <p> and then we store corresponding String selected from database into {@code String[]} dbQuake in a while
     * loop, after this loop, the {@code ObservableList<String[]>} dbData generated and contain all row data.
     *
     * @param fromDay
     *        the start day for filter quake.
     * @param toDay
     *        the end day for filter quake.
     * @param magnitude
     *        the minimal value of magnitude for filter quake.
     * @param region
     *        the appointed region.
     * @return dbData contain all row data as String[] match the restrict parameter.
     *
    */
    public ObservableList<String[]> readData(String fromDay, String toDay, float magnitude, String region) {
        ObservableList<String[]> dbData = FXCollections.observableArrayList();
        Connection connection;
        try {
            String sql;
            DBUtil dbutil = new DBUtil();
            Statement statement;
            ResultSet resultSet;
            connection = dbutil.getConnection();
            if (region == null || region.equals("-- World Wide --")) {
                sql = "SELECT id,UTC_date,latitude,longitude,depth,magnitude,region " +
                        "FROM quakes " +
                        "WHERE UTC_date >= datetime('" + fromDay + "') AND UTC_date < datetime('" + toDay + "','+1 day') " +
                        "AND magnitude >= " + magnitude + " " +
                        "ORDER BY UTC_date DESC";
            } else {
                sql = "SELECT id,UTC_date,latitude,longitude,depth,magnitude,region " +
                        "FROM quakes " +
                        "WHERE UTC_date >= datetime('" + fromDay + "') AND UTC_date < datetime('" + toDay + "','+1 day') " +
                        "AND magnitude >= " + magnitude + " " +
                        "AND region = \"" + region + "\" " +
                        "ORDER BY UTC_date DESC";
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String[] dbQuake = new String[7];
                dbQuake[0] = resultSet.getInt("id") + "";
                dbQuake[1] = resultSet.getString("UTC_date");
                dbQuake[2] = resultSet.getFloat("latitude") + "";
                dbQuake[3] = resultSet.getFloat("longitude") + "";
                dbQuake[4] = resultSet.getInt("depth") + "";
                dbQuake[5] = resultSet.getFloat("magnitude") + "";
                dbQuake[6] = resultSet.getString("region");
                dbData.add(dbQuake);
            }
            dbutil.closeDBResource(connection, statement, resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbData;
    }

    @Override
    public ObservableList<String[]> readData() {
        return null;
    }
}
