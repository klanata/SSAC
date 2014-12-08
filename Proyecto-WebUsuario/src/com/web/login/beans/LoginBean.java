package com.web.login.beans;


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



import com.core.service.negocio.remote.UsuarioEBR;




@ManagedBean(name="loginBean")
@SessionScoped

public class LoginBean implements Serializable {
	private static final long serialVersionUID = -2152389656664659476L;
	private String nombre;
	private String clave;
	private String clave1;
	private boolean logeado = false;
	
	private String nick;
	private String nick1;
	private String email;
	private Date fechaNacimiento;
	
	private Long idCatastrofe;
	private Long idCatastrofe1;
	
	
	/*geter and seter*/
	
	
	public boolean estaLogeado() {
		return logeado;
	} 
	public String getClave1() {
		return clave1;
	}
	public void setClave1(String clave1) {
		this.clave1 = clave1;
	}
	public String getNick1() {
		return nick1;
	}
	public void setNick1(String nick1) {
		this.nick1 = nick1;
	}
	public Long getIdCatastrofe1() {
		return idCatastrofe1;
	}
	public void setIdCatastrofe1(Long idCatastrofe1) {
		this.idCatastrofe1 = idCatastrofe1;
	}
	public Long getIdCatastrofe() {
		return idCatastrofe;
	}
	public void setIdCatastrofe(Long idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}
	public boolean isLogeado() {
		return logeado;
	}
	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNombre() {
		return nombre;
	} 
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getClave() {
		return clave; 
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public void login(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		
		//////
		UsuarioEBR manager = null;		
		
		Context contexto = null;
		 
		try {
            // 1. Obtaining Context
            contexto = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (UsuarioEBR) contexto.lookup("ejb:Proyecto-EAR/Proyecto-Core//UsuarioEB!com.core.service.negocio.remote.UsuarioEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
		
		//System.out.print("clave1" + clave1);
		//System.out.print("nick1" + nick1);
		////veo si el usuario esta registrado
		Boolean exito =	manager.existeUsuario(nick1, clave1);
		
		
	
		//veo si esta registrado a esa catastrofe
		Boolean registrado = manager.estaRegistradoCatastrofe(nick1, idCatastrofe1);
		
		if(registrado==false){
					
					System.out.print("registro == false usuario no registrado a la catastrofe");
				}	
		if ((exito ==  true) && (registrado ==true))
		{
			
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nick1);
			context.addCallbackParam("view", "PerDesaparecidas.xhtml");
		} 
		else if((exito == true) && (registrado==false))
		{
			//si esta registrado en la plataforma pero no a la catastrofe
			
			manager.registrarACatastrofe(nick1, clave1, idCatastrofe);
			logeado = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@","Se ha registrado a la catástrofe");
			context.addCallbackParam("view", "PerDesaparecidas.xhtml");
		}
		else{
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error","Credenciales no válidas");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado){
			context.addCallbackParam("view", "index.xhtml");
		}
	} 
	
	
	public void registro(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		
		//////
		UsuarioEBR manager = null;		
		
		Context contexto = null;
		 
		try {
            // 1. Obtaining Context
            contexto = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (UsuarioEBR) contexto.lookup("ejb:Proyecto-EAR/Proyecto-Core//UsuarioEB!com.core.service.negocio.remote.UsuarioEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		manager.registroUsuarioPlataforma(nick, clave, email, nombre, fechaNacimiento, idCatastrofe);
		nick="";
		clave="";
		email="";
		nombre="";
		//fechaNacimiento = null;
		//idCatastrofe= //
		context.addCallbackParam("view", "PerDesaparecidas.xhtml");
	}
	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		logeado = false;
	} 
}