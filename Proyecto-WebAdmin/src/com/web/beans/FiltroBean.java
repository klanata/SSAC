package com.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;
import com.core.service.negocio.remote.FiltroEBR;


@ManagedBean(name="filtroBean")
@SessionScoped
public class FiltroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;			
	private String descripcion;						
	private boolean bajaLogica;
	
	
	//	------------------ Constructors  --------------------------------
	
	public FiltroBean() {	
	}	
	public FiltroBean(Long id, String descripcion,boolean bajaLogica) {
		super();
		this.id = id;		
		this.descripcion = descripcion;
		this.bajaLogica = bajaLogica;	
	}	
	
	//	------------------ Getter and setter methods ---------------------
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	
	
	//	------------------ Operaciones ---------------------
		
	
	public String registrar(){
		
		FiltroEBR manager = null;		
		
		Context context = null;
				
		FacesMessage message = null; 
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (FiltroEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//FiltroEB!com.core.service.negocio.remote.FiltroEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				    			
		
    	try{    	
    		Long in = manager.ingesarFiltro(descripcion);       		    	
    		if (in == 0){
    			System.out.println("es repetido." + in);
    			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya existe un catástrofe con el mismo nombre de evento registrada en el sistema.");
    	        //FacesMessage messages = new FacesMessage("Ya existe un catastrofe con el mismo nombre de evento registrada en el sistema."); 
    	        //contexto.addMessage("registroCatastrofe", messages);
    		}
    		else {    	    			   	
        		this.descripcion = "";       
    			System.out.println("no es repetido." + in);
    			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "La filtro fue ingresada al sistema.");
    		}    		    
    		FacesContext.getCurrentInstance().addMessage(null, message);
    		return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar el filtro: " + excep.getMessage());      		 			       
	        return "failure";     		
    	}        	    	
	}	
	

}
