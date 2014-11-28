package com.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="listaCatastrofesBean")
@RequestScoped
public class ListaCatastrofesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<CatastrofeBean> catastrofesBean = new ArrayList<CatastrofeBean>();    
    private List<CatastrofeBean> filtroCatastrofeBean;
    
    private CatastrofeBean selectedCatastrofe;    
    
    @ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean;
    
    
	@PostConstruct
    public void init() {
    	
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
			List<Catastrofe> res = new ArrayList<Catastrofe>();
			res = manager.listaCatastrofes();    				
			Catastrofe catastrofe;
	    	Long id;
	    	String nombreEvento;
	    	String descripcion;
	    	String logo;
	    	double coordenadasX;
	    	double coordenadasY;
	    	Boolean activa;
	    	Boolean prioridad;	    	
			for (int i=0; i<=res.size()-1; i++){    		
				catastrofe = res.get(i);
				id = catastrofe.getId();
				nombreEvento = catastrofe.getNombreEvento();
				descripcion = catastrofe.getDescripcion();												
				logo = catastrofe.getLogo();																					
				//System.out.println(logo);
				coordenadasX = catastrofe.getCoordenadasX();
				coordenadasY = catastrofe.getCoordenadasY();
				activa = catastrofe.getActiva();
				prioridad = catastrofe.getPrioridad();
				catastrofesBean.add(i, new CatastrofeBean(id,nombreEvento,descripcion,logo,coordenadasX,coordenadasY,activa,prioridad));									    		
			}	
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar las catástrofes: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }    
    
    //	------------------ Getter and setter methods ---------------------
        
    public ArrayList<CatastrofeBean> getCatastrofesBean() {
		return catastrofesBean;
	}
	
	public void setCatastrofesBean(ArrayList<CatastrofeBean> catastrofesBean) {
		this.catastrofesBean = catastrofesBean;
	}
	
	public List<CatastrofeBean> getFiltroCatastrofeBean() {
		return filtroCatastrofeBean;
	}
	
	public void setFiltroCatastrofeBean(List<CatastrofeBean> filtroCatastrofeBean) {
		this.filtroCatastrofeBean = filtroCatastrofeBean;
	}
	
	public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}
	
	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}
    

	public CatastrofeBean getSelectedCatastrofe() {
		return selectedCatastrofe;
	}


	public void setSelectedCatastrofe(CatastrofeBean selectedCatastrofe) {
		this.selectedCatastrofe = selectedCatastrofe;
	}
    		
	//	------------------ Operaciones ---------------------
	
	public void onRowSelect(SelectEvent event) {
		
		Long id = ((CatastrofeBean) event.getObject()).getId();
		System.out.println("id de la catastrofe seleccionada: " + id);
		String idEvento = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", idEvento); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("asignarOngCatastrofe?faces-redirect=true");						 														
		
		//ConfigurableNavigationHandler.performNavigation("asignarOngCatastrofe?faces-redirect=true");
		//return "asignarOngCatastrofe?faces-redirect=true";
		            
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Catastrofe No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
