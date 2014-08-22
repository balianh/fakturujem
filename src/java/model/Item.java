package model;
// Generated 22.8.2014 3:48:13 by Hibernate Tools 3.6.0



/**
 * Item generated by hbm2java
 */
public class Item  implements java.io.Serializable {


     private Integer iditem;
     private int accountIdaccount;
     private int rateIdrate;
     private String title;
     private int price;
     private String code;

    public Item() {
    }

	
    public Item(int accountIdaccount, int rateIdrate, String title, int price) {
        this.accountIdaccount = accountIdaccount;
        this.rateIdrate = rateIdrate;
        this.title = title;
        this.price = price;
    }
    public Item(int accountIdaccount, int rateIdrate, String title, int price, String code) {
       this.accountIdaccount = accountIdaccount;
       this.rateIdrate = rateIdrate;
       this.title = title;
       this.price = price;
       this.code = code;
    }
   
    public Integer getIditem() {
        return this.iditem;
    }
    
    public void setIditem(Integer iditem) {
        this.iditem = iditem;
    }
    public int getAccountIdaccount() {
        return this.accountIdaccount;
    }
    
    public void setAccountIdaccount(int accountIdaccount) {
        this.accountIdaccount = accountIdaccount;
    }
    public int getRateIdrate() {
        return this.rateIdrate;
    }
    
    public void setRateIdrate(int rateIdrate) {
        this.rateIdrate = rateIdrate;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPrice() {
        return this.price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }




}


