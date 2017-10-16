package com.github.assenovabay.students.entiry;

/**
 * @author Abay Assenov
 *         10/16/2017
 */
public class Student {

    private int id;
    private String fio;
    private String faculty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "ID "+getId()+" FIO "+getFio()+" FACULTY "+getFaculty();
    }
}
