package model;
// Generated 23.8.2014 17:14:34 by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Invoice generated by hbm2java
 */
public class Invoice  implements java.io.Serializable {


     private Integer idinvoice;
     private int accountIdaccount;
     private int stateIdstate;
     private int methodIdmethod;
     private Date created;
     private Date due;
     private Date duzp;
     private int variablesymbol;
     private Integer constantsymbol;
     private Integer specificsymbol;

    public Invoice() {
    }

	
    public Invoice(int accountIdaccount, int stateIdstate, int methodIdmethod, Date created, Date due, Date duzp, int variablesymbol) {
        this.accountIdaccount = accountIdaccount;
        this.stateIdstate = stateIdstate;
        this.methodIdmethod = methodIdmethod;
        this.created = created;
        this.due = due;
        this.duzp = duzp;
        this.variablesymbol = variablesymbol;
    }
    public Invoice(int accountIdaccount, int stateIdstate, int methodIdmethod, Date created, Date due, Date duzp, int variablesymbol, Integer constantsymbol, Integer specificsymbol) {
       this.accountIdaccount = accountIdaccount;
       this.stateIdstate = stateIdstate;
       this.methodIdmethod = methodIdmethod;
       this.created = created;
       this.due = due;
       this.duzp = duzp;
       this.variablesymbol = variablesymbol;
       this.constantsymbol = constantsymbol;
       this.specificsymbol = specificsymbol;
    }
   
    public Integer getIdinvoice() {
        return this.idinvoice;
    }
    
    public void setIdinvoice(Integer idinvoice) {
        this.idinvoice = idinvoice;
    }
    public int getAccountIdaccount() {
        return this.accountIdaccount;
    }
    
    public void setAccountIdaccount(int accountIdaccount) {
        this.accountIdaccount = accountIdaccount;
    }
    public int getStateIdstate() {
        return this.stateIdstate;
    }
    
    public void setStateIdstate(int stateIdstate) {
        this.stateIdstate = stateIdstate;
    }
    public int getMethodIdmethod() {
        return this.methodIdmethod;
    }
    
    public void setMethodIdmethod(int methodIdmethod) {
        this.methodIdmethod = methodIdmethod;
    }
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getDue() {
        return this.due;
    }
    
    public void setDue(Date due) {
        this.due = due;
    }
    public Date getDuzp() {
        return this.duzp;
    }
    
    public void setDuzp(Date duzp) {
        this.duzp = duzp;
    }
    public int getVariablesymbol() {
        return this.variablesymbol;
    }
    
    public void setVariablesymbol(int variablesymbol) {
        this.variablesymbol = variablesymbol;
    }
    public Integer getConstantsymbol() {
        return this.constantsymbol;
    }
    
    public void setConstantsymbol(Integer constantsymbol) {
        this.constantsymbol = constantsymbol;
    }
    public Integer getSpecificsymbol() {
        return this.specificsymbol;
    }
    
    public void setSpecificsymbol(Integer specificsymbol) {
        this.specificsymbol = specificsymbol;
    }




}


