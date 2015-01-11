package com.web.beans.infoCatastrofe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenCatastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;

 
@ManagedBean
public class ImagesView {
     
	
	
	
	
    private List<String> imagesMostrar;
     
     
    
    public List<String> getImagesMostrar() {
		return imagesMostrar;
	}

	public void setImagesMostrar(List<String> imagesMostrar) {
		this.imagesMostrar = imagesMostrar;
	}

	@PostConstruct
    public void init() {
    
    	
		/*ServletRequest request = null;
		HttpServletRequest req = (HttpServletRequest) request;
		@SuppressWarnings("null")
		HttpSession session = req.getSession();
		Long idCatastrofe = (long) session.getAttribute("idCatastrofeUsuario");*/
    	Long idCatastrofe = new Long(1);
    	//
        if ((idCatastrofe == null) || (idCatastrofe == 00))
		{	
			System.out.println("No existe la catástrofe al asignar imagenes. "); 			
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
				
				System.out.print("esto obtiene de id catastrofe");
				System.out.print(idCatastrofe);
				imagesMostrar = new ArrayList<String>();
		        int posicion = 1;
				if(idCatastrofe != 0){	
		        ///////////////////////////////////////////
		        
				Collection<ImagenCatastrofe> res = new ArrayList<ImagenCatastrofe>();
				res = manager.listaImagenesDeCatastrofe(idCatastrofe);				
				Long id;
				String path;				
				int i = 0;
				
		        
				for (ImagenCatastrofe imgCatastrofe : res){
					id = imgCatastrofe.getId();
					path = imgCatastrofe.getPath();					
					imagesMostrar.add(path);
			//		images.add(i, new ImagenCatastrofeBean(id,path));
					i = i + 1;
				}										       
				}else
				{
					imagesMostrar.add("imagen" + posicion + ".jpg");
				}
		    }catch (Exception excep){
				System.out.println("Excepción en ImagenesView: " + excep.getMessage());      		 			       	            	
			}  
		}    	
    	  	
    	
    	
    	   }
 
    public List<String> getImages() {
        return imagesMostrar;
    }
}