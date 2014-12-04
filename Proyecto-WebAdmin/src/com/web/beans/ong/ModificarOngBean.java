package com.web.beans.ong;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Administrador;
import com.core.data.entites.Ong;
import com.core.service.negocio.remote.AdministradorEBR;
import com.core.service.negocio.remote.OngEBR;
import com.web.beans.administrador.AdministradorBean;


@ManagedBean(name="modificarOngBean")
@SessionScoped
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
    			System.out.println("No existe ong "); 			
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
				String citioWeb =  a.getCitioWeb();
				String email = a.getEmail();
				String telefono = a.getTelefono();
				
				
				ongBean =  new OngBean(id, nombre, direccion, telefono, email, citioWeb, descripcion);
				 
				  System.out.println("obtengo ong ");      	
		     
		     }
				
			
    	}catch (Exception excep){
    		System.out.println("Excepci�n al listar los administradores: " + excep.getMessage());      		 			       	           	
    	}  					
		
		
    }    
	/*---------------------------------------------------------------------------------------------------------*/
	public void editar(){
			
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
					
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoOngEditar");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe ong"); 			
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
			manager.modificarOng(nombre, direccion, descripcion, email, telefono, citioWeb);
			
			


			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarOng?faces-redirect=true");
		}
	
	}


}
