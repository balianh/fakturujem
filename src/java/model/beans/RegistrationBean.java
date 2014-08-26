/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 *
 * @author fiktivni
 */
@ManagedBean
public class RegistrationBean {
     
    private String email;
    @Size(min=6,max=64)
    private String password1;
    private String password2;
    @Digits(integer=8,fraction=0)
    @Size(min=8,max=8)
    private Integer ICO;
    private String city;
    private Integer pcode;
    private String street;
    private boolean agreed;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Integer getICO() {
        return ICO;
    }

    public void setICO(Integer ICO) {
        this.ICO = ICO;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPcode() {
        return pcode;
    }

    public void setPcode(Integer PSC) {
        this.pcode = PSC;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }
 
    public void done(ActionEvent actionEvent) {
        addMessage("Vidím, že jsi kliknul na tlačítko Registrovat.");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
