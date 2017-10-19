package com.github.assenovabay.students.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * @author Abay Assenov
 *         10/16/2017
 */

@ManagedBean
@ApplicationScoped
public class LoginRedirect {

    public void doRedirect() throws IOException{

        FacesContext.getCurrentInstance().getExternalContext().redirect("views/login.xhtml");//redirect to login page
    }

}
