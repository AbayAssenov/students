package com.github.assenovabay.students.bean;

/**
 * @author Abay Assenov
 * 10/16/2017
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
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

    public void login() throws IOException{


        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {

            FacesContext.getCurrentInstance().getExternalContext().redirect("student.xhtml");

        } else {

            System.out.println("ERROR "+username+password);
        }


    }
}












//        RequestContext context = RequestContext.getCurrentInstance();
//        FacesMessage message = null;
//        boolean loggedIn = false;
//
//        if (username != null &&
//                username.equals("admin") &&
//                password != null &&
//                password.equals("admin")) {
//            loggedIn = true;
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
//        } else {
//            loggedIn = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
//        }
//
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        context.addCallbackParam("loggedIn", loggedIn);

//        DataBase d = new DataBase();
//
//        StudentDao studentDao = new StudentDao(d.getConnection());
//
//        for (Student s : studentDao.getAll()) {
//            System.out.println(s);
//        }

