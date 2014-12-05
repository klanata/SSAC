package com.web.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.event.FileUploadEvent;

import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;

@ManagedBean(name="fileUploadController")
public class FileUploadController {	
	
	
	
	public void upload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Las imágenes fueron ingresadas con exito.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);              
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }  
	
	public void copyFile(String fileName, InputStream in) {	
		
		String idP = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idP");
		System.out.println("El id: " + idP);		
		if ((idP == null) || (idP == ""))
		{	
			System.out.println("No existe . "); 			
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listaCatastrofesImagenes?faces-redirect=true");
		}
		else	
		{		
			PersonasDesaparecidasEBR manager = null;				
			Context context = null;				
			//FacesMessage message = null; 
			
			try {
	            // 1. Obtaining Context
	            context = ClienteUtility.getInitialContext();
	            // 2. Generate JNDI Lookup name
	            //String lookupName = getLookupName();
	            // 3. Lookup and cast
	            manager = (PersonasDesaparecidasEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//PersonasDesaparecidasEB!com.core.service.negocio.remote.PersonasDesaparecidasEBR");
	 
	        } catch (NamingException e) {
	            e.printStackTrace();
	        }				
			
	        try {
	        	 
	        	Long idPer = new Long(idP);
	        	
	        	String jboss = System.getenv("JBOSS_HOME");
	        	int x = new Double(Math.random() * 1000000).intValue();        	        	        	
	        	
	        	File outputFilePath = new File(x + fileName);		
	    		String fileString = outputFilePath.toString();
	    		
	    		try {
	    			manager.agregarImagenAPersonaDesaparecida(idPer, fileString);
	    			outputFilePath = new File(jboss + "\\Proyecto\\imagenes.war\\" + x + fileName);
		    		OutputStream out = new FileOutputStream(outputFilePath);                        
		           
		             int read = 0;
		             byte[] bytes = new byte[1024];
		           
		             while ((read = in.read(bytes)) != -1) {
		                 out.write(bytes, 0, read);
		             }
		           
		             in.close();
		             out.flush();
		             out.close();
		           
		             System.out.println("Nuevo archivo creado!");   	    		
		        }catch (Exception excep){
					System.out.println("Excepción al obtener " + excep.getMessage());      		 			       	           	
				}	    			    			    		        	      
	        } catch (Exception e) {
	             System.out.println(e.getMessage());
	        }
	        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeImg", "");
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("vistaImagenesCatastrofe?faces-redirect=true");	
	        
		}
	}		
	
	
	public void cancelar(){
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeImg", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("vistaImagenesCatastrofe?faces-redirect=true");		
	}
	

}
