/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author fiktivni
 */
@Named(value = "contactBean")
@Dependent
public class ContactBean {
    
    private String name;
    private String lastName;
    private String company;
    private String ICO;
    private String DIC;
    private String pcode;
    private String city;
    private String street;
    private String house;
    private String email;
    private String phone;

    /**
     * Creates a new instance of contactBean
     */
    public ContactBean() {
    }
    
    public String addNewContact(){
        return "contacts";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getICO() {
        return ICO;
    }

    public void setICO(String ICO) {
        this.ICO = ICO;
    }

    public String getDIC() {
        return DIC;
    }

    public void setDIC(String DIC) {
        this.DIC = DIC;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
