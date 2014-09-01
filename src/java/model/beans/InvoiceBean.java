/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import controller.HttpSessionUtil;
import controller.Queries;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Invoice;
import model.Person;


@ManagedBean(name = "invoiceBean")
@SessionScoped
public class InvoiceBean implements Serializable{

    private boolean receiverIsPurchaser;
    private boolean duzpIsEqualToCreated;
    private Person receiver;
    private String purchaser;
    private String invoiceNumber;
    private String method;
    private Date created;
    private Date due;
    private Date duzp;
    private Item item;
    private List<Item> items;
    private List<Person> persons;
    private String logedID = "0";
    private Invoice selectedInvoice;

    /**
     * @return the selectedInvoice
     */
    public Invoice getSelectedInvoice() {
        return selectedInvoice;
    }

    /**
     * @param aSelectedInvoice the selectedInvoice to set
     */
    public void setSelectedInvoice(Invoice aSelectedInvoice) {
        selectedInvoice = aSelectedInvoice;
        created = selectedInvoice.getCreated();
        setInvoiceNumber(Integer.toString(selectedInvoice.getInvoicenumber()));
    }


    
    @PostConstruct
    public void init() {
        HttpSession s = HttpSessionUtil.getSession();

        if (s != null) {
            setLogedID((s.getAttribute("logedid").toString()));
        }
        item = new Item();
        items = new ArrayList<>();
        getPersons();
       
    }
    
       public void flushForm() {
    
        this.setCreated(null);
        this.setInvoiceNumber(null);
        
    }

    
    public List<Person> completeReceiver(String query) {
        
        List<Person> filterPersons = new ArrayList<>();
        
        int validResultsCount = 0;            
       
        for(int i = 0; i < persons.size(); i++) {

            Person receiver = persons.get(i);
            
            if(receiver.getWholename().toLowerCase().contains(query)) {
                validResultsCount++;           
                if (validResultsCount <= 10)
                filterPersons.add(receiver);
            }
        }
         
        return filterPersons;
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

    
    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
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

    /**
     * @return the persons
     */
    public List<Person> getPersons() {
        persons = Queries.getPersonsAtAccountId(logedID);
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * @return the logedID
     */
    public String getLogedID() {
        return logedID;
    }

    /**
     * @param logedID the logedID to set
     */
    public void setLogedID(String logedID) {
        this.logedID = logedID;
    }

    /**
     * @return the receiver
     */
    public Person getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the invoiceNumber
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

 
    public class Item {

        private String title;
        private Integer price;
        private Integer amount;
        
        public Item(){
            amount = 1;
            price = 0;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
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