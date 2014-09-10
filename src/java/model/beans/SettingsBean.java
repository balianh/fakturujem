/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import controller.HttpSessionUtil;
import controller.Queries;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Person;

/**
 *
 * @author fiktivni
 */
@Named(value = "settingsBean")
@Dependent
public class SettingsBean {

    private Person user;
    private Account account;

    /**
     * Creates a new instance of SettingsBean
     */
    public SettingsBean() {
    }

    @PostConstruct
    public void init() {
        String sessionId = getUserID();
        this.user = Queries.getPerson(sessionId, true);
        this.account = Queries.getAccount(sessionId);
    }

    public void updateUser() {
        Queries.updatePerson(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uloženo"));
    }

    public String deleteUser() {
        Queries.deleteAccount(account);
        Queries.deletePerson(user);
        return "index";
    }

    public String getUserID() {
        HttpSession s = HttpSessionUtil.getSession();
        String userID = "";
        if (s != null) {
            userID = (s.getAttribute("logedid").toString());
        }
        return userID;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

}
