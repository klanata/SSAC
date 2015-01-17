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

import com.core.data.entites.Filtro;
import com.core.service.negocio.remote.FiltroEBR;


@ManagedBean(name="listaFiltroBean")
@RequestScoped
public class ListaFiltroBean  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<FiltroBean> filtrosBean = new ArrayList<FiltroBean>();    
    private List<FiltroBean> filtroFiltroBean;
    
    private List<FiltroBean> selectedFiltro;    
    
    @ManagedProperty("#{filtroBean}")
    private FiltroBean filtroBean;
    
    
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
			//res = manager.listaFiltros();
			res = manager.listaFiltrosNoAsignadosAYoutube();
			Filtro filtro;
	    	Long id;	    	
	    	String descripcion;	    	
	    	Boolean bajaLogica;	    	
			for (int i=0; i<=res.size()-1; i++){    		
				filtro = res.get(i);
				id = filtro.getId();				
				descripcion = filtro.getDescripcion();												
				bajaLogica = filtro.isBajaLogica();				
				filtrosBean.add(i, new FiltroBean(id,descripcion,bajaLogica));									    		
			}	
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar los filtros: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }    
    
    //	------------------ Getter and setter methods ---------------------

	public ArrayList<FiltroBean> getFiltrosBean() {
		return filtrosBean;
	}

	public void setFiltrosBean(ArrayList<FiltroBean> filtrosBean) {
		this.filtrosBean = filtrosBean;
	}

	public List<FiltroBean> getFiltroFiltroBean() {
		return filtroFiltroBean;
	}

	public void setFiltroFiltroBean(List<FiltroBean> filtroFiltroBean) {
		this.filtroFiltroBean = filtroFiltroBean;
	}	

	public List<FiltroBean> getSelectedFiltro() {
		return selectedFiltro;
	}

	public void setSelectedFiltro(List<FiltroBean> selectedFiltro) {
		this.selectedFiltro = selectedFiltro;
	}

	public FiltroBean getFiltroBean() {
		return filtroBean;
	}

	public void setFiltroBean(FiltroBean filtroBean) {
		this.filtroBean = filtroBean;
	}
    
	//	------------------ Operaciones ---------------------
		
	
	public void asignar(){
							
		FiltroEBR manager = null;
		Context context = null;	
		FacesMessage message = null;
			
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
			
		if (selectedFiltro.size() > 0){ 				
			try{					
				FiltroBean filtroBean;
				
				for (int i=0; i<=selectedFiltro.size()-1; i++){ 				
						filtroBean = selectedFiltro.get(i);
						Long idFiltro = filtroBean.getId();								
						String fuente ="Youtube";
						manager.asignarFiltroServicio(idFiltro, fuente);
				}															
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Las Filtros fueron asignados a Youtube.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.getExternalContext().getFlash().setKeepMessages(true);
				
				ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				handler.performNavigation("filtrosSobreYoutube?faces-redirect=true");
														
			}catch (Exception excep){
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo dar de alta algunos de los filtros.");
					System.out.println("Excepción al asignar los filtros a Youtube: " + excep.getMessage());				
			}  
				
		}
		else{				
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe seleccionar al menos un filtro.");				
		}
		FacesContext.getCurrentInstance().addMessage(null, message);				
	}
	
	public void cancelar(){		
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("filtrosSobreYoutube?faces-redirect=true");				
	}


}
