package controller;
 
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Person;
import model.beans.InvoiceBean;
 

 
@FacesConverter("personConverter")
public class PersonConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
      
        if(!value.contains("null")) {
             InvoiceBean service = (InvoiceBean) fc.getExternalContext().getSessionMap().get("invoiceBean");
           int i;
           for (i =0; i < service.getPersons().size()-1; i++ ){
                 if(service.getPersons().get(i).getId() == Integer.parseInt(value)) break;              
            }
           Person sP = service.getPersons().get(i);
           
           Person personToEdit = new Person(sP.getAccountIdaccount(), sP.getName(),
                   sP.getLastname(), sP.getCompany(), sP.getStreet(), sP.getHouse(),
                   sP.getCity(), sP.getPcode(), sP.getState(), Boolean.FALSE,
                   sP.getPhone(), sP.getEmail(), sP.getFax(), sP.getWww(), 
                   sP.getBankaccount(), sP.getIco(), sP.getDic());
            
           return sP;
        
        }
        else {
            return new Person();
        }
        
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Person) object).getId());
        }
        else {
            return null;
        }
    }   
}   