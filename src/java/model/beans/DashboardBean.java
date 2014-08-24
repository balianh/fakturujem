/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import controller.HttpSessionUtil;
import static controller.HttpSessionUtil.getSession;
import controller.queries.MainQuery;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Invoice;

 
@ManagedBean(name="dashboardBean")
@ViewScoped
public class DashboardBean implements Serializable {
    
   
     
    private static List<Invoice> invoices;
    private String logedID = "0";

 
    @PostConstruct
    public void init() {
       HttpSession s = HttpSessionUtil.getSession();
        
        if ( s != null )
            setLogedID( (s.getAttribute("logedid").toString()));
       
        invoices = MainQuery.getInvoices(Integer.parseInt(logedID));
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
    

    
}
