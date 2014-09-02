/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import controller.HttpSessionUtil;
import controller.Queries;
import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.Invoice;
import model.InvoiceHasItem;
import model.Item;
import model.Person;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "invoiceBean")
@SessionScoped
public class InvoiceBean implements Serializable {

    private boolean singleContact;
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
  
    
    public void printInvoice(ActionEvent actionEvent) throws IOException, JRException{
        controller.Printer.printInvoice(actionEvent,selectedInvoice, items , getRecipient(), getCustomer(), getRecipient());
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

        if (s != null) {
            setLogedID((s.getAttribute("logedid").toString()));
        }
        item = new Item();
        customer = new Person();
        recipient = new Person();
        items = new ArrayList<>();
        getPersons();

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

    public void createNew() {
        if (items.contains(item)) {
            FacesMessage msg = new FacesMessage("Dublicated", "This item has already been added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
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

}
