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
	private Date fechNac;
	private String desc ;
	private String foto;
	private Set<ImagenPersonaDesaparecida> imagenes = new HashSet<ImagenPersonaDesaparecida>();
	private Boolean hallada;
	
	private Part part;
	
	
	/////Constructores
	
	public PersonasDesaparecidasBean() {	
	}	
	public PersonasDesaparecidasBean(Long id, String nombre, String apellido, String numeroContacto, Date fechNac, String desc, String foto,
			 Boolean hallada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroContacto = numeroContacto;		
		this.fechNac = fechNac;
		this.desc = desc;
		this.foto = foto;
		this.hallada = hallada;
	}
		//////////////// Getters and Setters
	
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

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	public Boolean getHallada() {
		return hallada;
	}
	public void setHallada(Boolean hallada) {
		this.hallada = hallada;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public String registrarPersonasDesaparecidas(){				
		
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
//    		InputBean inputBean = new InputBean();
//    		String foto= inputBean.uploadFile(this.part); 
//    		Long in = manager.crearReportePersonasDesaparecidas(this.nombre, this.apellido, this.numeroContacto, this.fechNac, this.desc, foto, imagenes, this.hallada);   
//    		if (in == 0){
//    			System.out.println("es repetido." + in);
//    			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya existe .");
//    	      
//    		}
//    		else {    	
//    			this.nombre = "";   		
//        		this.apellido = "";
//        		this.numeroContacto = "";
//        		this.desc = "";
//        		this.hallada = false;
//        		
//    			System.out.println("no es repetido." + in);
//    			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Persona ingresada.");
//    		}    		    
//    		FacesContext.getCurrentInstance().addMessage(null, message);
    		return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar catastrofe: " + excep.getMessage());      		 			       
	        return "failure";     		
    	}        	    	
	}
	
		
}
       		
    	