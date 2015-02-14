package com.web.beans;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.annotation.PostConstruct; 

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Filtro;
import com.core.data.entites.FiltroServicio;
import com.core.data.entites.Servicio;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.FiltroEBR;
import com.core.service.negocio.remote.FiltroServicioEBR;
import com.core.service.negocio.remote.ServicioEBR;


@ManagedBean(name="modificarFiltroCatastrofeBean")
@RequestScoped
public class ModificarFiltroCatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	@ManagedProperty("#{filtroServicioBean}")
    private FiltroServicioBean filtroServicioBean = new FiltroServicioBean();
				
	
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
	
	
	//	------------------ Operaciones ---------------------
				
	@PostConstruct
	public void init() {		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		String idFiltroServString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idFiltroServicioString");
        if ((idEventoString == null) || (idEventoString == "") || (idFiltroServString == null) || (idFiltroServString == ""))
		{	
			System.out.println("No existe la catástrofe al modificar FiltroServicio."); 			
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
				System.out.println("Excepción en borrarCatastrofeBeanMap: " + excep.getMessage());      		 			       	            	
			}  				
			
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
				Long idFiltroServicio = new Long(idFiltroServString);								
				FiltroServicio filtroServicio;
				filtroServicio = managerFS.buscaFiltroServicioPorId(idFiltroServicio);
				
				Long idFiltro;
				Long idServicio;
				Filtro filtro;
		    	Servicio servicio;		    	
				
		    	String fuente;
		    	String descripcion;	
		    	boolean bajaLogica;
		    	
		    	idFiltro = filtroServicio.getIdFiltro();
		    	idServicio = filtroServicio.getIdServicio();
		    	
		    	filtro = managerF.buscaFiltroPorId(idFiltro);
		    	servicio = managerS.buscarServicioPorId(idServicio);
				
				fuente = servicio.getFuente();
				descripcion = filtro.getDescripcion();
				bajaLogica = filtroServicio.isBajaLogica();
								
				filtroServicioBean = new FiltroServicioBean(idFiltroServicio,fuente,descripcion,bajaLogica);								
				    				
			}catch (Exception excep){
	    		System.out.println("Excepción al listar los filtros en FiltrosServiciosCatastrofeBean: " + excep.getMessage());      		 			       	           	
	    	} 
			
			
		}    	
	}

		
	public void modificarFiltroCatastrofe(){			
		String idFiltroServString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idFiltroServicioString");
		if ((idFiltroServString == null) || (idFiltroServString == ""))
		{	
			System.out.println("No existe el filtroServicio. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{ 			
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
				Long idFiltroServicio = new Long(idFiltroServString);												
				String descripcion = filtroServicioBean.getDescripcion();
				managerFS.modificarFiltroServicioCatastrofe(idFiltroServicio, descripcion);
				
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación Exitosa", "El filtro fue modificado.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.getExternalContext().getFlash().setKeepMessages(true);
	        	ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				handler.performNavigation("modificarFiltrosDeDatos?faces-redirect=true");											
				    				
			}catch (Exception excep){
	    		System.out.println("Excepción al modificar los filtros en FiltrosServiciosCatastrofeBean: " + excep.getMessage());      		 			       	           	
	    	} 
				
		}
		
	}
	
	public void cancelar(){	
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("modificarFiltrosDeDatos?faces-redirect=true");	
		
	}
	
			
}
