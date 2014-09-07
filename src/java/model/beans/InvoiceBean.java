/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import controller.HttpSessionUtil;
import controller.Queries;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.Invoice;
import model.InvoiceHasItem;
import model.Item;
import model.Method;
import model.Person;
import model.Rate;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "invoiceBean")
@SessionScoped
public class InvoiceBean implements Serializable {

    private boolean singleContact;
    private boolean newCustomerContact;
    private boolean newRecipientContact;
    private Person recipient;
    private Person customer;
    private String invoiceNumber;
    private String method;
    private Date created;
    private Date due;
    private Date duzp;
    private Item item;
    private InvoiceHasItem invoiceHasItem;
    private List<Item> items;
    private List<Person> persons;
    private String logedID = "0";
    private Invoice selectedInvoice;
    private UIComponent recipientFields;
    private List<Rate> rates;
 

    public void printInvoice(ActionEvent actionEvent) throws IOException, JRException {
        controller.Printer.printInvoice(actionEvent, selectedInvoice, items, getRecipient(), getCustomer(), getRecipient());
    }

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

        /*
         Get users ID from session
         */
        if (s != null) {
            setLogedID((s.getAttribute("logedid").toString()));
        }
        /*
         */

        /*
         Init variables
         */
        persons = Queries.getPersonsAtAccountId(logedID);
        rates = Queries.getRates();

        selectedInvoice = new Invoice();

        customer = new Person();
        recipient = new Person();
        setItem(new Item());
        getItem().setRate(rates.get(0));

        Calendar cal = Calendar.getInstance();
        created = cal.getTime();
        duzp = cal.getTime();
        cal.add(Calendar.DATE, 14);  
        due = cal.getTime();

        /*
         Fill invoice, recipient, customer with ID
         */
        selectedInvoice.setAccountIdaccount(Integer.parseInt(logedID));
        selectedInvoice.setStateIdstate(1);
        recipient.setAccountIdaccount(Integer.parseInt(logedID));
        customer.setAccountIdaccount(Integer.parseInt(logedID));
        /*
         */

        //to do

        items = new ArrayList<>();

    }

    public void flushForm() {

        this.setCreated(null);
        this.setInvoiceNumber(null);

    }
 
    public List<Person> completeContact(String query) {

        List<Person> filterPersons = new ArrayList<>();

        int validResultsCount = 0;

        for (Person person : persons) {
            if (person.getWholename().toLowerCase().contains(query)) {
                validResultsCount++;
                if (validResultsCount <= 10) {
                    filterPersons.add(person);
                }
            }
        }

        return filterPersons;
    }

    public List<Method> completeMethod(String query) {

        List<Method> filterPersons = new ArrayList<>();
        return filterPersons;
    }

    public List<Item> completeItemTitle(String query) {

        List<Item> filterPersons = new ArrayList<>();
        return filterPersons;
    }

    public String saveInvoice() {

        /*
         Save invoice and return her ID to variable
         */
        int savedInvoiceID = Queries.createInvoice(selectedInvoice);

        /*
         Save rercipient, customer and fill invoice with their ID and return her ID to variable
         */
        int savedRecipientID = Queries.createPerson(recipient);

        int savedCustomerID;
        if (!singleContact) {
            savedCustomerID = Queries.createPerson(customer);
        } else {
            savedCustomerID = savedRecipientID;
        }

        return "dashboard";
    }

    /*
     public void createNew() {
     if (items.contains(item)) {
     FacesMessage msg = new FacesMessage("Dublicated", "This item has already been added");
     FacesContext.getCurrentInstance().addMessage(null, msg);
     } else {
     items.add(item);
     item = new Item();
     }
     }*/
    public String reinit() {
        setItem(new Item());
        return null;
    }

  
    public List<Item> getItems() {
        return items;
    }

    public boolean isSingleContact() {
        return singleContact;
    }

    public void setSingleContact(boolean singleContact) {
        this.singleContact = singleContact;
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

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
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

    public void updateRecipientFields() {
        RequestContext.getCurrentInstance().update("form:recipientFields");
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

    public UIComponent getRecipientFields() {
        return recipientFields;
    }

    public void setRecipientFields(UIComponent recipientFields) {
        this.recipientFields = recipientFields;
    }

    /**
     * @return the newCustomerContact
     */
    public boolean isNewCustomerContact() {
        return newCustomerContact;
    }

    /**
     * @param newCustomerContact the newCustomerContact to set
     */
    public void setNewCustomerContact(boolean newCustomerContact) {
        this.newCustomerContact = newCustomerContact;
    }

    /**
     * @return the newRecipientContact
     */
    public boolean isNewRecipientContact() {
        return newRecipientContact;
    }

    /**
     * @param newRecipientContact the newRecipientContact to set
     */
    public void setNewRecipientContact(boolean newRecipientContact) {
        this.newRecipientContact = newRecipientContact;
    }

    /**
     * @return the rates
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * @param rates the rates to set
     */
    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

 
}
