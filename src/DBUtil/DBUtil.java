package DBUtil;

import java.sql.*;

/**
 * Created by hans on 2017/12/25.
 */
public class DBUtil {
    /**
     * 建立数据库链接
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Connection connection = null;
        String diverClass = "org.sqlite.JDBC";
        connection = DriverManager.getConnection("jdbc:sqlite:src/dataSource/earthquakes-1.sqlite");
        Class.forName(diverClass);
        return connection;
    }
    /**
     * 关闭数据库链接
     * @param connection
     * @param preparedStatement
     * @param resultSet
     * @throws Exception
     */
    public void closeDBResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws Exception{
        if(resultSet!=null) {
            try{
                resultSet.close();
            }finally{
                resultSet=null;
            }
        }
        if(preparedStatement!=null) {
            try{
                preparedStatement.close();
            }finally{
                preparedStatement=null;
            }
        }
        if(connection!=null) {
            try{
                connection.close();
            }finally{
                connection=null;
            }
        }
    }
}
