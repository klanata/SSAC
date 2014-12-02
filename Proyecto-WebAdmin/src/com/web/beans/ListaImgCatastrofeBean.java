package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import java.io.File;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;

@ManagedBean(name="listaImgCatastrofeBean")
@RequestScoped
public class ListaImgCatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	private ArrayList<ImagenCatastrofeBean> imgCatastrofesBean = new ArrayList<ImagenCatastrofeBean>();
	
    private List<ImagenCatastrofeBean> filtroImgCatastrofeBean;
         
    private List<ImagenCatastrofeBean> selectedImgsCatastrofe;
    
    @ManagedProperty("#{imgCatastrofeBean}")
    private ImagenCatastrofeBean imgCatastrofeBean;

    
    @PostConstruct
    public void init() {
    	
    		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeImg");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    			System.out.println("No existe la catástrofe. "); 			
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");
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
			    	
					nombreEvento = catastrofe.getNombreEvento();
					descripcionCatastrofe = catastrofe.getDescripcion();												
					logo = catastrofe.getLogo();																								
					coordenadasX = catastrofe.getCoordenadasX();
					coordenadasY = catastrofe.getCoordenadasY();
					activa = catastrofe.getActiva();
					prioridad = catastrofe.getPrioridad(); 
					catastrofeBean = new CatastrofeBean(idCatastrofe,nombreEvento,descripcionCatastrofe,logo,coordenadasX,coordenadasY,activa,prioridad);					
    				    				
    				Collection<ImagenCatastrofe> res = new ArrayList<ImagenCatastrofe>();
    				res = manager.listaImagenesDeCatastrofe(idCatastrofe);				
    				Long id;
    				String path;				
    				int i = 0;
    				for (ImagenCatastrofe imgCatastrofe : res){
    					id = imgCatastrofe.getId();
    					path = imgCatastrofe.getPath();					
    					imgCatastrofesBean.add(i, new ImagenCatastrofeBean(id,path));    					
    					i = i + 1;
    				}										       
    			
    		    }catch (Exception excep){
    				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
    			}  
    		}    		    	                       
    }
                
    
    //	------------------ Getter and setter methods ---------------------
    
    
    public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}

	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}
    
	public ArrayList<ImagenCatastrofeBean> getImgCatastrofesBean() {
		return imgCatastrofesBean;
	}

	public void setImgCatastrofesBean(
			ArrayList<ImagenCatastrofeBean> imgCatastrofesBean) {
		this.imgCatastrofesBean = imgCatastrofesBean;
	}

	public List<ImagenCatastrofeBean> getFiltroImgCatastrofeBean() {
		return filtroImgCatastrofeBean;
	}

	public void setFiltroImgCatastrofeBean(
			List<ImagenCatastrofeBean> filtroImgCatastrofeBean) {
		this.filtroImgCatastrofeBean = filtroImgCatastrofeBean;
	}

	public List<ImagenCatastrofeBean> getSelectedImgsCatastrofe() {
		return selectedImgsCatastrofe;
	}

	public void setSelectedImgsCatastrofe(
			List<ImagenCatastrofeBean> selectedImgsCatastrofe) {
		this.selectedImgsCatastrofe = selectedImgsCatastrofe;
	}

	public ImagenCatastrofeBean getImgCatastrofeBean() {
		return imgCatastrofeBean;
	}

	public void setImgCatastrofeBean(ImagenCatastrofeBean imgCatastrofeBean) {
		this.imgCatastrofeBean = imgCatastrofeBean;
	}


	//	------------------ Operaciones ---------------------
	
	public void quitarImagenes(){
		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEventoCatastrofeImg");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
			System.out.println("No existe la catástrofe. "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");
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
			
			if (selectedImgsCatastrofe.size() > 0){ 				
				try{					
					ImagenCatastrofeBean imgCatBean;	
					
					for (int i=0; i<=selectedImgsCatastrofe.size()-1; i++){ 				
						imgCatBean = selectedImgsCatastrofe.get(i);
						Long idImg = imgCatBean.getId();	
						borrarImagenCatastrofe(imgCatBean.getPath());
						manager.eliminarImgDeCatastrofe(idCatastrofe, idImg);
						
					}				
					//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeONG", "");
					ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
					handler.performNavigation("vistaImagenesCatastrofe?faces-redirect=true");
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operación Exitosa", "Las imagenes fueron quitadas de la catástrofe al sistema.");
														
				}catch (Exception excep){
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudieron quitar al menos alguna de las ONG.");
					System.out.println("Excepción al quitar las imagenes a la catástrofe: " + excep.getMessage());				
				}  
			}
			else{				
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe seleccionar al menos una imagen.");				
			}
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
												
	}
	
	public void cancelar(){				
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("vistaImagenesCatastrofe?faces-redirect=true");				
	}	

	
	public void borrarImagenCatastrofe(String path){	
		
		String jboss = System.getenv("JBOSS_HOME");		
		File file = new File(jboss + "\\Proyecto\\imagenes.war\\" + path);		
		if(file.delete()){
			System.out.println(file.getName() + " fue elimindada!");
		}else{
			System.out.println("La operación de eliminación falló.");
		}
	}
    
    
    
    

}
