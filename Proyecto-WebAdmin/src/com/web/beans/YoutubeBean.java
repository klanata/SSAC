package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Filtro;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.FiltroEBR;

@ManagedBean(name="youtubeBean")
@RequestScoped
public class YoutubeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	FiltroYoutubeBean filtroYoutubeBean;
	
	private List<FiltroYoutubeBean> filtroYoutubes = new ArrayList<FiltroYoutubeBean>();
	
	
	@PostConstruct
    public void init() {
		
    		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeYoutube");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    			System.out.println("No existe la catástrofe. "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");
    		}
    		else	
    		{        
    	        CatastrofeEBR manager = null;				
    			Context context = null;			
    			//FacesMessage message = null; 
    			
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
    				Long idCatastrofe = new Long(idEventoString);
    				
    				Catastrofe catastrofe = new Catastrofe();
					catastrofe = manager.buscaCatastrofePorId(idCatastrofe); 				
												
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
					css = null;
					catastrofeBean = new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad,css);    				    														         			
    		    }catch (Exception excep){
    				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
    			}  
    		}  
            
            // listo los filtros que tiene youtube
            FiltroEBR managerF = null;				
			Context contextF = null;			
			//FacesMessage message = null; 
			
			try {
	            // 1. Obtaining Context
	            contextF = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
	            managerF = (FiltroEBR) contextF.lookup("ejb:Proyecto-EAR/Proyecto-Core//FiltroEB!com.core.service.negocio.remote.FiltroEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
            
			try{				
				List<Filtro> listaFiltro = new ArrayList<Filtro>();
				listaFiltro = managerF.listaFiltrosYoutube();
				Filtro filtro;
		    	Long id;
		    	String descripcion;
		    	boolean bajaLogica;
		    	
		    	for (int i=0; i<=listaFiltro.size()-1; i++){    							
					filtro = listaFiltro.get(i);
					id = filtro.getId();
					descripcion = filtro.getDescripcion();
					bajaLogica = filtro.isBajaLogica();
					filtroYoutubes.add(i, new FiltroYoutubeBean(id,descripcion,bajaLogica));																	    		
				}				
				
			}catch (Exception excep){
	    		System.out.println("Excepción al listar las catástrofes youtube: " + excep.getMessage());      		 			       	           	
	    	} 			                                    
                        
    }
	
		
	//	------------------ Getter and setter methods ---------------------
	

	public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}

	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}

	public List<FiltroYoutubeBean> getFiltroYoutubes() {
		return filtroYoutubes;
	}

	public void setFiltroYoutubes(List<FiltroYoutubeBean> filtroYoutubes) {
		this.filtroYoutubes = filtroYoutubes;
	}

	public FiltroYoutubeBean getFiltroYoutubeBean() {
		return filtroYoutubeBean;
	}

	public void setFiltroYoutubeBean(FiltroYoutubeBean filtroYoutubeBean) {
		this.filtroYoutubeBean = filtroYoutubeBean;
	}
	
	//	------------------ Operaciones ---------------------
	
	public void asignarServicios(){
		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeYoutube");
        if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");
		}
		else	
		{
		
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
	    		Long idCatastrofe = new Long(idEventoString);
	    			    		
	    		Long idServicio;
	    		idServicio = (long) 4;
				manager.asignarServicioALaCatastrofe(idCatastrofe, idServicio);       		    	    		    		
	    		//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "La catástrofe fue asignada al Servicio.");
	    		    		    
	    		//FacesContext.getCurrentInstance().addMessage(null, message);
	    		
	    		
	    	}catch (Exception excep){
	    		System.out.println("Excepcion al asignar servicio a la catastrofe: " + excep.getMessage());      		 			       
		           		
	    	}
		}
	}
	

}
