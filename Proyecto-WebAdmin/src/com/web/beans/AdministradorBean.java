package com.web.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.AdministradorEBR;

@ManagedBean(name="administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre = "";
	private String apellido = "";
	
	private String nick = "";
	private String email = "";
	private String password = "";
	private Date fechaNac= null;
	private String sexo = "";
	private Integer celular = new Integer(0);
	
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
	public Integer getCelular() {
		return celular;
	}
	public void setCelular(Integer celular) {
		this.celular = celular;
	}
	public String registrarAdministrador(){				
		
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
    		RequestContext requestContext = RequestContext.getCurrentInstance();
            
            requestContext.update("form:display");
            requestContext.execute("PF('dlg').show()");
    		
    		Long id= manager.crearAdministrador(nombre, nick, apellido, email, password, fechaNac, sexo, celular);
    		if (id.equals(0)){
    			
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Ya existe un Administrador con el mismo nick registrada en el sistema."); 
    	        contexto.addMessage("registroAdministrador", messages);
    		}
    		else {    	
    			

    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Administrador registrado con exito !!"); 
    	        contexto.addMessage("registroAdministrador", messages);
    			
    			this.nombre = "";   		
        		this.apellido = "";
        		this.nick = "";
        		this.celular = null;
        		this.email = "";
        		this.password = "";
        		this.sexo= "";
        		
    		}
    		    		    		    		
    		return "success"; 
    		
    	}catch (Exception excep){
    		//System.out.println("Excepcion en agregar ong: " + excep.getMessage());      		 			       
	        return "failure"; 
    		
    	}    
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
}