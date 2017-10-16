package com.github.assenovabay.students.bean;

/**
 * @author Abay Assenov
 * 10/16/2017
 */

import com.github.assenovabay.students.dao.impl.StudentDao;
import com.github.assenovabay.students.database.DataSourceConnection;
import com.github.assenovabay.students.entiry.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class UserLoginView {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() throws SQLException {

        DataSourceConnection d = new DataSourceConnection();

        StudentDao studentDao = new StudentDao(d.getConnection());

        for (Student s : studentDao.getAll()) {
            System.out.println(s);
        }

//        RequestContext context = RequestContext.getCurrentInstance();
//        FacesMessage message = null;
//        boolean loggedIn = false;
//
//        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
//            loggedIn = true;
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
//        } else {
//            loggedIn = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
//        }
//
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        context.addCallbackParam("loggedIn", loggedIn);
    }
}
