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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import model.Invoice;
import model.Person;
import net.sf.jasperreports.engine.JRException;

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
    private List<Item> items;
    private List<model.Item> items2;
    private List<Person> persons;
    private String logedID = "0";
    private Invoice selectedInvoice;
    private Contact c_customer;
    private Contact c_recipient;
    
    public void printInvoice(ActionEvent actionEvent) throws IOException, JRException{
        controller.Printer.printInvoice(actionEvent,selectedInvoice, items2 , getRecipient(), getCustomer(), getRecipient());
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
        c_customer = new Contact();
        c_recipient = new Contact();
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

    public Contact getC_customer() {
        return c_customer;
    }

    public void setC_customer(Contact c_customer) {
        this.c_customer = c_customer;
    }

    public Contact getC_recipient() {
        return c_recipient;
    }

    public void setC_recipient(Contact c_recipient) {
        this.c_recipient = c_recipient;
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

    public void toggleRecipientView() {
    }

    public class Item {

        private String title;
        private Integer price;
        private Integer amount;

        public Item() {
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

            return (book.getTitle() != null && book.getTitle().equals(title)) && (book.getPrice() != null && book.getPrice().equals(price));
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

    public class Contact {

        private String name;
        private String lastName;
        private String company;
        private Integer ico;
        private String city;
        private Integer pcode;
        private String street;
        private String house;
        private String email;
        private String phone;

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

        public Integer getIco() {
            return ico;
        }

        public void setIco(Integer ico) {
            this.ico = ico;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getPcode() {
            return pcode;
        }

        public void setPcode(Integer pcode) {
            this.pcode = pcode;
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

}
