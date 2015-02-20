package com.web.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.annotation.PostConstruct; 

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Filtro;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="catastrofeBeanMap")
@SessionScoped
public class CatastrofeBeanMap implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String nombreEvento;
	private String descripcion;
	private String logo;
	private double coordenadasX;
	private double coordenadasY;
	private Boolean activa;
	private Boolean prioridad;
	private String css;
	private Set<ImagenCatastrofe> imagenes = new HashSet<ImagenCatastrofe>();
	private Set<Filtro> filtros = new HashSet<Filtro>();
	private Set<Ong> ongs  =  new HashSet<Ong>();
	private PlanDeRiesgo planDeRiesgo;	
	private Part part;	   
	private String polygon;
	
	
	//	------------------ Constructors  --------------------------------
	
	public CatastrofeBeanMap() {	
	}	
	
	public CatastrofeBeanMap(Long id, String nombreEvento, String descripcion, String logo, double coordenadasX,
			double coordenadasY, Boolean activa, Boolean prioridad, String css,String polygon) {
		super();
		this.id = id;
		this.nombreEvento = nombreEvento;
		this.descripcion = descripcion;
		this.logo = logo;	
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.activa = activa;
		this.prioridad = prioridad;
		this.css = css;
		this.polygon = polygon;
	}
		
	
	//	------------------ Getter and setter methods ---------------------
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public Set<ImagenCatastrofe> getImagenes() {
		return imagenes;
	}

	public void setImagenes(Set<ImagenCatastrofe> imagenes) {
		this.imagenes = imagenes;
	}

	public Set<Filtro> getFiltros() {
		return filtros;
	}

	public void setFiltros(Set<Filtro> filtros) {
		this.filtros = filtros;
	}

	public Set<Ong> getOngs() {
		return ongs;
	}

	public void setOngs(Set<Ong> ongs) {
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

	public String getPolygon() {
		return polygon;
	}

	public void setPolygon(String polygon) {
		this.polygon = polygon;
	}

	
	//	------------------ Operaciones ---------------------
		
	
	@PostConstruct
	public void init() {
		
	}

		
	public void registrarCatastrofe(){	
		
		CatastrofeEBR manager = null;		
		
		Context context = null;
				
		FacesMessage message = null; 
		
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
    		
    		//String logo = inputBean.uploadFile(this.part);
    		    		    		
    		String nombreLogo = inputBean.uploadFile(this.part);  
    		
    		String logo = inputBean.resizeImageCopy(nombreLogo);    
    		
    		inputBean.borrarLogo(nombreLogo);
    		
    		String css = null;    		
    		
    		List<Catastrofe> res = new ArrayList<Catastrofe>();
			res = manager.listaCatastrofes();    				
			Catastrofe catastrofe;
			String catastrofePoligono;
			boolean existePoligono;
			Long idCatastrofePoligono;
	    	
			idCatastrofePoligono = (long) 0;
			existePoligono = false;
			for (int i=0; i<=res.size()-1; i++){    		
				catastrofe = res.get(i);				
				catastrofePoligono = catastrofe.getPoligono();
				if (catastrofePoligono.equals(polygon)){					
					existePoligono = true;
					idCatastrofePoligono = catastrofe.getId();
				}								
			}
    		
			if (existePoligono) {
				Catastrofe c;
				c = manager.buscaCatastrofePorId(idCatastrofePoligono);
				String nombreC;
				nombreC = c.getNombreEvento();
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya existe un catástrofe en la misma zona. Para ingresar una nueva debe dar de baja la catástrofe " + nombreC);
    			FacesContext.getCurrentInstance().addMessage(null, message);    
			}
			else {
				Long idCatastrofe= manager.ingresarCatastrofe(this.nombreEvento, this.descripcion, logo, this.coordenadasX, this.coordenadasY, this.activa, this.prioridad, css, imagenes, filtros, ongs, planDeRiesgo,this.polygon);    	
	    		if (idCatastrofe == 0){
	    			//System.out.println("es repetido." + idCatastrofe);
	    			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya existe un catástrofe con el mismo nombre de evento registrada en el sistema.");
	    			FacesContext.getCurrentInstance().addMessage(null, message);    	        
	    		}
	    		else {    	
	    			this.nombreEvento = "";     			
	        		this.descripcion = "";
	        		this.part = null;        		
	        		this.activa = false;
	        		this.prioridad = false;
	        		this.polygon = "";
	    			//System.out.println("no es repetido." + idCatastrofe);
	    			//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "La catástrofe fue ingresada al sistema.");
	    			//FacesContext.getCurrentInstance().addMessage(null, message);
	        		String idCatastrofeString = idCatastrofe.toString();
	        		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idCatastrofeString", idCatastrofeString);
	        		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ABMCatastrofe", "Alta");
	        		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fileString", "");
	        		
	        		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	        		handler.performNavigation("asignarImgCatastrofe?faces-redirect=true");	        		
	    		} 
	    		
			}
       		    		    		
    		//return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepción en agregar catastrofe: " + excep.getMessage());      		 			       
	        //return "failure";     		
    	}      
		
		
	}
	
			
}
