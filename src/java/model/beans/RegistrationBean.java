/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author fiktivni
 */
@ManagedBean(name = "registrationBean")
@SessionScoped
public class RegistrationBean {
    
    @Size(max=45)
    private String email;
    
    @Size(min=6,max=45)
    private String password1;
    
    private String password2;
    
    @Digits(integer=8,fraction=0) @Min(0) @Max(99999999)
    private Integer ICO;
    
    @Size(max=45)
    private String city;
    
    @Digits(integer=5,fraction=0) @Min(0) @Max(99999)
    private Integer pcode;
    
    @Size(max=45)
    private String street;
    
    @AssertTrue
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
    
}
