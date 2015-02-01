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

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Filtro;
import com.core.data.entites.Servicio;
import com.core.data.entites.FiltroServicio;
import com.core.service.negocio.remote.FiltroEBR;
import com.core.service.negocio.remote.ServicioEBR;
import com.core.service.negocio.remote.FiltroServicioEBR;


@ManagedBean(name="listaFiltrosDeDatosBean")
@RequestScoped
public class ListaFiltrosDeDatosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<FiltroDeDatosBean> filtrosFiltroDeDatosBean = new ArrayList<FiltroDeDatosBean>();    
    private List<FiltroDeDatosBean> filtroFiltroDeDatosBean; 
    
    private FiltroDeDatosBean selectedFiltroDeDatosBean;
    
    @ManagedProperty("#{filtroDeDatosBean}")
    private FiltroDeDatosBean filtroDeDatosBean;

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
			Catastrofe catastrofe;
	    	Long idFiltro;	 
	    	Long idServicio;
	    	Long idFiltroServicio;
	    	String nombreCatastrofe;
	    	String fuente;
	    	String descripcion;	    	
	    	Boolean bajaLogica;		    	
			for (int i=0; i<=listFiltroServicio.size()-1; i++){    		
				filtroServicio = listFiltroServicio.get(i);	
				idFiltroServicio = filtroServicio.getId();
				bajaLogica = filtroServicio.isBajaLogica();
								
				idFiltro = filtroServicio.getIdFiltro();
				idServicio = filtroServicio.getIdServicio();
				catastrofe = filtroServicio.getCatastrofe();
				if (catastrofe == null){ 
					nombreCatastrofe = "";					
				}
				else{
					nombreCatastrofe = catastrofe.getNombreEvento();
				}										
				filtro = manager.buscaFiltroPorId(idFiltro);
				servicio = managerS.buscarServicioPorId(idServicio);
				fuente = servicio.getFuente();
				//fuente = "Youtube";
				descripcion = filtro.getDescripcion();																
								
				filtrosFiltroDeDatosBean.add(i, new FiltroDeDatosBean(idFiltroServicio,fuente,descripcion,nombreCatastrofe,bajaLogica));												    
			}		
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar los filtros de youtube: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }
	
    //	------------------ Getter and setter methods ---------------------
      
	public ArrayList<FiltroDeDatosBean> getFiltrosFiltroDeDatosBean() {
		return filtrosFiltroDeDatosBean;
	}

	public void setFiltrosFiltroDeDatosBean(
			ArrayList<FiltroDeDatosBean> filtrosFiltroDeDatosBean) {
		this.filtrosFiltroDeDatosBean = filtrosFiltroDeDatosBean;
	}

	public List<FiltroDeDatosBean> getFiltroFiltroDeDatosBean() {
		return filtroFiltroDeDatosBean;
	}

	public void setFiltroFiltroDeDatosBean(
			List<FiltroDeDatosBean> filtroFiltroDeDatosBean) {
		this.filtroFiltroDeDatosBean = filtroFiltroDeDatosBean;
	}

	public FiltroDeDatosBean getSelectedFiltroDeDatosBean() {
		return selectedFiltroDeDatosBean;
	}

	public void setSelectedFiltroDeDatosBean(
			FiltroDeDatosBean selectedFiltroDeDatosBean) {
		this.selectedFiltroDeDatosBean = selectedFiltroDeDatosBean;
	}

	public FiltroDeDatosBean getFiltroDeDatosBean() {
		return filtroDeDatosBean;
	}

	public void setFiltroDeDatosBean(FiltroDeDatosBean filtroDeDatosBean) {
		this.filtroDeDatosBean = filtroDeDatosBean;
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
