package com.github.assenovabay.students.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Abay Assenov
 *         10/16/2017
 */
public class DataSourceConnection {

//    @Resource(name = "jdbc/student")
    private DataSource ds;
//    private static final String DATASOURCE_NAME = "jdbc/student";
//
//    static{
//        try {
//            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//            ds = (DataSource) envContext.lookup(DATASOURCE_NAME);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }

    public DataSourceConnection() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/student");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {

        return ds.getConnection();
    }

    public void close(Connection connection) throws SQLException {

        connection.close();
    }
}
