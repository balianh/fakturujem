package model;
// Generated 23.8.2014 17:14:34 by Hibernate Tools 3.6.0


import controller.Queries;
import java.util.Date;


public class InvoiceView  implements java.io.Serializable {

     private Integer idInvoice;
     private Integer invoiceNumber;
     private String receiver;
     private Date created;
     private String state;
     private Integer total;

	
    public InvoiceView (int idInvoice, Date created, int idState) {
        
        Invoice invoice = Queries.getInvoiceAtId(idInvoice);
        State state = Queries.getStateAtId(idState);
        
        this.idInvoice = idInvoice;
        this.invoiceNumber = invoice.getInvoicenumber();
        this.created = created;
        this.receiver = Queries.getName(idInvoice);
        this.state = state.getTitle();
        this.total = invoice.getTotal();
        
    }

    /**
     * @return the invoiceNumber
     */
    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
   


}


