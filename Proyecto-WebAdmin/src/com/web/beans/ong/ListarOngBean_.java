package com.web.beans.ong;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import clienteutility.ClienteUtility;

import com.core.data.entites.Ong;

import com.core.service.negocio.remote.OngEBR;


@ManagedBean(name="listarOngBean2")
@SessionScoped
public class ListarOngBean_ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OngBean> listaOngBean2 = new ArrayList<OngBean>();    

	private List<OngBean> filtroOngBean;
    
    private OngBean selectedOng;    
    
    @ManagedProperty("#{ongBean}")
    private OngBean ongBean;
    
    /*-----------------------*/
	public ArrayList<OngBean> getListaOngBean() {
		return listaOngBean2;
	}

	public void setListaOngBean(ArrayList<OngBean> listaOngBean) {
		this.listaOngBean2 = listaOngBean;
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
    	
		OngEBR manager = null;		
		Collection<Ong> lista = new ArrayList<Ong>();
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
		lista= manager.listarTodasLasOng(); 
			
		Iterator<Ong> it = lista.iterator();
		int i= 0;
		while(it.hasNext())
		{
			
			Ong res = it.next();
			Long id = res.getId();
			String nombre =  res.getNombre();   		
    		String descripcion = res.getDescripcion();
    		String citioWeb = res.getCitioWeb();
    		String telefono = res.getTelefono();
    		String email = res.getEmail();
    		String direccion = res.getDescripcion();
    		listaOngBean2.add(i, new OngBean(id, nombre, direccion, telefono, email, citioWeb, descripcion));
			i++;
		}													    		
			
				
    }    
	
	public void onRowSelect(SelectEvent event) {
		
		///Obtenego el string con el id del objeto
		Long id = ((OngBean) event.getObject()).getId();
		System.out.println("id del ong seleccionada: " + id);
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
