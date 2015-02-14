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
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="borrarCatastrofeBeanMap")
@RequestScoped
public class BorrarCatastrofeBeanMap implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
				
	
	//	------------------ Getter and setter methods ---------------------
		
    public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}
	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}
	
	//	------------------ Operaciones ---------------------
			
	@PostConstruct
	public void init() {
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
        if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe al borrar."); 			
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
			
			/*
			CatastrofeEBR manager2 = null;				
			Context context2 = null;			
			//FacesMessage message = null; 
			
			try {
	            // 1. Obtaining Context
	            context2 = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
	            manager2 = (CatastrofeEBR) context2.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
		
			try{
				long idCatastrofe2 = new Long(idEventoString);
				System.out.println("Catastrofe: " + idCatastrofe2);
				List<String> filtros = new ArrayList<String>();
				String fuente ="Youtube";
				filtros= manager2.listarFiltrosDeCatastrofe(idCatastrofe2, fuente);
				System.out.println("Filtros size: " + filtros.size());
				String f;
				for (int i=0; i<=filtros.size()-1; i++){    		
					f = filtros.get(i);
					System.out.println("Filtros: " + f);
				}	

		    }catch (Exception excep){
				System.out.println("Excepción en borrarCatastrofeBeanMap: " + excep.getMessage());      		 			       	            	
			} 
			*/
			
		}    	
	}

		
	public void borrarCatastrofe(){	
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
		}
		else	
		{ 			
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
									
			try{			
				Long idCatastrofe = new Long(idEventoString);
				
				try {	    				    	
						    			
					manager.EliminarCatastrofe(idCatastrofe);
					
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja Exitosa", "La catástrofe fue dada de baja del sistema.");
    				FacesContext.getCurrentInstance().addMessage(null, message);
    				
    				FacesContext contexto = FacesContext.getCurrentInstance();
    				contexto.getExternalContext().getFlash().setKeepMessages(true);
		        	ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    				handler.performNavigation("listaBajaCatastrofe?faces-redirect=true");																	
														
				}catch (Exception excep){
					System.out.println("Excepción al borrar la catástrofe: " + excep.getMessage());      		 			       	           	
				}	    			    			    		        	      
	        } catch (Exception e) {
	             System.out.println(e.getMessage());
	        }
						
			FacesContext.getCurrentInstance().addMessage(null, message);
				
		}
		
	}
	
	public void cancelar(){	
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listaBajaCatastrofe?faces-redirect=true");	
		
	}
	
			
}
