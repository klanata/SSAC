package com.web.beans;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.Part;

import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.ImagenPersonaDesaparecida;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.PersonasDesaparecidasEBR;

import cross_cuting.enums.EstadoPersona;

@ManagedBean(name="personasdesaparecidasBean")
@SessionScoped
public class PersonasDesaparecidasBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String apellido ;
	private String numeroContacto ;	
	private EstadoPersona decripcion ;	
	private Date fechNac;
	private String foto;
	private Set<ImagenPersonaDesaparecida> imagenes = new HashSet<ImagenPersonaDesaparecida>();
	
	private Part part;
	
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public EstadoPersona getDecripcion() {
		return decripcion;
	}
	public void setDecripcion(EstadoPersona decripcion) {
		this.decripcion = decripcion;
	}

	public Date getFechNac() {
		return fechNac;
	}

	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}

	public Set<ImagenPersonaDesaparecida> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<ImagenPersonaDesaparecida> imagenes) {
		this.imagenes = imagenes;
	}




	
	

	public String  registrarPersonasDesaparecidas(){				
		
		PersonasDesaparecidasEBR manager = null;
		
		Context context = null;
		
		FacesMessage message = null; 
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (PersonasDesaparecidasEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//PersonasDesaparecidasEB!com.core.service.negocio.remote.PersonasDesaparecidasEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				
    	
    	try{    		   	    	     		
    		InputBean inputBean = new InputBean();
    		String foto= inputBean.uploadFile(this.part); 
    		Long in = manager.crearReportePersonasDesaparecidas(nombre, apellido, numeroContacto, decripcion, fechNac,foto, imagenes);   
       		if (in == 0){
    			System.out.println("es repetido." + in);
    			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "ya existe  la persona ingresada.");
    	       
    		}
       		else {    	
    			this.nombre = "";   		
        		this.apellido = "";
        		this.numeroContacto= "";
        		this.decripcion = EstadoPersona.hallada;
        		
        		
    			System.out.println("no es repetido." + in);
    			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Se creo el reporte");
    		}    		    
    		FacesContext.getCurrentInstance().addMessage(null, message);
    		return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar catastrofe: " + excep.getMessage());      		 			       
	        return "failure";     		
    	}
	}
}
       		
    	