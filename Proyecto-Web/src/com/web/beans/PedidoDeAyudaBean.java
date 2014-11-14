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
import com.core.data.entites.Ong;

import clienteutility.ClienteUtility;

import java.util.Collection;
import java.util.ArrayList;

import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.PedidoDeAyudaEBR;

@ManagedBean(name="pedidoAyudaBean")
@SessionScoped
public class PedidoDeAyudaBean {
	
	
	private static final long serialVersionUID = 1L;
	private Long catastrofeId;

	private String descripcion = "";
	
	private BigDecimal coordenadasX ;
		
	private BigDecimal coordenadasY ;
	
	private Date fechaPublicacion ;
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(BigDecimal coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public BigDecimal getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(BigDecimal coordenadasY) {
		this.coordenadasY = coordenadasY;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Long getCatastrofe() {
		return catastrofeId;
	}
	public void setCatastrofe(Long catastrofeId) {
		this.catastrofeId = catastrofeId;
	}

	public void  registrarpedidoAyuda(){				
		
		PedidoDeAyudaEBR manager = null;
		
		Context context = null;
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (PedidoDeAyudaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//PedidoDeAyudaEB!com.core.service.negocio.remote.PedidoDeAyudaEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				
    	
    	try{    		   	    	  
    		//esto esta mal es solo por ahora para que ande
    		
    		Date fechaPublicacion = new Date();
    		fechaPublicacion.getTime();
    		
       		manager.crearPedido(catastrofeId, descripcion, coordenadasX, coordenadasY, fechaPublicacion);    	
    		
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar catastrofe: " + excep.getMessage());      		 			       
	        
    		
    	}    
	}
}
