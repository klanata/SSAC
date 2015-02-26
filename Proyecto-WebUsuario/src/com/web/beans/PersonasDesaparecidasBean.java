package com.web.beans;
import java.io.Serializable;
import java.util.Date;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;









import javax.servlet.http.HttpSession;

import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
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
	private List<String> listaMostrar;
	private String imagen;
	private String encontrada;
//	private Part part;
	

		//////////////// Getters and Setters
	public PersonasDesaparecidasBean(){}
	
	
	public PersonasDesaparecidasBean(Long id, String nombre,
			String apellido, String cedula, String numeroContacto, String descripcion,
			Date fechnac, List<String> listaMostrar,
			String encontrada) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.cedula=cedula;
		this.numeroContacto=numeroContacto;
		this.descripcion=descripcion;
		this.fechNac=fechnac;
		this.listaMostrar=listaMostrar;
		this.encontrada=encontrada;
		
	}
	
	
	public List<String> getListaMostrar() {
		/*
		String idPersonaString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idP");
		
		System.out.println("idPersonaString: " + idPersonaString); 
		
		if (idPersonaString != null) {
			
			PersonasDesaparecidasEBR manager = null;		    	
			Context context = null;			
			//FacesMessage message = null;
			
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
				PersonasDesaparecidas persona;
				Long idPersona = new Long(idPersonaString);
				persona = manager.buscaPersonaPorId(idPersona);
				
				Set<ImagenPersonaDesaparecida> imagenes;
				imagenes = persona.getImagenes();
				String path;	
				
				for (ImagenPersonaDesaparecida img : imagenes){					
					path = img.getPath();					
					listaMostrar.add(path);							
				}				
				
			}catch (Exception excep){
	    		System.out.println("Excepción al obtener imagnes de las personas: " + excep.getMessage());      		 			       	           	
	    	} 
			
		}		
		*/				
		return listaMostrar;
	}


	public void setListaMostrar(List<String> listaMostrar) {
		this.listaMostrar = listaMostrar;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getEncontrada() {
		return encontrada;
	}


	public void setEncontrada(String encontrada) {
		this.encontrada = encontrada;
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
    		
    		FacesContext contexto = FacesContext.getCurrentInstance();
			HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
			catastrofeId = (Long)sesion.getAttribute("idmongo");
			
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
       		
    	