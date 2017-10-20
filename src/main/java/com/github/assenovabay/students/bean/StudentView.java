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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static com.github.assenovabay.students.constant.Constant.*;

/**
 * @author Abay Assenov
 *         10/17/2017
 */

@ManagedBean(name = "studentView")
@SessionScoped
public class StudentView implements Serializable {

    @ManagedProperty(value = "#{student}")
    private Student student;

    private Student selectedStudent = new Student();

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

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

    //logout event, invalidate session
    public void logout() throws IOException {

        session.invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect(LOGIN_PAGE);//redirect to login page
    }

    /**
     * Shows info message the row is selected
     */
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage(SELECTED_ROW_WITH_ID, EMPTY_STR + ((Student) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage(CANCEL_SELECT_ROW_WITH_ID, EMPTY_STR + ((Student) event.getObject()).getId());
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

        FacesMessage msg = new FacesMessage(ROW_ADDED, student.getFio() + SPACE_STR + student.getFaculty());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        student.setFio(EMPTY_STR);
        student.setFaculty(EMPTY_STR);
    }

    /**
     * Updates old values
     */
    public void updateStudent() {

        if (selectedStudent != null && selectedStudent.getId() != null) {

            student.setId(selectedStudent.getId());

            new StudentDao(new DataBase().getConnection()).update(student);

            FacesMessage msg = new FacesMessage(ROW_CHANGED, OLD_VALUE
                    + selectedStudent + NEXT_LINE + NEW_VALUE + student);
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            student.setFio(EMPTY_STR);
            student.setFaculty(EMPTY_STR);

        } else {
            FacesMessage msg = new FacesMessage(TO_SELECT_ROW_FOR_CHANGE, ROW_UNSELECTED);
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

            FacesMessage msg = new FacesMessage(ROW_DELETED_ID, EMPTY_STR + selectedStudent.getId());
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            FacesMessage msg = new FacesMessage(TO_SELECT_ROW_FOR_DELETE, ROW_UNSELECTED);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
