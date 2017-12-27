package DBUtil;

import java.sql.*;

/**
 * Created by hans on 2017/12/25.
 */
public class DBUtil {
    /**
     * Setting up database connection
     *
     * <p> Every time we need to connect the database, we create a {@code DBUtil}
     * object to revoke this method, the part of connection encapsulated by this way.
     *
     * @return return a connection to database
     * @throws Exception
     *         If the driveClass do not exist or appointed database file could not be found
     */
    public Connection getConnection() throws Exception {
        Connection connection;
        String diverClass = "org.sqlite.JDBC";
        connection = DriverManager.getConnection("jdbc:sqlite:" + "src/dataSource/earthquakes-1.sqlite");
        Class.forName(diverClass);
        return connection;
    }
    /**
     * Close the database link.
     *
     * <p> revoke this method to close database connection, statement, and resultSet,
     * if no resultSet generated, the param is null. This is a more safe way to close connection
     *
     * @param connection
     *        A established connection can be used as a parameter
     * @param statement
     *        Pass the parameter of a statement
     * @param resultSet
     *        pass the parameter of resultSet
     * @throws Exception
     *         if any close method revoked for Statement, ResultSet, Connection
     */
    public void closeDBResource(Connection connection, Statement statement, ResultSet resultSet) throws Exception{
        if(resultSet!=null) {
            try{
                resultSet.close();
            }finally{
                resultSet=null;
            }
        }
        if(statement!=null) {
            try{
                statement.close();
            }finally{
                statement=null;
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
