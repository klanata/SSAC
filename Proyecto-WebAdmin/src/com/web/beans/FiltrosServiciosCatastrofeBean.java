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


@ManagedBean(name="filtrosServiciosCatastrofeBean")
@RequestScoped
public class FiltrosServiciosCatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	
	@ManagedProperty("#{filtroServicioBean}")
	FiltroServicioBean filtroServicioBean;
	
	private List<FiltroServicioBean> listaFiltroServicioBean = new ArrayList<FiltroServicioBean>();
	
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
    				System.out.println("Excepción en FiltrosServiciosCatastrofeBean al ver los datos de la catastrofe: " + excep.getMessage());      		 			       	            	
    			}      			    			    			    	
    			
    			// Listo todos los filtros que tiene al menos un servicio asignado
    			/*
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
    				listaFiltro = managerF.listarFiltrosQueTieneAlMenosUnServicio();
    				Filtro filtro;
    		    	Long id;
    		    	String fuente;
    		    	String descripcion;
    		    	boolean bajaLogica;
    		    	Servicio servicio;
    		    	Set <Servicio> serviciosFiltro = new HashSet<Servicio>();    		        		    
    		    	
    		    	//int j = 0;
    		    	for (int i=0; i<=listaFiltro.size()-1; i++){    							
    					filtro = listaFiltro.get(i);
    					id = filtro.getId();
    					descripcion = filtro.getDescripcion();
    					bajaLogica = filtro.isBajaLogica();
    					    					
    					serviciosFiltro = filtro.getServicios();
    					    					
    					//fuente = "Youtube";
    					List <Servicio> listServicios = new ArrayList<Servicio>();
    					for (Servicio s : serviciosFiltro){
    						//fuente = s.getFuente();	
    						listServicios.add(s);
    						//listaFiltroServicioBean.add(j, new FiltroServicioBean(id,fuente,descripcion,bajaLogica));
    						//j = j + 1;
    					}    
    					for (int j=0; j<=listServicios.size()-1; j++){
    						servicio = listServicios.get(j);
    						fuente = servicio.getFuente();
    						listaFiltroServicioBean.add(j, new FiltroServicioBean(id,fuente,descripcion,bajaLogica));    						
    					}    					    					
    																						    		
    				}				
    				
    			}catch (Exception excep){
    	    		System.out.println("Excepción al listar los filtros en FiltrosServiciosCatastrofeBean: " + excep.getMessage());      		 			       	           	
    	    	}  
    	    	*/
    			
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
    			
    			ServicioEBR managerS = null;				
    			Context contextS = null;			
    			//FacesMessage message = null; 
    			
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
    			
    			// Listo todos los filtroServicio que no tienen asignada la catastrofe todavia
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
    				List<FiltroServicio> listaFiltroServicio = new ArrayList<FiltroServicio>();
    				listaFiltroServicio = managerFS.listaFiltroServiciosSinCatastrofe();
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
						
						listaFiltroServicioBean.add(i, new FiltroServicioBean(id,fuente,descripcion,bajaLogica));    						
					} 
    				    				
    			}catch (Exception excep){
    	    		System.out.println("Excepción al listar los filtros en FiltrosServiciosCatastrofeBean: " + excep.getMessage());      		 			       	           	
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


	public List<FiltroServicioBean> getListaFiltroServicioBean() {
		return listaFiltroServicioBean;
	}


	public void setListaFiltroServicioBean(
			List<FiltroServicioBean> listaFiltroServicioBean) {
		this.listaFiltroServicioBean = listaFiltroServicioBean;
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
			
			if (selectedFiltroServicioBean.size() > 0){ 				
				try{					
					FiltroServicioBean filtroServicioBean;	
					
					for (int i=0; i<=selectedFiltroServicioBean.size()-1; i++){ 				
						filtroServicioBean = selectedFiltroServicioBean.get(i);
						Long idFiltroServicio = filtroServicioBean.getId();															
						manager.asignarFiltroServicioALaCatastrofe(idCatastrofe, idFiltroServicio);
					}
										
					String abm = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ABMCatastrofe");
					if (abm=="Alta"){
						ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		    			handler.performNavigation("asignarCSSCatastrofe?faces-redirect=true");	
			        }
			        else{
			        	if (abm=="Modificacion"){
			        		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
							handler.performNavigation("vistaServiciosCatastrofe?faces-redirect=true");	
				        }
			        	else{	        		
			        		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			    			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");	        		
			        	}	        	
			        }															
	    			
				}catch (Exception excep){					
					System.out.println("Excepción al agregar las filtros a la catástrofe: " + excep.getMessage());				
				}  
				
			}
			else{				
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe seleccionar al menos una filtro.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
			
		}
		
	}
	
	
	public void cancelarAsignarFiltroCatastrofe(){
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("vistaServiciosCatastrofe?faces-redirect=true");		
	}
	
	public void cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeYoutube", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listaCatastrofesServiciosYoutube?faces-redirect=true");
		
	}

	
	

}