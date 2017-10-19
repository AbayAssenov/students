package com.github.assenovabay.students.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Abay Assenov
 *         10/17/2017
 */
public abstract class AbstractDao<T> implements InterfaceDao<T> {

    protected Connection connection = null;

    public AbstractDao(Connection connection) {

    this.connection=connection;
    }

    protected void close(Connection connection) {
        try {
            if(connection!=null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close(ResultSet resultSet) {
        try {
            if(resultSet!=null)
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void close(Statement statement) {
        try {
            if(statement!=null)
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
