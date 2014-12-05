package com.web.beans.rescatista;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.RescatistaEBR;

@ManagedBean(name="rescatistaBean")
@RequestScoped
public class RescatistaBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre = "";
	private String apellido = "";
	
	private String nick = "";
	private String email = "";
	private String password = "";
	private Date fechaNac= null;
	private String sexo;
	private String celular = "";
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String registrarRescatista(){				
		
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
    		RequestContext requestContext = RequestContext.getCurrentInstance();
            
            requestContext.update("form:display");
            requestContext.execute("PF('dlg').show()");
    		
    		Long id= manager.crearRescatista(nombre, nick, apellido, email, password, fechaNac, sexo, celular);
    		if (id.equals(0)){
    			
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Ya existe una Rescatista con el mismo nick registrada en el sistema."); 
    	        contexto.addMessage("registroRescatista", messages);
    	        
    		}
    		else {    	
    			
    			this.nombre = "";   		
        		this.apellido = "";
        		this.nick = "";
        		this.celular = "";
        		this.email = "";
        		this.password = "";
        		this.sexo= "";
        		
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Rescatista registrado con exito !!"); 
    	        contexto.addMessage("registroRescatista", messages);
    			
    	        FacesContext.getCurrentInstance().addMessage(null, messages); 
        		
    		}
    		   		    		    		
    		return "success"; 
    		
    	}catch (Exception excep){
    		//System.out.println("Excepcion en agregar ong: " + excep.getMessage());      		 			       
	        return "failure"; 
    		
    	}    
	}
	/**************************************************************/
	public RescatistaBean(){}
	
	public RescatistaBean(Long id, String nombre, String apellido, String nick,
			String email, String password, Date fechaNac, String sexo,
			String celular) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nick = nick;
		this.email = email;
		this.password = password;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.celular = celular;
	}
	
	
}
