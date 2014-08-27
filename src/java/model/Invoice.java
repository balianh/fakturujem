package model;
// Generated 27.8.2014 20:47:12 by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Invoice generated by hbm2java
 */
public class Invoice  implements java.io.Serializable {


     private Integer id;
     private int invoicenumber;
     private int accountIdaccount;
     private int stateIdstate;
     private int methodIdmethod;
     private Date created;
     private Date due;
     private Date duzp;
     private int variablesymbol;
     private Integer constantsymbol;
     private Integer specificsymbol;
     private Integer total;

    public Invoice() {
    }

	
    public Invoice(int invoicenumber, int accountIdaccount, int stateIdstate, int methodIdmethod, Date created, Date due, Date duzp, int variablesymbol) {
        this.invoicenumber = invoicenumber;
        this.accountIdaccount = accountIdaccount;
        this.stateIdstate = stateIdstate;
        this.methodIdmethod = methodIdmethod;
        this.created = created;
        this.due = due;
        this.duzp = duzp;
        this.variablesymbol = variablesymbol;
    }
    public Invoice(int invoicenumber, int accountIdaccount, int stateIdstate, int methodIdmethod, Date created, Date due, Date duzp, int variablesymbol, Integer constantsymbol, Integer specificsymbol, Integer total) {
       this.invoicenumber = invoicenumber;
       this.accountIdaccount = accountIdaccount;
       this.stateIdstate = stateIdstate;
       this.methodIdmethod = methodIdmethod;
       this.created = created;
       this.due = due;
       this.duzp = duzp;
       this.variablesymbol = variablesymbol;
       this.constantsymbol = constantsymbol;
       this.specificsymbol = specificsymbol;
       this.total = total;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getInvoicenumber() {
        return this.invoicenumber;
    }
    
    public void setInvoicenumber(int invoicenumber) {
        this.invoicenumber = invoicenumber;
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
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }




}


