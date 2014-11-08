package view;



import com.core.service.negocio.remote.UsuarioEBR;

import java.util.Date;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



@ManagedBean
@SessionScoped
public class registroUsuario {
	
	private String nick;
	private String nombre;
	private String apellido;
	private String password;	
	private String mail;
	private String pathy = "3333";
	
	@EJB
	private UsuarioEBR servicioSeguridad;
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String registrar(){
		
		
		try{
		   
    		Date fechaNac = new Date();
    		fechaNac.getTime();
    		
    		servicioSeguridad.ingesarUsuraio(nick, password, mail, 
    				nombre, fechaNac);
    		
    		
    		System.out.println("pasa por aca.");
    		return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Agregar-Usuario: " + excep.getMessage());  
    		FacesContext contexto = FacesContext.getCurrentInstance(); 
	        FacesMessage messages = new FacesMessage("Ya existe un usuario con el mismo nick registrado en el sistema."); 
	        contexto.addMessage("registrarForm", messages); 		
	        nick="";
    		nombre="";
    		apellido="";
    		mail=""; 
	        return "failure"; 
    		
    	}    
	}
    	
	
	

}
