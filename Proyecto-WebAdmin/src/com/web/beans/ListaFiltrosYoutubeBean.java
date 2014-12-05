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
import com.core.service.negocio.remote.FiltroEBR;


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
					
		try{	
			
			List<Filtro> res = new ArrayList<Filtro>();
			res = manager.listaFiltrosYoutube();    	
			Filtro filtro;			
	    	Long id;	    	
	    	String descripcion;	    	
	    	Boolean bajaLogica;		    	
			for (int i=0; i<=res.size()-1; i++){    		
				filtro = res.get(i);				
				id = filtro.getId();				
				descripcion = filtro.getDescripcion();																
				bajaLogica = filtro.isBajaLogica();				
				filtrosYoutubeBean.add(i, new FiltroYoutubeBean(id,descripcion,bajaLogica));												    
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
