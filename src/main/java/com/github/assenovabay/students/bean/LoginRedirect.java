package com.github.assenovabay.students.bean;

import com.github.assenovabay.students.constant.Constant;

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

        FacesContext.getCurrentInstance().getExternalContext().redirect(Constant.PATH_PAGE_LOGIN);//redirect to login page
    }

}
