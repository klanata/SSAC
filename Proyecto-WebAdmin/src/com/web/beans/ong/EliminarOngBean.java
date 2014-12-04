package com.web.beans.ong;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;
import com.core.data.entites.Ong;

import com.core.service.negocio.remote.OngEBR;




@ManagedBean(name="eliminarOngBean")
@SessionScoped
public class EliminarOngBean implements Serializable{
	
	

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{ongBean}")
	 private OngBean ongBean;
	 public OngBean getOngBean() {
		return ongBean;
	}

	public void setOngBean(OngBean ongBean) {
		this.ongBean = ongBean;
	}
		
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
						
						
						
				
				String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoOngEliminar");
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
					
				
	    					
			
			
	    }    
		/*--------------------------------------------------------------------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------------------------------*/
		public void eliminar(){
				
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
						
						
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoOngEliminar");
			if ((idEventoString == null) || (idEventoString == ""))
			{	
				System.out.println("No existe ong "); 			
				ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				handler.performNavigation("listarOng_?faces-redirect=true");
			}
			else	
			{
				
				Long id = new Long(idEventoString);
			//	Ong o = new Ong();
				
				//o = manager.buscarOngPorID_EB(id);
				//a = manager.obetenrAdministradorPorNick(idAdministrador);
				manager.EliminarONG(id);
				
				


				ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				handler.performNavigation("listarOng_?faces-redirect=true");
			}
		
		}
		/*----------------------------------------------------------------------------------------------------------------------------------*/
		public void cancelar(){
		


				ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				handler.performNavigation("listarOng_?faces-redirect=true");
			
		
		}
	}



