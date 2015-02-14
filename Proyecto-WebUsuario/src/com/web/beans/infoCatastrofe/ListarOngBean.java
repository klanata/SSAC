package com.web.beans.infoCatastrofe;


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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import clienteutility.ClienteUtility;

import com.core.data.entites.Ong;
import com.core.service.negocio.remote.CatastrofeEBR;

@ManagedBean(name="listarOngUsuarioBean")
@RequestScoped
public class ListarOngBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<OngBean> listaOngBean = new ArrayList<OngBean>();    

	private List<OngBean> filtroOngBean;
    
    private OngBean selectedOng;    
    
    private SelectItem auxong;
    private String ong;  
	private List<SelectItem> ongs;
	private ArrayList<SelectItem> listaongs = new ArrayList<SelectItem>();
	
    
    public ArrayList<SelectItem> getListaongs() {
		return listaongs;
	}

	public void setListaongs(ArrayList<SelectItem> listaongs) {
		this.listaongs = listaongs;
	}

	

	public SelectItem getAuxong() {
		return auxong;
	}

	public void setAuxong(SelectItem auxong) {
		this.auxong = auxong;
	}



	@ManagedProperty("#{ongUsuarioBean}")
    private OngBean ongBean;
    
    /*-----------------------*/
    
    
	public ArrayList<OngBean> getListaOngBean() {
		return listaOngBean;
	}

	public String getOng() {
		return ong;
	}

	public void setOng(String ong) {
		this.ong = ong;
	}

	public List<SelectItem> getOngs() {
		return ongs;
	}

	public void setOngs(List<SelectItem> ongs) {
		this.ongs = ongs;
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
	
	
	
	@SuppressWarnings("null")
	@PostConstruct
    public void init() {
    	
		CatastrofeEBR manager = null;		
		//boolean encontre= false;
		Context context = null;
		//Catastrofe catastrofe = null;		
		//FacesMessage message = null; 
		Ong ong = null;
		
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
			Collection<Ong> res = new ArrayList<Ong>();
			//*probar sino borrar
				
			///borrar
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
			Long idCatastrofe = (Long)sesion.getAttribute("idmongo");

			//Long idCatastrofe=  new Long(1);
			res = manager.listaOngDeCatastrofe(idCatastrofe);   				
			//Ong ong;
	    	Long id;
	    	String nombre;
	    	String direccion;
	    	String telefono;
	    	String email;
	    	String citioWeb;
	    	String descripcion;
	    	String imagen;
	    	Iterator<Ong> it = res.iterator();
	    	int i = 0;
	    	SelectItemGroup g1 = new SelectItemGroup("Ongs");
	    	int tope = res.size() -1;
	    	SelectItem[] selectItems = new SelectItem[tope];
	    	while(it.hasNext()){
			//for (int i=0; i<=res.size()-1; i++){    		
				ong = it.next();
				
				id = ong.getId();
				nombre = ong.getNombre();
				direccion = ong.getDireccion();												
				telefono = ong.getTelefono();																					
				email = ong.getEmail();
				citioWeb = ong.getsitioWeb();
				descripcion = ong.getDescripcion();			
				imagen = ong.getImagen();
				listaongs.add(i, new SelectItem(ong.getNombre(),ong.getNombre()));
							
				listaOngBean.add(i, new OngBean(id,nombre,direccion,telefono,email,citioWeb,descripcion,imagen));	
				i++;
			}	
	    	g1.setSelectItems(selectItems );
	    	 ongs = new ArrayList<SelectItem>();
	         ongs.add(g1);
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar las ONGs: " + excep.getMessage());      		 			       	           	
    	}  	
	}

				
     
	
	public void onRowSelect(SelectEvent event) {
		
		///Obtenego el string con el id del objeto
		Long id = ((OngBean) event.getObject()).getId();
		System.out.println("id del ong seleccionada: " + id);
		//Pasarlo a string cuando lo mandemos por sesion
		String idEvento = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoOngEditar", idEvento); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("modificacionOng?faces-redirect=true");						 														

		            
    }
	
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Ong No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
}
