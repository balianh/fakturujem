/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import controller.queries.AccountQuery;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import controller.LoginUtil;

 
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String message, email, password;
    
    public LoginBean(){
   
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String loginProject() {
        
        boolean result = AccountQuery.login(email, password);
        if (result) {
            // Vezme HTTP sessionu a uloží do ní uživatele (možno sloučit s ID v AccountQuery)
            HttpSession session = LoginUtil.getSession();
            session.setAttribute("email", email);
 
            return "dashboard";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Zadal jste špatné přihlašovací informace!",
                    "Prosím zkuste to znovu!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
            return "index";
        }
    }
 
    public String logout() {
      HttpSession session = LoginUtil.getSession();
      session.invalidate();
      return "index";
   }
}