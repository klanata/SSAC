package com.web.beans.ong;

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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import clienteutility.ClienteUtility;

import com.core.data.entites.Ong;
import com.core.service.negocio.remote.OngEBR;


@ManagedBean(name="listarOngBean2")
@RequestScoped
public class ListarOngBean_ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OngBean> listaOngBean = new ArrayList<OngBean>();    

	private List<OngBean> filtroOngBean;
    
    private OngBean selectedOng;    
    
    @ManagedProperty("#{ongBean}")
    private OngBean ongBean;
    
    /*-----------------------*/
	public ArrayList<OngBean> getListaOngBean() {
		return listaOngBean;
	}

	public void setListaOngBean(ArrayList<OngBean> listaOngBean) {
		this.listaOngBean = listaOngBean;
	}

	public List<OngBean> getFiltroOngBean() {
		return filtroOngBean;
	}

	public void setFiltroOngBean(List<OngBean> filtroOngBean) {
		this.filtroOngBean = filtroOngBean;
	}

	public OngBean getSelectedOng() {
		return selectedOng;
	}

	public void setSelectedOng(OngBean selectedOng) {
		this.selectedOng = selectedOng;
	}

	public OngBean getOngBean() {
		return ongBean;
	}

	public void setOngBean(OngBean ongBean) {
		this.ongBean = ongBean;
	}
	/*---------------------------------------------------------------------------------*/
	
	@PostConstruct
    public void init() {
    	
		//Creo la lista de todas las ONGs
		OngEBR manager = null;	    	
		Context context = null;			
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (OngEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
					
		try{			
			List<Ong> res = new ArrayList<Ong>();
			res = manager.listarTodasLasOng();   				
			Ong ong;
	    	Long id;
	    	String nombre;
	    	String direccion;
	    	String telefono;
	    	String email;
	    	String citioWeb;
	    	String descripcion;
	    	String imagen;
			for (int i=0; i<=res.size()-1; i++){    		
				ong = res.get(i);
				id = ong.getId();
				nombre = ong.getNombre();
				direccion = ong.getDireccion();												
				telefono = ong.getTelefono();																					
				email = ong.getEmail();
				citioWeb = ong.getsitioWeb();
				descripcion = ong.getDescripcion();	
				imagen = ong.getImagen();
				listaOngBean.add(i, new OngBean(id,nombre,direccion,telefono,email,citioWeb,descripcion,imagen));	
				
			}	
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar las ONGs: " + excep.getMessage());      		 			       	           	
    	}  	
	}	
	
	
	public void onRowSelect(SelectEvent event) {		
		///Obtenego el string con el id del objeto
		Long id = ((OngBean) event.getObject()).getId();
	
		//Pasarlo a string cuando lo mandemos por sesion
		String idEvento = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoOngEliminar", idEvento); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("bajaOng?faces-redirect=true");						 														
		         
    }
	
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Ong No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
}
