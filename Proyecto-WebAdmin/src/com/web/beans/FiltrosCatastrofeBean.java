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
import com.core.data.entites.Servicio;
import com.core.data.entites.FiltroServicio;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.FiltroEBR;
import com.core.service.negocio.remote.ServicioEBR;
import com.core.service.negocio.remote.FiltroServicioEBR;


@ManagedBean(name="filtrosCatastrofeBean")
@RequestScoped
public class FiltrosCatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	
	@ManagedProperty("#{filtroServicioBean}")
	FiltroServicioBean filtroServicioBean;
	
	private List<FiltroServicioBean> listaFiltrosServicioBean = new ArrayList<FiltroServicioBean>();
	
	private List<FiltroServicioBean> filtroFiltroServicioBean;
    
    private List<FiltroServicioBean> selectedFiltroServicioBean;
    
		
	@PostConstruct
    public void init() {
		
    		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    			System.out.println("No existe la catástrofe en youtubeBean. "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
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
    			
    			/*
    			//Lista los Filtros de datos asignados a la Catástrofe seleccionada			    			
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
    			
    			ServicioEBR managerServicio = null;				
    			Context contextS = null;			
    			//FacesMessage message = null; 
    			
    			try {
    	            // 1. Obtaining Context
    	            contextS = ClienteUtility.getInitialContext();
    	            // 2. Generate JNDI Lookup name
    	            //String lookupName = getLookupName();
    	            // 3. Lookup and cast
    	            managerServicio = (ServicioEBR) contextS.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServicioEB!com.core.service.negocio.remote.ServicioEBR");
    	 
    	        } catch (NamingException e) {
    	            e.printStackTrace();
    	        }
    		
    			try{
    				Long idCat = new Long(idEventoString);
    				
    				List<Servicio> listaServicios =managerServicio.listaServicios();
    				List<Filtro> lista = new ArrayList<Filtro>();
    				Servicio s;
    				
    				for (int i=0; i<=listaServicios.size()-1; i++){
    					
    					s = listaServicios.get(i);
    					String fuenteCat = s.getFuente();
    					lista = manager2.filtrosAsingadosACatastrofe(idCat, fuenteCat);
        				Filtro filtroCat;
        		    	Long idF;
        		    	String descripcionF;
        		    	boolean bajaLogicaF;
        		    	    		    	
        		    	for (int j=0; j<=lista.size()-1; j++){    	
        		    		
        		    		filtroCat = lista.get(j);
        		    		idF = filtroCat.getId();
        		    		descripcionF = filtroCat.getDescripcion();
        		    		bajaLogicaF = filtroCat.isBajaLogica();
        		    		listaFiltrosServicioBean.add(j, new FiltroServicioBean(idF,fuenteCat,descripcionF,bajaLogicaF));
        		    		//System.out.println("FiltroCatastrofeBean: " + fuenteCat);
        		    		//System.out.println("FiltroCatastrofeBean: " + listaFiltrosServicioBean.size());
        				}	
    				}    			    	    		
    				    												 																		    						    				    														         	
    		    }catch (Exception excep){
    				System.out.println("Excepción en FiltroCatastrofeBean al ver los filtros de datos de la catastrofe: " + excep.getMessage());      		 			       	            	
    			}      
    			*/			    			    			        		
    			
    			//Lista de los FiltroServicio de datos asignados a la Catástrofe seleccionada
    			
    			FiltroEBR managerF = null;				
    			Context contextF = null;			    			
    			
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
    			
    			ServicioEBR managerS = null;				
    			Context contextS = null;			    			
    			
    			try {
    	            // 1. Obtaining Context
    				contextS = ClienteUtility.getInitialContext();
    	            // 2. Generate JNDI Lookup name
    	            //String lookupName = getLookupName();
    	            // 3. Lookup and cast
    	            managerS = (ServicioEBR) contextS.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServicioEB!com.core.service.negocio.remote.ServicioEBR");
    	 
    	        } catch (NamingException e) {
    	            e.printStackTrace();
    	        }
    			
    			
    			FiltroServicioEBR managerFS = null;				
    			Context contextFS = null;			
    			//FacesMessage message = null; 
    			
    			try {
    	            // 1. Obtaining Context
    				contextFS = ClienteUtility.getInitialContext();
    	            // 2. Generate JNDI Lookup name
    	            //String lookupName = getLookupName();
    	            // 3. Lookup and cast
    	            managerFS = (FiltroServicioEBR) contextFS.lookup("ejb:Proyecto-EAR/Proyecto-Core//FiltroServicioEB!com.core.service.negocio.remote.FiltroServicioEBR");
    	 
    	        } catch (NamingException e) {
    	            e.printStackTrace();
    	        }
    			
    			try{
    				long idCat= new Long(idEventoString);			
    				List<FiltroServicio> listaFiltroServicio = new ArrayList<FiltroServicio>();
    				listaFiltroServicio = managerFS.listaFiltroServicioAsignadosCatastrofe(idCat);
    				Long id;
    				Long idFiltro;
    				Long idServicio;
    		    	String fuente;
    		    	String descripcion;
    		    	boolean bajaLogica;
    		    	Filtro filtro;
    		    	Servicio servicio;
    		    	FiltroServicio filtroServicio;
    		    	
    		    	for (int i=0; i<=listaFiltroServicio.size()-1; i++){
    					filtroServicio = listaFiltroServicio.get(i);
    					id = filtroServicio.getId();
    					bajaLogica = filtroServicio.isBajaLogica();
    					
    					idFiltro = filtroServicio.getIdFiltro();
    					filtro = managerF.buscaFiltroPorId(idFiltro);
    					descripcion = filtro.getDescripcion();
    					
    					idServicio = filtroServicio.getIdServicio();
    					servicio = managerS.buscarServicioPorId(idServicio);
    					fuente = servicio.getFuente();
						
    					listaFiltrosServicioBean.add(i, new FiltroServicioBean(id,fuente,descripcion,bajaLogica));    	    					
					}     				    		    	
    				
    			}catch (Exception excep){
    				System.out.println("Excepción en FiltroCatastrofeBean al ver los filtroServicios de datos de la catastrofe: " + excep.getMessage());      		 			       	            	
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
		
	public void siguiente(){		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{
			Long idCatastrofe = new Long(idEventoString);			
			FiltroServicioEBR managerFS = null;				
			Context contextFS = null;			
			FacesMessage message = null; 
			
			try {
	            // 1. Obtaining Context
				contextFS = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
	            managerFS = (FiltroServicioEBR) contextFS.lookup("ejb:Proyecto-EAR/Proyecto-Core//FiltroServicioEB!com.core.service.negocio.remote.FiltroServicioEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
			
			try{				
				List<FiltroServicio> listaFiltroServicio = new ArrayList<FiltroServicio>();
				listaFiltroServicio = managerFS.listaFiltroServicioAsignadosCatastrofe(idCatastrofe);				
				
				if (listaFiltroServicio.size() == 0){
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe ingresar al menos una filtro de datos a la catástrofe.");
	    			FacesContext.getCurrentInstance().addMessage(null, message);					
				}
				else{
					ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					handler.performNavigation("vistaCSSCatastrofe?faces-redirect=true");					
				}
												
			}catch (Exception excep){
	    		System.out.println("Excepcion en FiltrosCatastrofeBean en la lista de filtroServicios de la catastrofe: " + excep.getMessage());      		 			       
		           		
	    	}
												
		}
	}
	
	public void agregarFiltroServicios(){		
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("altaFiltro?faces-redirect=true");	
	}
	
	public void borrarFiltroServicios(){			
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("quitarServiciosCatastrofe?faces-redirect=true");		
	}
	
	public void cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("modificarCatastrofe?faces-redirect=true");		
	}
	
	public void quitarFiltroServicioCatastrofe(){		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{ 						
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
			
			if (selectedFiltroServicioBean.size() > 0){ 				
				try{					
					FiltroServicioBean filtroServicioBean;	
					
					for (int i=0; i<=selectedFiltroServicioBean.size()-1; i++){ 				
						filtroServicioBean = selectedFiltroServicioBean.get(i);
						Long idFiltroServicio = filtroServicioBean.getId();								
						//manager.eliminarFiltroServicioDeCatastrofe(idFiltroServicio);
						manager.bajaFiltroServicioDeCatastrofe(idFiltroServicio);
					}									
					ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					handler.performNavigation("vistaServiciosCatastrofe?faces-redirect=true");						
														
				}catch (Exception excep){
					//message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudieron quitar al menos una de las ONG.");
					System.out.println("Excepción al quitar los FiltrosServicios a la catástrofe: " + excep.getMessage());				
				}  
			}
			
		}
		
	}
	
	
	public void cancelarQuitarFiltroServicioCatastrofe(){		
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("vistaServiciosCatastrofe?faces-redirect=true");		
	}
	
	
}