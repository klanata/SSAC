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
	
	
	@ManagedProperty("#{filtroServicioBean}")
	FiltroServicioBean filtroServicioBean;
	
	private List<FiltroServicioBean> listaFiltrosServicioBean = new ArrayList<FiltroServicioBean>();
	
	private List<FiltroServicioBean> filtroFiltroServicioBean;
    
    private List<FiltroServicioBean> selectedFiltroServicioBean;
    
    
	
	
	@ManagedProperty("#{filtroYoutubeBean}")
	FiltroYoutubeBean filtroYoutubeBean;
	
	private List<FiltroYoutubeBean> filtrosYoutube = new ArrayList<FiltroYoutubeBean>();
	
	private List<FiltroYoutubeBean> filtroFiltroYoutubeBean;
    
    private List<FiltroYoutubeBean> selectedFiltroYoutubeBean;
	
	
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
    				System.out.println("Excepción en Youtube Bean al ver los datos de la catastrofe: " + excep.getMessage());      		 			       	            	
    			}  
    			
    			//lista todos los filtros en youtube asignados a la catastrofe
    			CatastrofeEBR manager2 = null;				
    			Context context2 = null;			
    			//FacesMessage message = null; 
    			
    			try {
    	            // 1. Obtaining Context
    	            context2 = ClienteUtility.getInitialContext();
    	            // 2. Generate JNDI Lookup name
    	            //String lookupName = getLookupName();
    	            // 3. Lookup and cast
    	            manager2 = (CatastrofeEBR) context2.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
    	 
    	        } catch (NamingException e) {
    	            e.printStackTrace();
    	        }
    		
    			try{
    				Long idCat = new Long(idEventoString);
    				
    				List<Filtro> lista = new ArrayList<Filtro>();
    				String fuenteCat = "Youtube";
    				lista = manager2.filtrosAsingadosACatastrofe(idCat, fuenteCat);
    				Filtro filtroCat;
    		    	Long idF;
    		    	String descripcionF;
    		    	    		    	
    		    	for (int i=0; i<=lista.size()-1; i++){    	
    		    		
    		    		filtroCat = lista.get(i);
    		    		idF = filtroCat.getId();
    		    		descripcionF = filtroCat.getDescripcion();
    		    		listaFiltrosServicioBean.add(i, new FiltroServicioBean(idF,descripcionF,fuenteCat));    		    		    				
    				}	
    												 																		    						    				    														         		
    		    }catch (Exception excep){
    				System.out.println("Excepción en Youtube Bean al ver los datos de la catastrofe: " + excep.getMessage());      		 			       	            	
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
    					filtrosYoutube.add(i, new FiltroYoutubeBean(id,descripcion,bajaLogica));																	    		
    				}				
    				
    			}catch (Exception excep){
    	    		System.out.println("Excepción al listar todas las catástrofes youtube: " + excep.getMessage());      		 			       	           	
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

	public List<FiltroYoutubeBean> getFiltrosYoutube() {
		return filtrosYoutube;
	}

	public void setFiltrosYoutube(List<FiltroYoutubeBean> filtrosYoutube) {
		this.filtrosYoutube = filtrosYoutube;
	}

	public FiltroYoutubeBean getFiltroYoutubeBean() {
		return filtroYoutubeBean;
	}

	public void setFiltroYoutubeBean(FiltroYoutubeBean filtroYoutubeBean) {
		this.filtroYoutubeBean = filtroYoutubeBean;
	}
	
	public List<FiltroYoutubeBean> getFiltroFiltroYoutubeBean() {
		return filtroFiltroYoutubeBean;
	}


	public void setFiltroFiltroYoutubeBean(List<FiltroYoutubeBean> filtroFiltroYoutubeBean) {
		this.filtroFiltroYoutubeBean = filtroFiltroYoutubeBean;
	}


	public List<FiltroYoutubeBean> getSelectedFiltroYoutubeBean() {
		return selectedFiltroYoutubeBean;
	}


	public void setSelectedFiltroYoutubeBean(
			List<FiltroYoutubeBean> selectedFiltroYoutubeBean) {
		this.selectedFiltroYoutubeBean = selectedFiltroYoutubeBean;
	}
	
	public FiltroServicioBean getFiltroServicioBean() {
		return filtroServicioBean;
	}


	public void setFiltroServicioBean(FiltroServicioBean filtroServicioBean) {
		this.filtroServicioBean = filtroServicioBean;
	}


	public List<FiltroServicioBean> getListaFiltrosServicioBean() {
		return listaFiltrosServicioBean;
	}


	public void setListaFiltrosServicioBean(
			List<FiltroServicioBean> listaFiltrosServicioBean) {
		this.listaFiltrosServicioBean = listaFiltrosServicioBean;
	}


	public List<FiltroServicioBean> getFiltroFiltroServicioBean() {
		return filtroFiltroServicioBean;
	}


	public void setFiltroFiltroServicioBean(
			List<FiltroServicioBean> filtroFiltroServicioBean) {
		this.filtroFiltroServicioBean = filtroFiltroServicioBean;
	}


	public List<FiltroServicioBean> getSelectedFiltroServicioBean() {
		return selectedFiltroServicioBean;
	}


	public void setSelectedFiltroServicioBean(
			List<FiltroServicioBean> selectedFiltroServicioBean) {
		this.selectedFiltroServicioBean = selectedFiltroServicioBean;
	}
	
	//	------------------ Operaciones ---------------------
	
	/*
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
	*/
	
	


	public void asignarFiltroCatastrofe(){
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeYoutube");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listaCatastrofesServiciosYoutube?faces-redirect=true");
		}
		else	
		{		
			Long idCatastrofe = new Long(idEventoString);		
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
			
			if (selectedFiltroYoutubeBean.size() > 0){ 				
				try{					
					FiltroYoutubeBean filtroYoutubeBean;	
					
					for (int i=0; i<=selectedFiltroYoutubeBean.size()-1; i++){ 				
						filtroYoutubeBean = selectedFiltroYoutubeBean.get(i);
						Long idFiltro = filtroYoutubeBean.getId();			
						//manager.agregarOngALaCatastrofe(idCatastrofe, idOng);	
						manager.asignarFiltroYoutubeALaCatastrofe(idCatastrofe, idFiltro);
					}			
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Los filtros fueron ingresadas a la catástrofe.");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeYoutube", "");
					//ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					//handler.performNavigation("listaCatastrofesServiciosYoutube?faces-redirect=true");
					//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Las ONGs fueron ingresadas al sistema.");
					
														
				}catch (Exception excep){
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo dar de alta alguno de los filtros.");
					System.out.println("Excepción al agregar las filtros a la catástrofe: " + excep.getMessage());				
				}  
				
			}
			else{				
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe seleccionar al menos una filtro.");
				
			}
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public void cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeYoutube", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listaCatastrofesServiciosYoutube?faces-redirect=true");
		
	}

	
	

}