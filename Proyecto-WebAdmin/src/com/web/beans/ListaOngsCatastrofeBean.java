package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.faces.bean.RequestScoped;

import clienteutility.ClienteUtility;

import com.core.data.entites.Ong;
import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.OngEBR;
import com.web.beans.ong.OngBean;

@ManagedBean(name="listaOngsBean")
@RequestScoped
public class ListaOngsCatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	
	private ArrayList<OngBean> ongsBeanCat = new ArrayList<OngBean>();    
    private List<OngBean> filtroOngBeanCat;
    
    private List<OngBean> selectedOngsCat;
    
    @ManagedProperty("#{ongBeanCat}")
    private OngBean ongBeanCat;
	
	
	private ArrayList<OngBean> ongsBean = new ArrayList<OngBean>();    
    private List<OngBean> filtroOngBean;    
    
    //private OngBean selectedOng;
    private List<OngBean> selectedOngs;
    
    @ManagedProperty("#{ongBean}")
    private OngBean ongBean;
    
           
	@PostConstruct
    public void init() {
		
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeONG");
			//System.out.println("El id del evento: " + idEventoString);		
			if ((idEventoString == null) || (idEventoString == ""))
			{	
				System.out.println("No existe la catástrofe. "); 			
				ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				handler.performNavigation("listaCatastrofesONGs?faces-redirect=true");
			}
			else	
			{											
				CatastrofeEBR managerCat = null;
				Context contextCat = null;				
				
				try {
		            // 1. Obtaining Context
					contextCat = ClienteUtility.getInitialContext();
		            // 2. Generate JNDI Lookup name
		            //String lookupName = getLookupName();
		            // 3. Lookup and cast
					managerCat = (CatastrofeEBR) contextCat.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
		 
		        } catch (NamingException e) {
		            e.printStackTrace();
		        }
						
				try{	
					Long idCatastrofe = new Long(idEventoString);
					Catastrofe catastrofe = new Catastrofe();
					catastrofe = managerCat.buscaCatastrofePorId(idCatastrofe); 				
												
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
					css = catastrofe.getCss();
					//setCatastrofeBean(new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad));
					
					catastrofeBean = new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad,css);
				
				}catch (Exception excep){
					System.out.println("Excepción al obtener la catástrofe: " + excep.getMessage());      		 			       	           	
				}  		
				
				
				//Creo la lista de ONGs que pertenecen a la catástrofe			
				CatastrofeEBR managerOngsCat = null;
				Context contextOngsCat = null;
				
				try {
		            // 1. Obtaining Context
					contextOngsCat = ClienteUtility.getInitialContext();
		            // 2. Generate JNDI Lookup name
		            //String lookupName = getLookupName();
		            // 3. Lookup and cast
					managerOngsCat = (CatastrofeEBR) contextOngsCat.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
		 
		        } catch (NamingException e) {
		            e.printStackTrace();
		        }
				try{			
					Long idCat = new Long(idEventoString);
					Collection<Ong> resOngCat = new ArrayList<Ong>();
					resOngCat = managerOngsCat.listaOngDeCatastrofe(idCat);  							
					if (resOngCat.size() != 0) {
						Long idOngCat;
				    	String nombreOngCat;
				    	String direccionOngCat;
				    	String telefonoOngCat;
				    	String emailOngCat;
				    	String citioWebOngCat;
				    	String descripcionOngCat;
				    	String imagen;
				    	int iOngCat = 0;
						for (Ong ongCatastrofe : resOngCat){   					
							idOngCat = ongCatastrofe.getId();
							nombreOngCat = ongCatastrofe.getNombre();
							//System.out.println("Excepción al listar las ONGs que pertenecen a la catástrofe2222: " +nombreOngCat);
							direccionOngCat = ongCatastrofe.getDireccion();												
							telefonoOngCat = ongCatastrofe.getTelefono();																					
							emailOngCat = ongCatastrofe.getEmail();
							citioWebOngCat = ongCatastrofe.getCitioWeb();
							descripcionOngCat = ongCatastrofe.getDescripcion();		
							imagen = ongCatastrofe.getImagen(); 
							ongsBeanCat.add(iOngCat, new OngBean(idOngCat,nombreOngCat,direccionOngCat,telefonoOngCat,emailOngCat,citioWebOngCat,descripcionOngCat,imagen));
										
						}		    	
					}
					else
						System.out.println("La catástrofe no tiene ONGs. ");					
					
		    	}catch (Exception excep){
		    		System.out.println("Excepción al listar las ONGs que pertenecen a la catástrofe: " + excep.getMessage());      		 			       	           	
		    	}  							
				
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
						citioWeb = ong.getCitioWeb();
						descripcion = ong.getDescripcion();	
						imagen = ong.getImagen();
						ongsBean.add(i, new OngBean(id,nombre,direccion,telefono,email,citioWeb,descripcion,imagen));									    		
					}	
					
		    	}catch (Exception excep){
		    		System.out.println("Excepción al listar las ONGs: " + excep.getMessage());      		 			       	           	
		    	}  	
			}
			
			//if (catastrofeBean == null) 
			//{
			//	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEvento", "");
			//	String mystring = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEvento");
			//	System.out.println("El id del evento my string: " + mystring);	
			//}		  	
		
	}

	
	//	------------------ Getter and setter methods ---------------------

	public ArrayList<OngBean> getOngsBean() {
		return ongsBean;
	}
	public void setOngsBean(ArrayList<OngBean> ongsBean) {
		this.ongsBean = ongsBean;
	}	
	public List<OngBean> getFiltroOngBean() {
		return filtroOngBean;
	}
	public void setFiltroOngBean(List<OngBean> filtroOngBean) {
		this.filtroOngBean = filtroOngBean;
	}
	public List<OngBean> getSelectedOngs() {
		return selectedOngs;
	}
	public void setSelectedOngs(List<OngBean> selectedOngs) {
		this.selectedOngs = selectedOngs;
	}
	public OngBean getOngBean() {
		return ongBean;
	}
	public void setOngBean(OngBean ongBean) {
		this.ongBean = ongBean;
	}
	public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}	
	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}
	public ArrayList<OngBean> getOngsBeanCat() {
		return ongsBeanCat;
	}
	public void setOngsBeanCat(ArrayList<OngBean> ongsBeanCat) {
		this.ongsBeanCat = ongsBeanCat;
	}
	public List<OngBean> getFiltroOngBeanCat() {
		return filtroOngBeanCat;
	}
	public void setFiltroOngBeanCat(List<OngBean> filtroOngBeanCat) {
		this.filtroOngBeanCat = filtroOngBeanCat;
	}
	public List<OngBean> getSelectedOngsCat() {
		return selectedOngsCat;
	}
	public void setSelectedOngsCat(List<OngBean> selectedOngsCat) {
		this.selectedOngsCat = selectedOngsCat;
	}
	public OngBean getOngBeanCat() {
		return ongBeanCat;
	}
	public void setOngBeanCat(OngBean ongBeanCat) {
		this.ongBeanCat = ongBeanCat;
	}
	
	
	//	------------------ Operaciones ---------------------
	
	
	public void asignarOngs(){
		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeONG");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listaCatastrofesONGs?faces-redirect=true");
		}
		else	
		{		
			Long idCatastrofe = new Long(idEventoString);		
			CatastrofeEBR manager = null;
			Context context = null;	
			FacesMessage message = null;
			
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
			
			if (selectedOngs.size() > 0){ 				
				try{					
					OngBean ongBean;	
					
					for (int i=0; i<=selectedOngs.size()-1; i++){ 				
						ongBean = selectedOngs.get(i);
						Long idOng = ongBean.getId();			
						manager.agregarOngALaCatastrofe(idCatastrofe, idOng);				
					}				
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", "");
					ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					handler.performNavigation("listaCatastrofesONGs?faces-redirect=true");
					//message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Las ONGs fueron ingresadas al sistema.");
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "Las ONGs fueron ingresadas al sistema.");
														
				}catch (Exception excep){
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo dar de alta algúna de las ONG.");
					System.out.println("Excepción al agregar las ONGs a la catástrofe: " + excep.getMessage());				
				}  
				
			}
			else{				
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe seleccionar al menos una ONG.");
				
			}
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public void cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listaCatastrofesONGs?faces-redirect=true");		
	}
	
	public void quitarOngs(){
		
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeONG");
			if ((idEventoString == null) || (idEventoString == ""))
    		{	
    			System.out.println("No existe la catástrofe. "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listaCatastrofesONGs?faces-redirect=true");
    		}
    		else	
    		{ 
				Long idCatastrofe = new Long(idEventoString);
				
				CatastrofeEBR manager = null;
				Context context = null;	
				FacesMessage message = null;
				
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
				
				if (selectedOngsCat.size() > 0){ 				
					try{					
						OngBean ongBean;	
						
						for (int i=0; i<=selectedOngsCat.size()-1; i++){ 				
							ongBean = selectedOngsCat.get(i);
							Long idOng = ongBean.getId();			
							manager.eliminarOngDeCatastrofe(idCatastrofe, idOng);				
						}				
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", "");
						ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
						handler.performNavigation("listaCatastrofesONGs?faces-redirect=true");
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operación Exitosa", "Las ONGs fueron quitadas de la catástrofe al sistema.");
															
					}catch (Exception excep){
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudieron quitar al menos alguna de las ONG.");
						System.out.println("Excepción al quitar las ONGs a la catástrofe: " + excep.getMessage());				
					}  
				}
				else{				
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe seleccionar al menos una ONG.");				
				}
				FacesContext.getCurrentInstance().addMessage(null, message);	
    		}
					
	}	

}
