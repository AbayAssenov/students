package com.github.assenovabay.students.dao.impl;

import com.github.assenovabay.students.dao.AbstractDao;
import com.github.assenovabay.students.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 *         10/16/2017
 */

public class StudentDao extends AbstractDao<Student> {

    public StudentDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Student entity) {

        final String SQL = "INSERT INTO student (fio,faculty) VALUES (?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, entity.getFio());
            preparedStatement.setString(2, entity.getFaculty());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();//TODO remove
        }finally {
            close(connection);
        }
    }

    @Override
    public void update(Student entity) {

        final String SQL="UPDATE student SET fio=?, faculty=? WHERE id=?";

        try (PreparedStatement preparedStatement=connection.prepareStatement(SQL)){

            preparedStatement.setString(1, entity.getFio());
            preparedStatement.setString(2, entity.getFaculty());
            preparedStatement.setLong(3,entity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();//TODO remove
        }finally {
            close(connection);
        }
    }

    @Override
    public List<Student> getAll() {

        List<Student> studentList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        final String SQL = "SELECT id,fio,faculty FROM student";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Student student = new Student();

                student.setId(resultSet.getLong(1));
                student.setFio(resultSet.getString(2));
                student.setFaculty(resultSet.getString(3));

                studentList.add(student);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            close(resultSet);
            close(statement);
            close(connection);
        }

        return studentList;
    }

    @Override
    public void delete(Student student) {

        final String SQL = "DELETE FROM student WHERE id=?";

        try (   PreparedStatement preparedStatement=connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, student.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();//TODO remove
        } finally {
            close(connection);
        }
    }
}
