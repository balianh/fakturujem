/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Rate;
import model.beans.InvoiceBean;

@FacesConverter("rateConverter")
public class RateConverter implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
        if (value != null && value.trim().length() > 0) {
            InvoiceBean service = (InvoiceBean) fc.getExternalContext().getSessionMap().get("invoiceBean");
            int i = 0;
            for (i = 0; i < service.getRates().size(); i++) {
                if (service.getRates().get(i).getId() == Integer.parseInt(value)) {
                    break;
                }
            }
            Rate sP = service.getRates().get(i);
            service.getItem().setRate(sP);
            double price = service.getItem().getPrice();
            service.getItem().setPriceWithVat(price  * ((double)sP.getValue()/(double)100) + price); 
            return sP;
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return String.valueOf(((Rate) object).getId());
        } else {
            return null;
        }
    }
}
