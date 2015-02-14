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

import com.core.data.entites.Catastrofe;
import com.core.service.negocio.remote.CatastrofeEBR;


@ManagedBean(name="fileUploadControllerCSS")
public class FileUploadControllerCSS {
	
	public void upload(FileUploadEvent event) {  
        //FacesMessage msg = new FacesMessage("El CSS fue ingresado con exito.");  
        //FacesContext.getCurrentInstance().addMessage(null, msg);              
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }  
	
	public void copyFile(String fileName, InputStream in) {	
		
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idCatastrofeString");		
		//System.out.println("El id del evento catastrofe CSS: " + idEventoString);		
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
			
	        try {
	        	 
	        	Long idCatastrofe = new Long(idEventoString);
	        	
	        	String jboss = System.getenv("JBOSS_HOME");
	        	int x = new Double(Math.random() * 1000000).intValue();        	        	        	
	        	
	        	File outputFilePath = new File(x + fileName);		
	    		String css = outputFilePath.toString();
	    		
	    		try {	    				    		
	    			Catastrofe c = manager.buscaCatastrofePorId(idCatastrofe);
	    			String nombCSS = c.getCss();
	    			
	    			if ((nombCSS != null) && (nombCSS != "")){
	    				manager.eliminarCSSDeLaCatastrofe(idCatastrofe);
	    				borrarCSSCatastrofe(nombCSS);
	    			}
	    				    				    			
	    			manager.agregarCSSALaCatastrofe(idCatastrofe, css);
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
		           
		             //System.out.println("Nuevo archivo creado!");   	    		
		        }catch (Exception excep){
					System.out.println("Excepción al obtener la catástrofe: " + excep.getMessage());      		 			       	           	
				}	    			    			    		        	      
	        } catch (Exception e) {
	             System.out.println(e.getMessage());
	        }
	        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeImg", "");
			//ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			//handler.performNavigation("listaCatastrofesCSS?faces-redirect=true");		        
		}
	}		
	
	
	public void cancelar(){
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoCatastrofeCSS", "");
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("modificarCatastrofe?faces-redirect=true");		
	}
	
	public void borrarCSSCatastrofe(String path){	
		
		String jboss = System.getenv("JBOSS_HOME");		
		File file = new File(jboss + "\\Proyecto\\imagenes.war\\" + path);	
		file.delete();
		/*
		if(file.delete()){
			//System.out.println(file.getName() + " fue elimindada!");
		}else{
			System.out.println("La operación de eliminación falló.");
		}
		*/
	}
	
	public void finalizar(){
		
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
			
	        try {
	        	 
	        	Long idCatastrofe = new Long(idEventoString);	       	        	
	    		
	    		try {	    				    		
	    			Catastrofe c = manager.buscaCatastrofePorId(idCatastrofe);
	    			String nombCSS = c.getCss();
	    			
	    			if  ((nombCSS != null) && (nombCSS != "")){
	    				
	    				String abm = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ABMCatastrofe");
	    		        
	    		        if (abm=="Alta"){
	    		        	message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso Exitoso", "La catástrofe fue ingresada al sistema.");
		    				FacesContext.getCurrentInstance().addMessage(null, message);
		    				
		    				FacesContext contexto = FacesContext.getCurrentInstance();
		    				contexto.getExternalContext().getFlash().setKeepMessages(true);
	    		        	ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		    				handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");
	    		        }
	    		        else{
	    		        	if (abm=="Modificacion"){
	    		        		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación Exitosa", "La catástrofe fue modificada de forma exitosa.");
	    	    				FacesContext.getCurrentInstance().addMessage(null, message);
	    	    				
	    	    				FacesContext contexto = FacesContext.getCurrentInstance();
	    	    				contexto.getExternalContext().getFlash().setKeepMessages(true);
	    		        		
	    		        		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	    	    				handler.performNavigation("modificarCatastrofe?faces-redirect=true");	
	    			        }
	    		        	else{	        		
	    		        		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
	    		    			handler.performNavigation("registrarCatastrofeMap?faces-redirect=true");	        		
	    		        	}	        	
	    		        }
	    					    					    				
	    			}
	    			else {	    				    					    				
	    				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Debe ingresar el archivo css.");
	    				FacesContext.getCurrentInstance().addMessage(null, message);
	    			}
	    				    				    				    				    	
		        }catch (Exception excep){
					System.out.println("Excepción al obtener la catástrofe al finalizar: " + excep.getMessage());      		 			       	           	
				}	    			    			    		        	      
	        } catch (Exception e) {
	             System.out.println(e.getMessage());
	        }
	       	        
		}						
	}
	

}
