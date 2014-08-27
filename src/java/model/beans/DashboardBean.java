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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Invoice;
import model.InvoiceView;

@ManagedBean(name = "dashboardBean")
@ViewScoped
public class DashboardBean implements Serializable {

    private static List<Invoice> invoices;
    private static List<InvoiceView> invoiceViews;
    private String logedID = "0";

    @PostConstruct
    public void init() {
        HttpSession s = HttpSessionUtil.getSession();

        if (s != null) {
            setLogedID((s.getAttribute("logedid").toString()));
        }

        invoices = Queries.getInvoices(Integer.parseInt(logedID));
        invoiceViews = new ArrayList<>(invoices.size()); 
        
        for (Invoice invoice : invoices) {
            InvoiceView iw = new InvoiceView(invoice.getId(),invoice.getCreated() , 0);
            invoiceViews.add(iw);
        }

    }

    public List<Invoice> getInvoices() {
        return invoices;
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
     * @return the invoiceViews
     */
    public List<InvoiceView> getInvoiceViews() {
        return invoiceViews;
    }

    /**
     * @param aInvoiceViews the invoiceViews to set
     */
    public void setInvoiceViews(List<InvoiceView> aInvoiceViews) {
        invoiceViews = aInvoiceViews;
    }

}
