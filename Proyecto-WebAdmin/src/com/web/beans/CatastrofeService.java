package com.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="catastrofeService", eager = true)
@SessionScoped
public class CatastrofeService {
	
	private List<CatastrofeBean> catastrofesBean = new ArrayList<CatastrofeBean>();
	
	@PostConstruct
    public void init() {		
		try {
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
		    	String css;
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
					css = catastrofe.getCss();
					catastrofesBean.add(i, new CatastrofeBean(id,nombreEvento,descripcion,logo,coordenadasX,coordenadasY,activa,prioridad,css));									    		
				}	
				
	    	}catch (Exception excep){
	    		System.out.println("Excepción al listar las catástrofes: " + excep.getMessage());      		 			       	           	
	    	}  		   		
			
		}catch (Exception excep){
			System.out.println("Excepción en FuentesService al cargar el combo: " + excep.getMessage());      		 			       	            	
		}      		
		
	}

	//	------------------ Getter and setter methods ---------------------
	
	public List<CatastrofeBean> getCatastrofesBean() {
		return catastrofesBean;
	}



	public void setCatastrofesBean(List<CatastrofeBean> catastrofesBean) {
		this.catastrofesBean = catastrofesBean;
	}


	//	------------------ Operaciones ---------------------
	
	

	

}
