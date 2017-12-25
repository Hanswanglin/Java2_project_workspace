package Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import DBUtil.DBUtil;

/**
 * Created by hans on 2017/12/21.
 */
public class DBReader implements reader {
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    public ObservableList<String[]> readData() throws Exception {
        ObservableList<String[]> dbdata = FXCollections.observableArrayList();
        connection = dbutil.getConnection();
        String sql = "";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();

        return dbdata;
    }
}
