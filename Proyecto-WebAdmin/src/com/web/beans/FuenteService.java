package com.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Servicio;
import com.core.service.negocio.remote.ServicioEBR;

@ManagedBean(name="fuenteService", eager = true)
@SessionScoped
public class FuenteService {
	
	private List<ServicioBean> serviciosBean = new ArrayList<ServicioBean>();
	
	@PostConstruct
    public void init() {		
		try {
			ServicioEBR managerServicio = null;				
			Context contextServicio = null;			
			//FacesMessage message = null; 
			
			try {
	            // 1. Obtaining Context
				contextServicio = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
	            managerServicio = (ServicioEBR) contextServicio.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServicioEB!com.core.service.negocio.remote.ServicioEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }
		
			try{
				//Aca cargo todos los servicios que voy a tener disponibles
				String fuenteServicio ="Youtube";
				
				boolean existeServicio = managerServicio.ExisteServicio(fuenteServicio);					
				if (existeServicio == false) {
					managerServicio.ingresarServicio(fuenteServicio);			
				}	
								
				List<Servicio> listaServicio = new ArrayList<Servicio>();
				listaServicio = managerServicio.listaServicios();
				Servicio servicio;
				//int id;
				long id;
				String url;
				String fuente;						
				    				    				
				for (int i=0; i<=listaServicio.size()-1; i++){
					servicio = listaServicio.get(i);
					id = servicio.getId();										
					url = servicio.getUrl();
					fuente = servicio.getFuente();    					
					serviciosBean.add(new ServicioBean(id,url,fuente));
				}    					    				
				    				    														         			
		    }catch (Exception excep){
				System.out.println("Excepción en FuentesService al cargar el combo: " + excep.getMessage());      		 			       	            	
			}      		
			
		}catch (Exception excep){
			System.out.println("Excepción en FuentesService al cargar el combo: " + excep.getMessage());      		 			       	            	
		}      		
		
	}

	public List<ServicioBean> getServiciosBean() {		
		return serviciosBean;
	}
	
	public ServicioBean buscarServicioBean(Long id) {
		ServicioBean servicioBean = new ServicioBean();
		
		ServicioEBR managerServicio = null;				
		Context contextServicio = null;			
		//FacesMessage message = null; 
		
		try {
            // 1. Obtaining Context
			contextServicio = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            managerServicio = (ServicioEBR) contextServicio.lookup("ejb:Proyecto-EAR/Proyecto-Core//ServicioEB!com.core.service.negocio.remote.ServicioEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
		try{
			
			Servicio servicio;
			servicio = managerServicio.buscarServicioPorId(id);						
			
			String url ;
			String fuente;	
			
			url = servicio.getUrl();
			fuente = servicio.getFuente(); 
			
			serviciosBean.add(new ServicioBean(id,url,fuente));			    				    										    		
			    				    														         			
	    }catch (Exception excep){
			System.out.println("Excepción en FuentesService al cargar el combo: " + excep.getMessage());      		 			       	            	
		}      						
		return servicioBean;
	}

	

}
