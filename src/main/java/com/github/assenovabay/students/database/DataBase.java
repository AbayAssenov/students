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
public class DataBase {

    private DataSource ds;

    public DataBase() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/student");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){

        Connection connection=null;

        try {
            connection=ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
