package com.github.assenovabay.students.dao.impl;

import com.github.assenovabay.students.dao.InterfaceDao;
import com.github.assenovabay.students.entiry.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 *         10/16/2017
 */
public class StudentDao implements InterfaceDao<Student> {

    private Connection connection=null;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Student entity) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public Student getById(Long idEntity) {
        return null;
    }

    @Override
    public List<Student> getAll() {

        List<Student> studentList= new ArrayList<>();

        Statement statement=null;
        ResultSet resultSet=null;

       final String SQL="SELECT id,fio,faculty FROM student";

        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(SQL);

            while (resultSet.next()){
                Student student=new Student();

                student.setId(resultSet.getInt(1));
                student.setFio(resultSet.getString(2));
                student.setFaculty(resultSet.getString(3));

                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return studentList;
    }

    @Override
    public void delete(Student user) {

    }
}
