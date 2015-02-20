package com.web.beans.rescatista;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.Part;

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
	private String imagen="";
	private Part auxPart;	
	
	
	
	public Part getAuxPart() {
		return auxPart;
	}
	public void setAuxPart(Part auxPart) {
		this.auxPart = auxPart;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
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
	public void registrarRescatista(){				
		
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
    		//RequestContext requestContext = RequestContext.getCurrentInstance();
           
            //requestContext.update("form:display");
            //requestContext.execute("PF('dlg').show()");
            
            
            //InputBean inputBean = new InputBean();
    		//String imagen= inputBean.uploadFile(this.auxPart);  
            
            String imagen= "";
            
    		Long id= manager.crearRescatista(nombre, nick, apellido, email, password, fechaNac, sexo, celular,imagen);
    		if (id==0){
    			
    			//FacesContext contexto = FacesContext.getCurrentInstance(); 
    			//message = new FacesMessage("Ya existe una Rescatista con el mismo nick registrada en el sistema."); 
    			//contexto.addMessage("registroRescatista", message);
    			FacesMessage messages = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya existe un Rescatista con el mismo nick registrada en el sistema.");    			
    			FacesContext.getCurrentInstance().addMessage(null, messages);
    	        
    		}
    		else {    	
    			
    			this.nombre = "";   		
        		this.apellido = "";
        		this.nick = "";
        		this.celular = "";
        		this.email = "";
        		this.password = "";
        		this.sexo= "";
        		this.auxPart = null;        		        		
        		
    			//FacesContext contexto = FacesContext.getCurrentInstance(); 
    			//message = new FacesMessage("Rescatista registrado con exito !!"); 
    	        //contexto.addMessage("registroRescatista", message);
        		//FacesContext.getCurrentInstance().addMessage(null, messages); 
    	        
    	        FacesContext contexto = FacesContext.getCurrentInstance();        		
        		FacesMessage messages = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "El administrador fue ingresado al sistema.");    			
        		
    			contexto.getExternalContext().getFlash().setKeepMessages(true);    			
    	        contexto.addMessage(null, messages);
    	        
    	        ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listarRescatistas?faces-redirect=true");
        		
    		}
    		//FacesContext.getCurrentInstance().addMessage(null, message);   		    		    		
    		//return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar rescatista: " + excep.getMessage());      		 			       
    		//return "failure"; 
    		
    	}    
	}
	/**************************************************************/
	public RescatistaBean(){}
	
	public RescatistaBean(Long id, String nombre, String apellido, String nick,
			String email, String password, Date fechaNac, String sexo,
			String celular,String imagen) {
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
		this.imagen = imagen;
	}
	
	
}
