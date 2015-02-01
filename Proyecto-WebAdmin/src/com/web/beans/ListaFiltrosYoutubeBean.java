package com.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Filtro;
import com.core.data.entites.Servicio;
import com.core.data.entites.FiltroServicio;
import com.core.service.negocio.remote.FiltroEBR;
import com.core.service.negocio.remote.ServicioEBR;
import com.core.service.negocio.remote.FiltroServicioEBR;


@ManagedBean(name="listaFiltrosYoutubeBean")
@RequestScoped
public class ListaFiltrosYoutubeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<FiltroYoutubeBean> filtrosYoutubeBean = new ArrayList<FiltroYoutubeBean>();    
    private List<FiltroYoutubeBean> filtroTablaFiltroYoutubeBean; 
    
    private FiltroYoutubeBean selectedFiltroYoutubeBean;
    
    @ManagedProperty("#{filtroYoutubeBean}")
    private FiltroYoutubeBean filtroYoutubeBean;

	@PostConstruct
    public void init() {
    	
		FiltroEBR manager = null;	    	
		Context context = null;		
		//FacesMessage message = null;
		
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
			/*
			List<Filtro> res = new ArrayList<Filtro>();
			res = manager.listaFiltrosYoutube();    	
			Filtro filtro;			
	    	Long id;	 
	    	String fuente;
	    	String descripcion;	    	
	    	Boolean bajaLogica;		    	
			for (int i=0; i<=res.size()-1; i++){    		
				filtro = res.get(i);				
				id = filtro.getId();	
				fuente = "Youtube";
				descripcion = filtro.getDescripcion();																
				bajaLogica = filtro.isBajaLogica();				
				filtrosYoutubeBean.add(i, new FiltroYoutubeBean(id,fuente,descripcion,bajaLogica));												    
			}		
			*/
			List<FiltroServicio> listFiltroServicio = new ArrayList<FiltroServicio>();
			listFiltroServicio = managerFS.listaFiltroServicios();
			FiltroServicio filtroServicio;	
			Filtro filtro;
			Servicio servicio;
	    	Long idFiltro;	 
	    	Long idServicio;
	    	String fuente;
	    	String descripcion;	    	
	    	Boolean bajaLogica;		    	
			for (int i=0; i<=listFiltroServicio.size()-1; i++){    		
				filtroServicio = listFiltroServicio.get(i);				
				idFiltro = filtroServicio.getIdFiltro();
				idServicio = filtroServicio.getIdServicio();
				filtro = manager.buscaFiltroPorId(idFiltro);
				servicio = managerS.buscarServicioPorId(idServicio);
				fuente = servicio.getFuente();
				//fuente = "Youtube";
				descripcion = filtro.getDescripcion();																
				bajaLogica = filtro.isBajaLogica();				
				filtrosYoutubeBean.add(i, new FiltroYoutubeBean(idFiltro,fuente,descripcion,bajaLogica));												    
			}		
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar los filtros de youtube: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }
	
    //	------------------ Getter and setter methods ---------------------
        
	public ArrayList<FiltroYoutubeBean> getFiltrosYoutubeBean() {
		return filtrosYoutubeBean;
	}


	public void setFiltrosYoutubeBean(
			ArrayList<FiltroYoutubeBean> filtrosYoutubeBean) {
		this.filtrosYoutubeBean = filtrosYoutubeBean;
	}


	public List<FiltroYoutubeBean> getFiltroTablaFiltroYoutubeBean() {
		return filtroTablaFiltroYoutubeBean;
	}


	public void setFiltroTablaFiltroYoutubeBean(
			List<FiltroYoutubeBean> filtroTablaFiltroYoutubeBean) {
		this.filtroTablaFiltroYoutubeBean = filtroTablaFiltroYoutubeBean;
	}


	public FiltroYoutubeBean getFiltroYoutubeBean() {
		return filtroYoutubeBean;
	}


	public void setFiltroYoutubeBean(FiltroYoutubeBean filtroYoutubeBean) {
		this.filtroYoutubeBean = filtroYoutubeBean;
	}

	public FiltroYoutubeBean getSelectedFiltroYoutubeBean() {
		return selectedFiltroYoutubeBean;
	}

	public void setSelectedFiltroYoutubeBean(FiltroYoutubeBean selectedFiltroYoutubeBean) {
		this.selectedFiltroYoutubeBean = selectedFiltroYoutubeBean;
	}
	
	//	------------------ Operaciones ---------------------
	
	public void asignarFiltro(){
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("asignarFiltroYoutube?faces-redirect=true");			
	}
	
	public void borrarFiltro(){			
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("quitarImagenCatastrofe?faces-redirect=true");		
	}
    
	
}
