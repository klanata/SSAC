package com.web.beans.rescatista;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;
import com.core.data.entites.Rescatista;
import com.core.service.negocio.remote.RescatistaEBR;


@ManagedBean(name="eliminarRescatista")
@RequestScoped
public class EliminarRescatistaBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @ManagedProperty("#{rescatistaBean}")
	   private RescatistaBean rescatistaBean = new RescatistaBean();
		
		
		public RescatistaBean getRescatistaBean() {
		return rescatistaBean;
		}
		public void setRescatistaBean(RescatistaBean rescatistaBean) {
			this.rescatistaBean = rescatistaBean;
		}
	
	@PostConstruct
    public void init() {
    	
		RescatistaEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }		
					
		try{			
			
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoRescatistaEliminar");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    			System.out.println("No existe administrador "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listarAdministrador?faces-redirect=true");
    		}
    		else	
    		{
			
    			
    			//busco al administrador con es id
    			Long id = new Long(idEventoString);
				Rescatista a = new Rescatista();
				a= manager.obtenerRescatistaID(id);
				
				//cargamos datos a mostrar
						
				//Long id;
				String nombre = a.getNombre();
				String apellido= a.getApellido();
				String nick =a.getNick();
				String email = a.getEmail();
				String password = a.getPassword() ;
				Date fechaNac = a.getFechaNac();
				String sexo = a.getSexo() ;
				String celular = a.getCelular();
				
				rescatistaBean = new RescatistaBean(id, nombre, apellido, nick, email, password, fechaNac, sexo, celular);
				
				 
				  System.out.println("obtengo rescatista ");      	
		     
		     }
				
			
    	}catch (Exception excep){
    		System.out.println("Excepcion al obtener rescatista: " + excep.getMessage());      		 			       	           	
    	}  					
		
		
    }    
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------------------------------*/
	public void eliminar(){
			
		RescatistaEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }			
					
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoRescatistaEliminar");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe rescatista"); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarRescatistas_?faces-redirect=true");
		}
		else	
		{
			//busco al administrador con es id
			Long idRescatista = new Long(idEventoString);
			
			Rescatista a = new Rescatista();
			a = manager.obtenerRescatistaID(idRescatista);
			
			manager.eliminarRescatista(a.getNick());
			


			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarRescatistas_?faces-redirect=true");
		}
	
	}
	/*----------------------------------------------------------------------------------------------------------------------------------*/
	public void cancelar(){
	


			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarRescatistas_?faces-redirect=true");
		
	
	}
}
