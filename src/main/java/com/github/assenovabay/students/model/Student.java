package com.github.assenovabay.students.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import static com.github.assenovabay.students.constant.Constant.*;

/**
 * @author Abay Assenov
 *         10/16/2017
 */

@ManagedBean
@SessionScoped
public class Student {

    private Long id;
    private String fio;
    private String faculty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return ID+getId()+FIO+getFio()+FACULTY+getFaculty();
    }
}
