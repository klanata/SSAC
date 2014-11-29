package com.web.beans;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Servicio;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.web.beans.InputBean;

import cross_cuting.enums.TipoCatastrofe;
import javax.servlet.http.Part;


@ManagedBean(name="catastrofeBean")
@SessionScoped
public class CatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String nombreEvento;
	private String descripcion;
	private String logo;
	private double coordenadasX;
	private double coordenadasY;
	private Boolean activa;
	private Boolean prioridad;
	private Set<ImagenCatastrofe> imagenes = new HashSet<ImagenCatastrofe>();
	private Set<Servicio> servicios = new HashSet<Servicio>();
	private Set<Ong> ongs  =  new HashSet<Ong>();
	private PlanDeRiesgo planDeRiesgo;	
	private Part part;
	private TipoCatastrofe tipoCatastrofe; 
	
	
	//	------------------ Constructors  --------------------------------
	
	public CatastrofeBean() {	
	}	
	public CatastrofeBean(Long id, String nombreEvento, String descripcion, String logo, double coordenadasX,
			double coordenadasY, Boolean activa, Boolean prioridad) {
		super();
		this.id = id;
		this.nombreEvento = nombreEvento;
		this.descripcion = descripcion;
		this.logo = logo;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.activa = activa;
		this.prioridad = prioridad;
	}
		
	
	//	------------------ Getter and setter methods ---------------------
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoCatastrofe getTipoCatastrofe() {
		return tipoCatastrofe;
	}
	public void setTipoCatastrofe(TipoCatastrofe tipoCatastrofe) {
		this.tipoCatastrofe = tipoCatastrofe;
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
	public Set<ImagenCatastrofe> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<ImagenCatastrofe> imagenes) {
		this.imagenes = imagenes;
	}
	public Set<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
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
	
	//	------------------ Operaciones ---------------------
		
	public String registrarCatastrofe(){				
		
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
    		String logo= inputBean.uploadFile(this.part);    		    		    	
       		Long in= manager.ingesarCatastrofe(this.nombreEvento, this.descripcion, logo, this.coordenadasX, this.coordenadasY, this.activa, this.prioridad, imagenes, servicios, ongs, planDeRiesgo);    	
    		if (in == 0){
    			System.out.println("es repetido." + in);
    			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya existe un catástrofe con el mismo nombre de evento registrada en el sistema.");
    	        //FacesMessage messages = new FacesMessage("Ya existe un catastrofe con el mismo nombre de evento registrada en el sistema."); 
    	        //contexto.addMessage("registroCatastrofe", messages);
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
    			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "La catástrofe fue ingresada al sistema.");
    		}    		    
    		FacesContext.getCurrentInstance().addMessage(null, message);
    		return "success"; 
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en agregar catastrofe: " + excep.getMessage());      		 			       
	        return "failure";     		
    	}        	    	
	}
	
		
	
	
			
}
