package com.web.login.beans;

import java.io.Serializable;
import java.util.Date;








import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;










import com.core.data.entites.Usuario;
import com.core.service.negocio.remote.UsuarioEBR;



@ManagedBean(name="registroBean")
@RequestScoped

public class RegistroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String clave;
	private String clave1;
	private boolean logeado = false;
	private String mensaje;
	private String nick;
	private String nick1;
	private String email;
	private Date fechaNacimiento;
	private String Menu;
	private Long idCatastrofe;
	private Long idCatastrofe1;
	
	
	/*geter and seter*/
	
	
	public boolean estaLogeado() {
		return logeado;
	} 
	public String getMenu() {
		return Menu;
	}
	public void setMenu(String menu) {
		Menu = menu;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	
	public void registro() {
		
		
		
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
		String imagen = "null";
		FacesContext contextousuario = FacesContext.getCurrentInstance();
		HttpSession sesion = (HttpSession)contextousuario.getExternalContext().getSession(true);
		idCatastrofe = (Long)sesion.getAttribute("idmongo");
   		
		Usuario u = manager.obtenerUsuarioPorNick(nick);
		
		if(u ==null)
		{
			
			try {
				manager.registroUsuarioPlataforma(nick, clave, email, nombre, fechaNacimiento, idCatastrofe,imagen);
				FacesContext contextomsg = FacesContext.getCurrentInstance();
				FacesMessage messages = new FacesMessage("Se ha registrado con exito. !!"); 
		        contextomsg.addMessage("registroBean", messages);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{
			nick="";
			clave="";
			email="";
			nombre="";
			//error usuario ya esta registrado
			FacesContext contextomsg = FacesContext.getCurrentInstance();
			FacesMessage messages = new FacesMessage("Error el usuario se encuentra registrado !!"); 
	        contextomsg.addMessage("registroBean", messages);
		}
		
		
		
	}

	
}
