package model;
// Generated 27.8.2014 20:47:12 by Hibernate Tools 3.6.0



/**
 * InvoiceHasItem generated by hbm2java
 */
public class InvoiceHasItem  implements java.io.Serializable {


     private Integer id;
     private int invoiceIdinvoice;
     private int itemIditem;
     private int count;

    public InvoiceHasItem() {
    }

    public InvoiceHasItem(int invoiceIdinvoice, int itemIditem, int count) {
       this.invoiceIdinvoice = invoiceIdinvoice;
       this.itemIditem = itemIditem;
       this.count = count;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getInvoiceIdinvoice() {
        return this.invoiceIdinvoice;
    }
    
    public void setInvoiceIdinvoice(int invoiceIdinvoice) {
        this.invoiceIdinvoice = invoiceIdinvoice;
    }
    public int getItemIditem() {
        return this.itemIditem;
    }
    
    public void setItemIditem(int itemIditem) {
        this.itemIditem = itemIditem;
    }
    public int getCount() {
        return this.count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }




}


