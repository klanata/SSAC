package com.web.beans;
import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;


import com.core.service.negocio.remote.PedidoDeAyudaEBR;
import com.core.service.negocio.remote.RescatistaEBR;

@ManagedBean(name="pedidoAyudaBean")
@SessionScoped
public class PedidoDeAyudaBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long catastrofeId;

	private String descripcion = "";
	
	private double coordenadasX ;
		
	private double coordenadasY ;
	
	private Date fechaPublicacion ;
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public double getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(double coordenadasY) {
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
		RescatistaEBR managerRes = null;
		Context context = null;
		Context contextEBR = null;
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            contextEBR = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (PedidoDeAyudaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//PedidoDeAyudaEB!com.core.service.negocio.remote.PedidoDeAyudaEBR");
 
            managerRes=(RescatistaEBR) contextEBR.lookup("ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR");
        } catch (NamingException e) {
            e.printStackTrace();
        }				
    	
    	try{    		   	    	  
    		
    		Date fechaPublicacion= new Date();
    		fechaPublicacion.getTime();
    	
    		FacesContext contexto = FacesContext.getCurrentInstance();
			HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
			catastrofeId = (Long)sesion.getAttribute("idmongo");
       		Long id = manager.crearPedido(catastrofeId, descripcion, coordenadasX, coordenadasY, fechaPublicacion);   
       		
       		///Llamamos a la funcion para asignar de forma autòmatica un rescatista.
       		//PedidoDeAyuda pedido= manager.buscarPedido(id);
       		managerRes.asignarRescatistaCatastrofe(id);
       		
       		
       		RequestContext requestContext = RequestContext.getCurrentInstance();
       	 requestContext.update("form:display");
         requestContext.execute("PF('dlg').show()");
         
         
    		if (id.equals(0)){
    			
    			//FacesContext contex = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Error al solicitar pedido de ayuda. "); 
    	        contexto.addMessage("pedidoAyudaBean", messages);
    	        
    	        
    		}
    		else {    	
    			

    			this.coordenadasX = (Double) null;
    			this.coordenadasY = (Double) null;
    			this.descripcion = "";
    			
        		
        		
    			//FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Pedido Creado con exito !!"); 
    	        contexto.addMessage("pedidoAyudaBean", messages);
    			
    			
        		
    		}
       		
       		
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en pedido de ayuda: " + excep.getMessage());      		 			       
	        
    		
    	}    
	}
}
