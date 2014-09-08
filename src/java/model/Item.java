package model;
// Generated 31.8.2014 5:02:47 by Hibernate Tools 3.6.0

import controller.Queries;






/**
 * Item generated by hbm2java
 */


public class Item  implements java.io.Serializable {


     private Integer id;
     private int accountIdaccount;
     private int rateIdrate;
     private String title;
     private double price;
      private double priceWithoutVat;
     private double priceWithVat;
     private String code;
     private InvoiceHasItem invoiceHasItem;
     private Rate rate;

    public Item() {
        this.invoiceHasItem = new InvoiceHasItem();
    
       }

	
    public Item(int accountIdaccount, int rateIdrate, String title, double price) {
        this.invoiceHasItem = new InvoiceHasItem();
        this.accountIdaccount = accountIdaccount;
        this.rateIdrate = rateIdrate;
        this.title = title;
        this.price = price;
    }
    public Item(int accountIdaccount, int rateIdrate, String title, double price, String code) {
       this.invoiceHasItem = new InvoiceHasItem();
       this.accountIdaccount = accountIdaccount;
       this.rateIdrate = rateIdrate;
       this.title = title;
       this.price = price;
       this.code = code;
       
       //Count VAT variables
       this.priceWithoutVat= price;
       this.priceWithVat = priceWithoutVat *(25.0/100.0) + priceWithoutVat;
       
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;     
    }
  
    /**
     * @return the priceWithoutVat
     */
    public double getPriceWithoutVat() {
        return priceWithoutVat;
    }

    /**
     * @param priceWithoutVat the priceWithoutVat to set
     */
    public void setPriceWithoutVat(double priceWithoutVat) {
        this.priceWithoutVat = priceWithoutVat;
        this.priceWithVat = priceWithoutVat *((double)rate.getValue()/(double)100) + priceWithoutVat;
        this.price = priceWithoutVat;
    }

    
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the invoiceHasItem
     */
    public InvoiceHasItem getInvoiceHasItem() {
        return invoiceHasItem;
    }

    /**
     * @param invoiceHasItem the invoiceHasItem to set
     */
    public void setInvoiceHasItem(InvoiceHasItem invoiceHasItem) {
        this.invoiceHasItem = invoiceHasItem;
    }

    /**
     * @return the priceWithVat
     */
    public double getPriceWithVat() {
        return priceWithVat;
    }

    /**
     * @param priceWithVat the priceWithVat to set
     */
    public void setPriceWithVat(double priceWithVat) {
        this.priceWithoutVat = (priceWithVat / (100 + rate.getValue())) * 100;
        this.priceWithVat = priceWithVat;
    }

    /**
     * @return the rate
     */
    public Rate getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(Rate rate) {
        this.rate = rate;
    }

    
  



}


