package com.web.beans.infoCatastrofe;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;

import com.core.data.entites.Ong;
import com.core.service.negocio.remote.OngEBR;
import com.web.beans.InputBean;

@ManagedBean(name="ongUsuarioBean")
@RequestScoped
public class OngBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	
	
	private String nombre = "";
	private String direccion = "";
	private String telefono = "";
	private String email ="";
	private String citioWeb = "";
	private String descripcion = "";
	private Long id;
	private String imagen = "";
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


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCitioWeb() {
		return citioWeb;
	}


	public void setCitioWeb(String citioWeb) {
		this.citioWeb = citioWeb;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String registrarOng(){				
		
		OngEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (OngEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				
    	
    	try{    	
    		RequestContext requestContext = RequestContext.getCurrentInstance();
            
            requestContext.update("form:display");
            requestContext.execute("PF('dlg').show()");
            
            InputBean inputBean = new InputBean();
    		String imagen= inputBean.uploadFile(this.auxPart); 
    		Long id= manager.ingesarOng(nombre, direccion, descripcion, email, telefono, citioWeb, imagen);
    		if (id.equals(0)){
    			
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Ya existe una ONG con el mismo nombre registrada en el sistema."); 
    	        contexto.addMessage("registroOng", messages);
    		}
    		else {    	
    			
    			
    			this.nombre = "";   		
        		this.descripcion = "";
        		this.citioWeb = "";
        		this.telefono = "";
        		this.email = "";
        		this.direccion = "";
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Ong registrada con exito !!"); 
    	        contexto.addMessage("registroOng", messages);
    			
        		
    		}
    		    		    		    		
    		return "success"; 
    		
    	}catch (Exception excep){
    		//System.out.println("Excepcion en agregar ong: " + excep.getMessage());      		 			       
	        return "failure"; 
    		
    	}    
	}
	////////////////////////////////////////////////////////////////
	public Collection<Ong> listarTodasLasOng ()
	{
		OngEBR manager = null;		
		Collection<Ong> lista = null;
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (OngEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }	
		lista= manager.listarTodasLasOng(); 
		
		return lista;
	}
	////////////////////////////////////////////////////////////////
	//Constructores
	public OngBean(){}
	
	public OngBean(Long id,String nombre, String direccion, String telefono,
			String email, String citioWeb, String descripcion, String imagen) {
		super();
		this.id= id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.citioWeb = citioWeb;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}
	
	
	//////////////////////////////////////////////////////////////////////

}
