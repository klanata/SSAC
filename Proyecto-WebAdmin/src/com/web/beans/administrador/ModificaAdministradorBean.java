package com.web.beans.administrador;

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

import com.core.data.entites.Administrador;

import com.core.service.negocio.remote.AdministradorEBR;


@ManagedBean(name="modificarAdminBean")
@RequestScoped
public class ModificaAdministradorBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
   @ManagedProperty("#{administradorBean}")
   private AdministradorBean administradorBean = new AdministradorBean();
	
	
	public AdministradorBean getAdministradorBean() {
	return administradorBean;
	}
	public void setAdministradorBean(AdministradorBean administradorBean) {
		this.administradorBean = administradorBean;
	}
	/*-------------- get and set --------------------------*/
	

	/*-------------------------------------------------------------------------------------*/
	@PostConstruct
    public void init() {
    	
		AdministradorEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (AdministradorEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }		
					
		try{			
			
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoAdministrador");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    			System.out.println("No existe administrador "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listarAdministrador?faces-redirect=true");
    		}
    		else	
    		{
			
    			
    			//busco al administrador con es id
    			Long idAdministrador = new Long(idEventoString);
				
				Administrador a = new Administrador();
				a = manager.obetenrAdministradorPorNick(idAdministrador);
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
				
				administradorBean = new AdministradorBean(idAdministrador, nombre, apellido, nick, email, password, fechaNac, sexo, celular);
				 
				  System.out.println("obtengo administrador ");      	
		     
		     }
				
			
    	}catch (Exception excep){
    		System.out.println("Excepci�n al listar los administradores: " + excep.getMessage());      		 			       	           	
    	}  					
		
		
    }    
	/*---------------------------------------------------------------------------------------------------------*/
	public void editar(){
			
		AdministradorEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (AdministradorEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }		
					
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoAdministrador");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe administrador "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarAdministrador?faces-redirect=true");
		}
		else	
		{
			String nombre = administradorBean.getNombre();
			String apellido= administradorBean.getApellido();
			String nick =administradorBean.getNick();
			String email = administradorBean.getEmail();
			String password = administradorBean.getPassword() ;
			Date fechaNac = administradorBean.getFechaNac();
			String sexo = administradorBean.getSexo() ;
			String celular = administradorBean.getCelular();
		
			manager.modificarAdministrador(nick, nombre, apellido, email, password, fechaNac, sexo, celular);
			


			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarAdministradores?faces-redirect=true");
		}
	
	}
	/*-------------------------------------------------------------------------------------------------------*/
}
