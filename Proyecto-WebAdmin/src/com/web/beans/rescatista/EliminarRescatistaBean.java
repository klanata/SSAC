package com.web.beans.rescatista;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
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
				String imagen = a.getImagen();
				
				rescatistaBean = new RescatistaBean(id, nombre, apellido, nick, email, password, fechaNac, sexo, celular, imagen);
								 				   			    
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
		FacesMessage message = null; 
		 
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
						
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarRescatistas_?faces-redirect=true");
		}
		else	
		{
			
			boolean eliminado =false;		
			//busco al administrador con es id
			Long idRescatista = new Long(idEventoString);
			
			Rescatista a = new Rescatista();
			a = manager.obtenerRescatistaID(idRescatista);
			
			String nick = a.getNick();
			eliminado= manager.eliminarRescatista(nick);
			
			if (eliminado) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja Exitosa", "El rescatista fue dado de baja del sistema.");
				//FacesContext.getCurrentInstance().addMessage(null, message);
				
				FacesContext contexto = FacesContext.getCurrentInstance();		    						    				
				contexto.getExternalContext().getFlash().setKeepMessages(true);
				
				contexto.addMessage(null, message);	
			}										

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
