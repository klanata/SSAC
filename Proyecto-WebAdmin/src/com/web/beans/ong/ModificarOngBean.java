package com.web.beans.ong;

import java.io.Serializable;



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



import com.core.data.entites.Ong;
import com.core.service.negocio.remote.OngEBR;



@ManagedBean(name="modificarOngBean")
@RequestScoped
public class ModificarOngBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{ongBean}")
	 private OngBean ongBean;
	 public OngBean getOngBean() {
		return ongBean;
	}

	public void setOngBean(OngBean ongBean) {
		this.ongBean = ongBean;
	}
	/*-------------------------------------------------------------------------------------*/
	@PostConstruct
    public void init() {
    	
		OngEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (OngEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }			
					
		try{			
			
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoOngEditar");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    					
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listarOng?faces-redirect=true");
    		}
    		else	
    		{
			
    			
    			//busco al administrador con es id
    			Long id = new Long(idEventoString);
				Ong a = new Ong();
				
				a = manager.buscarOngPorID_EB(id);
				//cargamos datos a mostrar
						
				//Long id;
				String nombre = a.getNombre();
				String direccion = a.getDireccion();
				String descripcion = a.getDescripcion();
				String citioWeb =  a.getsitioWeb();
				String email = a.getEmail();
				String telefono = a.getTelefono();
				String imagen = a.getImagen();
								
				ongBean =  new OngBean(id, nombre, direccion, telefono, email, citioWeb, descripcion,imagen);								      	
		     
		     }				
			
    	}catch (Exception excep){
    		System.out.println("Excepci�n al listar los administradores: " + excep.getMessage());      		 			       	           	
    	}  					
		
		
    }    
	/*---------------------------------------------------------------------------------------------------------*/
	public void editar(){
			
		OngEBR manager = null;				
		Context context = null;
		FacesMessage message = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (OngEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }	
					
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoOngEditar");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarOng?faces-redirect=true");
		}
		else	
		{
			String nombre = ongBean.getNombre();
			String direccion = ongBean.getDireccion();
			String descripcion = ongBean.getDescripcion();
			String citioWeb =  ongBean.getCitioWeb();
			String email = ongBean.getEmail();
			String telefono = ongBean.getTelefono();
			String imagen = ongBean.getImagen();
			manager.modificarOng(nombre, direccion, descripcion, email, telefono, citioWeb,imagen);
			
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación Exitosa", "La Ong fue modificada de forma exitosa.");
			//FacesContext.getCurrentInstance().addMessage(null, message);
			
			FacesContext contexto = FacesContext.getCurrentInstance();		    						    				
			contexto.getExternalContext().getFlash().setKeepMessages(true);
			
			contexto.addMessage(null, message);

			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarOng?faces-redirect=true");
		}
	
	}
	
	public void cancelar(){		
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listarOng?faces-redirect=true");			
	}
		


}
