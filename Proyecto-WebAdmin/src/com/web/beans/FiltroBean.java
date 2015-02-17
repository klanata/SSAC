package com.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Filtro;
import com.core.data.entites.Servicio;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.FiltroEBR;
import com.core.service.negocio.remote.FiltroServicioEBR;
import com.core.service.negocio.remote.ServicioEBR;


@ManagedBean(name="filtroBean")
@SessionScoped
public class FiltroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;			
	private String descripcion;						
	private boolean bajaLogica;
	
	private ServicioBean servicio;   
    private List<ServicioBean> servicios;
     
    @ManagedProperty("#{fuenteService}")
    private FuenteService service;
	
	
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
	public FuenteService getService() {
		return service;
	}
	public ServicioBean getServicio() {
		return servicio;
	}
	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}
	public List<ServicioBean> getServicios() {
		return servicios;
	}
	public void setServicios(List<ServicioBean> servicios) {
		this.servicios = servicios;
	}
	public void setService(FuenteService service) {
		this.service = service;
	}
		
	//	------------------ Operaciones ---------------------
		
	@PostConstruct
    public void init() {				
		servicios = service.getServiciosBean();		
	}
		
	
	public void registrar(){
		FacesMessage message = null; 
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{											
			if ((descripcion != null) && (descripcion.isEmpty() != true)){	
				FiltroEBR manager = null;					
				Context context = null;									
				
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
		    		Long idFiltro = manager.ingesarFiltro(descripcion);       		    	
		    		if (idFiltro == 0){    			
		    			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo ingresar el fitro al sistema intentelo más tarde.");	    	        
		    			FacesContext.getCurrentInstance().addMessage(null, message);
		    		}
		    		else {    	    			   	
		        		this.descripcion = "";       
		    			//System.out.println("no es repetido." + in);
		    			//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "El filtro fue ingresada al sistema.");
		        		
		        		FiltroEBR managerFiltro = null;
		        		Context contextFiltro = null;		        		
		        			
		        		try {
		        			// 1. Obtaining Context
		        			contextFiltro = ClienteUtility.getInitialContext();
		        	        // 2. Generate JNDI Lookup name
		        	        //String lookupName = getLookupName();
		        	        // 3. Lookup and cast
		        			managerFiltro = (FiltroEBR) contextFiltro.lookup("ejb:Proyecto-EAR/Proyecto-Core//FiltroEB!com.core.service.negocio.remote.FiltroEBR");
		        	 
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
		        				        		
						CatastrofeEBR managerC = null;
						Context contextC = null;							
						
						try {
				            // 1. Obtaining Context
							contextC = ClienteUtility.getInitialContext();
				            // 2. Generate JNDI Lookup name
				            //String lookupName = getLookupName();
				            // 3. Lookup and cast
							managerC = (CatastrofeEBR) contextC.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
				 
				        } catch (NamingException e) {
				            e.printStackTrace();
				        }
		        		
		        		try{
		        			Long idCatastrofe = new Long(idEventoString);
		    				Filtro filtro;
		    				Servicio servicio;
		    				 					    							    				
		    				filtro = managerFiltro.buscaFiltroPorId(idFiltro);
		    				
		    				String idServicioString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idServicioString");
		    				Long idServicio = new Long(idServicioString);
		    				Servicio serv = managerS.buscarServicioPorId(idServicio);
		    				String fuente = serv.getFuente();
		    				//String fuente ="Youtube";
		    						
		    				boolean existeServicio = managerS.ExisteServicio(fuente);
		    						
		    				if (existeServicio == false) {
		    					managerS.ingresarServicio(fuente);			
		    				}						
		    				servicio = managerS.buscarServicioPorFuente(fuente);
		    								    				
		    				Long idFiltroServicio = managerFS.ingesarFiltroServicio(filtro, servicio);
		    				
		    				managerC.asignarFiltroServicioALaCatastrofe(idCatastrofe, idFiltroServicio);
		    						    						    				
		    				//System.out.println("idServicioString: " + idServicioString);
		    					    				    			
		    				ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		    				handler.performNavigation("asignarServiciosCatastrofe?faces-redirect=true");	
		    						
		        		}
		        		catch (Exception excep){
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo dar de alta algunos de los filtros.");
							FacesContext.getCurrentInstance().addMessage(null, message);
							System.out.println("Excepción al asignar el filtro a Youtube: " + excep.getMessage());				
		        		}  	        			        	
		        		
		    		}    		        			    		 
		    		
		    	}catch (Exception excep){
		    		System.out.println("Excepcion en agregar el filtro: " + excep.getMessage());      		 			       		            	
		    	}      
				
			}
			else{		
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "La descripción del filtro es obligatoria.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				
			}
			 
		}
	}	
	
	
	public void cancelar(){
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("asignarServiciosCatastrofe?faces-redirect=true");		
	}
	
	
	
	

}
