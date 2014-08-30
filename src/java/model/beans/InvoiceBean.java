/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author fiktivni
 */
@ViewScoped
@Named
public class InvoiceBean implements Serializable{

    private boolean receiverIsPurchaser;
    private boolean duzpIsEqualToCreated;
    private String receiver;
    private String purchaser;
    private String code;
    private String method;
    private Date created;
    private Date due;
    private Date duzp;
    private Item item;
    private List<Item> items;

    
    @PostConstruct
    public void init() {
        item = new Item();
        items = new ArrayList<>();
    }

    public void createNew() {
        if(items.contains(item)) {
            FacesMessage msg = new FacesMessage("Dublicated", "This item has already been added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            items.add(item);
            item = new Item();
        }
    }
     
    public String reinit() {
        item = new Item();
        return null;
    }
    
    public Item getItem() {
        return item;
    }
 
    public List<Item> getItems() {
        return items;
    }
 
    
    public boolean isReceiverIsPurchaser() {
        return receiverIsPurchaser;
    }

    public void setReceiverIsPurchaser(boolean receiverIsPurchaser) {
        this.receiverIsPurchaser = receiverIsPurchaser;
    }

    public boolean isDuzpIsEqualToCreated() {
        return duzpIsEqualToCreated;
    }

    public void setDuzpIsEqualToCreated(boolean duzpIsEqualToCreated) {
        this.duzpIsEqualToCreated = duzpIsEqualToCreated;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getDuzp() {
        return duzp;
    }

    public void setDuzp(Date duzp) {
        this.duzp = duzp;
    }

    public class Item {

        private String title;
        private String price;
        private Integer amount;
        
        public Item(){
            amount = 1;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Item)) {
                return false;
            }

            Item book = (Item) obj;

            return (book.getTitle() != null && book.getTitle().equals(title)) && (book.getPrice()!= null && book.getPrice().equals(price));
        }

        @Override
        public int hashCode() {
            int hash = 1;
            if (title != null) {
                hash = hash * 31 + title.hashCode();
            }

            if (price != null) {
                hash = hash * 29 + price.hashCode();
            }

            return hash;
        }
    }

}