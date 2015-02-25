package com.web.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 

@FacesConverter("catastrofeConverter")
public class CatastrofeConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	//FuenteService service = (FuenteService) fc.getExternalContext().getApplicationMap().get("fuenteService");
            	//return service.getServiciosBean().get(Integer.parseInt(value));
                        	
            	//System.out.println("Excepción ThemeConverter " +  value);
            	
            	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idCatastrofeString", value);            	            
            	
            	return null;
                               
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la Conversion", "No hay un servicio valido."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((CatastrofeBean) object).getId());
        }
        else {
            return null;
        }
    }   
	
}
