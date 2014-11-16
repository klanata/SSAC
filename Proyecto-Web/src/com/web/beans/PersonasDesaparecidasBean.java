package com.web.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.Column;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.Ong;

import clienteutility.ClienteUtility;

import java.util.Collection;
import java.util.ArrayList;

import com.core.service.negocio.remote.PersonasDesaparecidasEBR;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;

import cross_cuting.enums.EstadoPersona;

@ManagedBean(name="personasdesaparecidasBean")
@SessionScoped
public class PersonasDesaparecidasBean {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String apellido ;
	private String numeroContacto ;	
	private EstadoPersona decripcion ;	
	private Date fechNac;
	private ImagenPersonaDesaparecida imagenPersonaDesaparecida;
	
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

	public ImagenPersonaDesaparecida getImagenPersonaDesaparecida() {
		return imagenPersonaDesaparecida;
	}
	
	public void setImagenPersonaDesaparecida(
			ImagenPersonaDesaparecida imagenPersonaDesaparecida) {
		this.imagenPersonaDesaparecida = imagenPersonaDesaparecida;
	}




	
	

	public void  registrarPersonasDesaparecidas(){				
		
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
    		
    		
    		
    		
       		manager.crearReportePersonasDesaparecidas(nombre, apellido, numeroContacto, decripcion, fechNac, imagenPersonaDesaparecida);    	
    		
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar catastrofe: " + excep.getMessage());      		 			       
	        
    		
    	}    
	}
}