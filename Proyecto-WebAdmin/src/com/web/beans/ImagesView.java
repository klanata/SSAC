package com.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.core.data.entites.ImagenCatastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;

@ManagedBean(name="imagesView")
@RequestScoped
public class ImagesView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{catastrofeBean}")
    private CatastrofeBean catastrofeBean = new CatastrofeBean();
	
	private List<ImagenCatastrofeBean> images = new ArrayList<ImagenCatastrofeBean>(); 
	
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
    				    				
    				Collection<ImagenCatastrofe> res = new ArrayList<ImagenCatastrofe>();
    				res = manager.listaImagenesDeCatastrofe(idCatastrofe);				
    				Long id;
    				String path;				
    				int i = 0;
    				for (ImagenCatastrofe imgCatastrofe : res){
    					id = imgCatastrofe.getId();
    					path = imgCatastrofe.getPath();					
    					images.add(i, new ImagenCatastrofeBean(id,path));
    					i = i + 1;
    				}										       
    			
    		    }catch (Exception excep){
    				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
    			}  
    		}    		    	
                        
    }
    
    
    //	------------------ Getter and setter methods ---------------------
    

	public List<ImagenCatastrofeBean> getImages() {
		return images;
	}

	public void setImages(List<ImagenCatastrofeBean> images) {
		this.images = images;
	}

	public ImagenCatastrofeBean getImgCatastrofeBean() {
		return imgCatastrofeBean;
	}

	public void setImgCatastrofeBean(ImagenCatastrofeBean imgCatastrofeBean) {
		this.imgCatastrofeBean = imgCatastrofeBean;
	}


	public CatastrofeBean getCatastrofeBean() {
		return catastrofeBean;
	}


	public void setCatastrofeBean(CatastrofeBean catastrofeBean) {
		this.catastrofeBean = catastrofeBean;
	}
    
 
	//	------------------ Operaciones ---------------------
	
	public void asignarImagen(){
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("asignarImgCatastrofe?faces-redirect=true");			
	}
	
	public void borrarImagen(){	
		/*
		String jboss = System.getenv("JBOSS_HOME");		
		File file = new File(jboss + "\\Proyecto\\imagenes.war\\" + "1887catastrofesUruguay1.jpg");		
		if(file.delete()){
			System.out.println(file.getName() + " fue elimindada!");
		}else{
			System.out.println("La operación de eliminación falló.");
		}
		*/		
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("quitarImagenCatastrofe?faces-redirect=true");
		
	}
	
	public void cancelar(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeImg", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");		
	}
    

}
