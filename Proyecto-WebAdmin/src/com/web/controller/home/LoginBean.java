package com.web.controller.home;


import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.AdministradorEBR;





@ManagedBean(name="loginBean")
@SessionScoped

public class LoginBean implements Serializable {
	private static final long serialVersionUID = -2152389656664659476L;
	private String nombre;
	private String clave;
	private boolean logeado = false;
	public boolean estaLogeado() {
	return logeado;
	
	
	}
	
	
	
	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getNombre() {
	return nombre;
	} public void setNombre(String nombre) {
	this.nombre = nombre;
	} public String getClave() {
	return clave; }
	public void setClave(String clave) {
	this.clave = clave;
	}
	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		
		//////
		AdministradorEBR manager = null;		
		
		Context contexto = null;
		 
		try {
            // 1. Obtaining Context
            contexto = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (AdministradorEBR) contexto.lookup("ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
		
		Date fechaNac = new Date();
		try {
			Long id= manager.crearAdministrador("Administrador", "admin", "Sistema", "admin@ssac.com.uy", "admin", fechaNac, "Femenino", "1523639");
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
	
		////
		Boolean exito =	manager.existeUsuario(nombre, clave);
		
		
		if (exito ==  true) 
		{
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombre);
			context.addCallbackParam("view", "index.xhtml");
		} 
		else
		{
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
			"Credenciales no v�lidas");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado){					
			context.addCallbackParam("view", "index.xhtml");
		}
	} 
	
	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	} 
}