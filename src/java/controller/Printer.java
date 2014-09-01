/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.org.apache.xerces.internal.xs.XSConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Invoice;
import model.Item;
import model.Person;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author Michal
 */
public class Printer {

    private static List<String> listOfUser = new ArrayList<String>(5);
    public static JasperPrint jasperPrint;

    public static void printInvoice(ActionEvent actionEvent, Invoice invoice, List<model.Item> items, Person owner, Person payer, Person receiver) throws JRException, IOException {

        Map map;
        listOfUser.add("test");

        int totalPrice = 0;
        Collection col = new java.util.Vector();
        // Vypsání položek

        for (int i = 1; i < 5; i++) {
          //  model.Item item = items.get(i);
            map = new HashMap();
          /*  map.put("itemName", item.getTitle() );
            map.put("itemCount", "TO DO");
            map.put("itemMjPrice", item.getPrice());
            map.put("itemTotalPrice", "TO DO");
            map.put("itemDPH", item.getRateIdrate());
            map.put("itemDPHPrice", "TO DO");
            map.put("itemPriceWithDPH", "TO DO");
            */
            map.put("itemName", "TO DO");
            map.put("itemCount", "TO DO");
            map.put("itemMjPrice", "TO DO");
            map.put("itemTotalPrice", "TO DO");
            map.put("itemDPH", "TO DO");
            map.put("itemDPHPrice", "TO DO");
            map.put("itemPriceWithDPH", "TO DO");
            col.add(map);
        }
       

        Map properties = new HashMap();
        properties.put("customerName", "čeněk");
        properties.put("companyName", "TO DO");
        properties.put("madeBy", "TO DO");
        properties.put("companyIc", "TO DO");
        properties.put("companyDic", "TO DO");
        properties.put("date", "TO DO");
        properties.put("totalPrice", "TO DO");
        properties.put("number", "TO DO");

        totalPrice = totalPrice + 1;

        JRDataSource dataSource = new JRMapCollectionDataSource(col);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/invoice.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, properties, dataSource);
        

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=invoice.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
  
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();

    }
}
