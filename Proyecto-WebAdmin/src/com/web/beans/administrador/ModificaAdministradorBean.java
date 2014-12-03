package com.web.beans.administrador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

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
import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.AdministradorEBR;


@ManagedBean(name="modificarAdminBean")
@RequestScoped
public class ModificaAdministradorBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre ;
	private String apellido  ;
	
	private String nick ;
	private String email ;
	private String password  ;
	private Date fechaNac ;
	private String sexo ;
	private String celular ;
	private Long id;
	
   @ManagedProperty("#{administradorBean}")
   private AdministradorBean administradorBean = new AdministradorBean();
	
	
	public AdministradorBean getAdministradorBean() {
	return administradorBean;
}
public void setAdministradorBean(AdministradorBean administradorBean) {
	this.administradorBean = administradorBean;
}
	/*-------------- get and set --------------------------*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isLogeado() {
		return logeado;
	}
	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}
	private boolean logeado = false;

	  public boolean estaLogeado() {
	    return logeado;
	  }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

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
