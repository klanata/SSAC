package com.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="cssBean")
@RequestScoped
public class CSSBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();

    
	@PostConstruct
    public void init() {
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		//System.out.println("El id del evento: " + idEventoString);		
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe en el plan de riesgo. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{											
			CatastrofeEBR managerCat = null;
			Context contextCat = null;				
			
			try {
	            // 1. Obtaining Context
				contextCat = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
				managerCat = (CatastrofeEBR) contextCat.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
					
			try{	
				Long idCatastrofe = new Long(idEventoString);
				Catastrofe catastrofe = new Catastrofe();
				catastrofe = managerCat.buscaCatastrofePorId(idCatastrofe); 				
											
		    	String nombreEvento;
		    	String descripcionCatastrofe;
		    	String logo;
		    	double coordenadasX;
		    	double coordenadasY;
		    	Boolean activa;
		    	Boolean prioridad;
		    	String css;
		    	
				nombreEvento = catastrofe.getNombreEvento();
				descripcionCatastrofe = catastrofe.getDescripcion();												
				logo = catastrofe.getLogo();																								
				coordenadasX = catastrofe.getCoordenadasX();
				coordenadasY = catastrofe.getCoordenadasY();
				activa = catastrofe.getActiva();
				prioridad = catastrofe.getPrioridad();
				css = catastrofe.getCss();
							
				//setCatastrofeBean(new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad));
				
				catastrofeBean = new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad,css);
			
			}catch (Exception excep){
				System.out.println("Excepción al obtener la catástrofe en el plan de riesgo: " + excep.getMessage());      		 			       	           	
			}  
		} 
		
	}        
    
	//	------------------ Getter and setter methods ---------------------
    
	public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}

	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}
	
	
	

}
