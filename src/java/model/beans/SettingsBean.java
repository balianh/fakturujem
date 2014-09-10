/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import controller.HttpSessionUtil;
import controller.Queries;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class SettingsBean implements Serializable {

    private Person user;
    private Account account;
    private Person originalUser;

    @PostConstruct
    public void init() {
        String sessionId = getUserID();
        setUser(Queries.getPerson(sessionId, true));
        setAccount(Queries.getAccount(sessionId));
        setOriginalUser(getUser());
    }

    public void updateUser() {
        Queries.updatePerson(getUser());
        setOriginalUser(getUser());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uloženo"));
    }

    public String deleteUser() {
        Queries.deleteAccount(getAccount());
        Queries.deletePerson(getOriginalUser());
        return "index";
    }

    private String getUserID() {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Person getOriginalUser() {
        return originalUser;
    }

    public void setOriginalUser(Person originalUser) {
        this.originalUser = originalUser;
    }

}
