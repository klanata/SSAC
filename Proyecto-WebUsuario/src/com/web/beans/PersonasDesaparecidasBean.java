package com.web.beans;
import java.io.Serializable;
import java.util.Date;


import java.util.HashSet;
import java.util.Set;

import javax.faces.application.ConfigurableNavigationHandler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;









import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;



@ManagedBean(name="personasdesaparecidasBean")
@RequestScoped
public class PersonasDesaparecidasBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long catastrofeId;
	private Long id;
	private String nombre;
	private String apellido ;
	private String cedula;
	private String numeroContacto ;	
	private Date fechNac;
	private String descripcion ;
	private Set<ImagenPersonaDesaparecida> imagenes = new HashSet<ImagenPersonaDesaparecida>();
	private Boolean hallada;
//	private Part part;
	

		//////////////// Getters and Setters
	public PersonasDesaparecidasBean(){}
	
	
	public PersonasDesaparecidasBean(Long id, String nombre,
			String apellido, String cedula, String numeroContacto, String descripcion,
			Date fechnac, Set<ImagenPersonaDesaparecida> imagenes,
			Boolean hallada) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.cedula=cedula;
		this.numeroContacto=numeroContacto;
		this.descripcion=descripcion;
		this.fechNac=fechnac;
		this.imagenes=imagenes;
		this.hallada=hallada;
		
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
	public String getNumeroContacto() {
		return numeroContacto;
	}
	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}
	public Date getFechNac() {
		return fechNac;
	}
	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<ImagenPersonaDesaparecida> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<ImagenPersonaDesaparecida> imagenes) {
		this.imagenes = imagenes;
	}
	public Boolean getHallada() {
		return hallada;
	}
	public void setHallada(Boolean hallada) {
		this.hallada = hallada;
	}
	
	
/*	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	*/
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


public Long getCatastrofeId() {
		return catastrofeId;
	}
	public void setCatastrofeId(Long catastrofeId) {
		this.catastrofeId = catastrofeId;
	}
public void registrarPersonasDesaparecidas(){				
		
		PersonasDesaparecidasEBR manager = null;
		
		Context context = null;
		
		
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
    		Date fechaPublicacion= new Date();
    		fechaPublicacion.getTime();
    		
    		hallada = false;
    		Long in =manager.crearReportePersonasDesaparecidas(catastrofeId, nombre, apellido, cedula, numeroContacto, descripcion, fechaPublicacion, imagenes, hallada);
    		String idP = in.toString();
    		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idP", idP); 
    		
    		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("agregarImagenPersona?faces-redirect=true");	
			
    	}catch (Exception excep){
    		System.out.println("Excepcion en persona: " + excep.getMessage());      		 			       
	        		
    	}        	   
	}
	
		
}
       		
    	