package com.web.beans.administrador;

import java.io.Serializable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.AdministradorEBR;

@ManagedBean(name="administradorBean")
@RequestScoped
public class AdministradorBean implements Serializable{


	/**
	 * 
	 */
	private String urlLogueado ="http://localhost:8080/Proyecto-WebAdmin/index.xhtml";
	
	private static final long serialVersionUID = 1L;
	private String nombre = "";
	private String apellido = "";
	
	private String nick = "";
	private String email = "";
	private String password = "";
	private Date fechaNac= null;
	private String sexo = "";
	private String celular ;
	private Long id;
	
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
	
	
	public String getUrlLogueado() {
		return urlLogueado;
	}
	public void setUrlLogueado(String urlLogueado) {
		this.urlLogueado = urlLogueado;
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
    		
            //controlamos que el correo tenga un @
            
            
            
            
    		Long id= manager.crearAdministrador(nombre, nick, apellido, email, password, fechaNac, sexo, celular);
    		if (id.equals(0)){
    			
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Ya existe un Administrador con el mismo nick registrada en el sistema."); 
    	        contexto.addMessage("registroAdministrador", messages);
    	        
    	        requestContext.addCallbackParam("view", "altaAdministrador.xhtml");
    		}
    		else {    	
    			

    			this.nombre = "";   		
        		this.apellido = "";
        		this.nick = "";
        		this.celular = "";
        		this.email = "";
        		this.password = "";
        		this.sexo= "";
        		this.urlLogueado ="http://localhost:8080/Proyecto-WebAdmin/index.xhtml";
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Administrador registrado con exito !!"); 
    	        contexto.addMessage("registroAdministrador", messages);
    			
    			
        		
    		}
    		    		    		    		
    		return "success"; 
    		
    	}catch (Exception excep){
    		//System.out.println("Excepcion en agregar ong: " + excep.getMessage());      		 			       
	        return "failure"; 
    		
    	}    
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public void login() 
	{
		
		AdministradorEBR manager = null;		
		
		Context contexto = null;
		
		
		System.out.print(nick);
		System.out.print(password);
		 
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
		
		
		Boolean exito= false;
		if (nick != null && password != null){
			
			exito=	manager.existeUsuario(nick, password);
		}
		
		
	    RequestContext context = RequestContext.getCurrentInstance();
	    FacesMessage msg = null;
	    if (exito==true) {
	    	logeado = true;
	      
	      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nick);
	    } else {
	      logeado = false;
	      
	      msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
	                             "Credenciales no válidas");
	    }

	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    context.addCallbackParam("estaLogeado", logeado);
	    if (logeado){
	    	
	      context.addCallbackParam("view", "index.xhtml");
	      }
	  }
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	 public void logout() {
		   
		 
		 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		    session.invalidate();
		    logeado = false;
		  }

	
	 /////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	private String MD5(String input) {

		String md5 = null;

		if(null == input) return null;

		try {

			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return md5;
	}

	public AdministradorBean(){}
	
	public AdministradorBean(Long id,String nombre, String apellido, String nick,
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