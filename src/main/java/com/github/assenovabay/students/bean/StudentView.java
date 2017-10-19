package com.github.assenovabay.students.bean;

import com.github.assenovabay.students.dao.impl.StudentDao;
import com.github.assenovabay.students.database.DataBase;
import com.github.assenovabay.students.model.Student;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * @author Abay Assenov
 *         10/17/2017
 */

@ManagedBean(name = "studentView")
@SessionScoped
public class StudentView implements Serializable {

    @ManagedProperty(value = "#{student}")
    private Student student;

    private Student selectedStudent=new Student();


    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    /**
     * Shows info message the row is selected
     */
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Выбрана запись с ID ", "" + ((Student) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Отмена выбора записи с ID ", "" + ((Student) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Takes student from database
     */
    public List<Student> getStudents() {

        return new StudentDao(new DataBase().getConnection()).getAll();
    }

    /**
     * Adds new record into database
     */
    public void addStudent() {

        new StudentDao(new DataBase().getConnection()).create(student);

        FacesMessage msg = new FacesMessage("Запись добавленна", student.getFio()+" "+student.getFaculty());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        student.setFio("");
        student.setFaculty("");
    }

    /**Updates old values*/
    public void updateStudent() {

        if (selectedStudent != null && selectedStudent.getId() != null) {

            student.setId(selectedStudent.getId());

            new StudentDao(new DataBase().getConnection()).update(student);

            FacesMessage msg = new FacesMessage("Запись изменнена: ", "Старое значение:"
                    + selectedStudent +"\n"+"Новое значение:" + student);
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            student.setFio("");
            student.setFaculty("");

        } else {
            FacesMessage msg = new FacesMessage("Выбирете запись для изменения", "Запись не выбрана!!!");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Deletes selected row
     */
    public void deleteStudent() {

        if (selectedStudent != null && selectedStudent.getId() != null) {

            new StudentDao(new DataBase().getConnection()).delete(selectedStudent);

            FacesMessage msg = new FacesMessage("Запись удаленна: ID ", "" + selectedStudent.getId());
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            FacesMessage msg = new FacesMessage("Выбирете запись для удаления", "Запись не выбрана!!!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
