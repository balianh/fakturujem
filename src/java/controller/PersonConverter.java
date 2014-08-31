package controller;
 
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Person;
 

 
@FacesConverter("personConverter")
public class PersonConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
      // TO DO
            return null;
        
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