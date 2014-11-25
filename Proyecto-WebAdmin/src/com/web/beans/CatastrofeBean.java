package com.web.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Servicio;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.web.beans.InputBean;
import javax.servlet.http.Part;


@ManagedBean(name="catastrofeBean")
@SessionScoped
public class CatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private String nombreEvento;
	private String descripcion;
	private String logo;
	private double coordenadasX;
	private double coordenadasY;
	private Boolean activa;
	private Boolean prioridad;
	private Collection<Servicio> servicios = new ArrayList<Servicio>();
	private Collection<Ong> ongs  = new ArrayList<Ong>();
	private PlanDeRiesgo planDeRiesgo;	
	private Part part;
	
	
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
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
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	public Boolean getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Boolean prioridad) {
		this.prioridad = prioridad;
	}
	public Collection<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(Collection<Servicio> servicios) {
		this.servicios = servicios;
	}
	public Collection<Ong> getOngs() {
		return ongs;
	}
	public void setOngs(Collection<Ong> ongs) {
		this.ongs = ongs;
	}
	public PlanDeRiesgo getPlanDeRiesgo() {
		return planDeRiesgo;
	}
	public void setPlanDeRiesgo(PlanDeRiesgo planDeRiesgo) {
		this.planDeRiesgo = planDeRiesgo;
	}	
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	public String registrarCatastrofe(){				
		
		CatastrofeEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (CatastrofeEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				
    	
    	try{    	
    		InputBean inputBean = new InputBean();
    		String logo= inputBean.uploadFile(this.part);    		    		    	
       		Long in= manager.ingesarCatastrofe(this.nombreEvento, this.descripcion, logo, this.coordenadasX, this.coordenadasY, this.activa, this.prioridad, servicios, ongs, planDeRiesgo);    	
    		if (in.equals(0)){
    			System.out.println("es repetido." + in);
    			FacesContext contexto = FacesContext.getCurrentInstance(); 
    	        FacesMessage messages = new FacesMessage("Ya existe un catastrofe con el mismo nombre de evento registrada en el sistema."); 
    	        contexto.addMessage("registroCatastrofe", messages);
    		}
    		else {    	
    			this.nombreEvento = "";   		
        		this.descripcion = "";
        		this.part = null;
        		this.coordenadasX = 0;
        		this.coordenadasY = 0;
        		this.activa = false;
        		this.prioridad = false;
    			System.out.println("no es repetido." + in);
    		}
    		    		    		    		
    		return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar catastrofe: " + excep.getMessage());      		 			       
	        return "failure"; 
    		
    	}    
	}
	
	
			
}
