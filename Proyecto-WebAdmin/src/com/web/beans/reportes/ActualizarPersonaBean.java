package com.web.beans.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.PersonasDesaparecidas;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;





@ManagedBean(name="actualizarPersonaBean")
@RequestScoped
public class ActualizarPersonaBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	@ManagedProperty("#{personasdesaparecidasBean}")
	private PersonasDesaparecidasBean personasdesaparecidasBean = new PersonasDesaparecidasBean();

	public PersonasDesaparecidasBean getPersonasdesaparecidasBean() {
		return personasdesaparecidasBean;
	}

	public void setPersonasdesaparecidasBean(
			PersonasDesaparecidasBean personasdesaparecidasBean) {
		this.personasdesaparecidasBean = personasdesaparecidasBean;
	}
	
	@PostConstruct
    public void init() {
    	
		PersonasDesaparecidasEBR manager = null;
		
		Context context = null;
		
		
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
		
		try{			
			
			String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPersonaDesaparecida");
            if ((idEventoString == null) || (idEventoString == ""))
    		{	
    						
    			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    			handler.performNavigation("listarPersonasDesaparecidas?faces-redirect=true");
    		}
    		else	
    		{
			
    			
    			//busco al administrador con es id
    			Long id = new Long(idEventoString);
				
    			PersonasDesaparecidas p = new PersonasDesaparecidas();
				p = manager.buscaPersonaPorId(id);
				//cargamos datos a mostrar
						
				//Long id;
				String nombre = p.getNombre();
				String apellido= p.getApellido();
				boolean hallada = p.isHallada();
				String nombreCatastrofe = p.getCatastrofe().getNombreEvento();
				String numeroContacto = p.getNumeroContacto();
				Date fechNac = p.getFechNac();
				String descripcion = p.getDescripcion();
				Long idCatastrofe = p.getCatastrofe().getId();
				String imagen = "ver de tomarla";
				personasdesaparecidasBean = new PersonasDesaparecidasBean(nombreCatastrofe, id, idCatastrofe, nombreCatastrofe, apellido, numeroContacto, fechNac, descripcion, hallada, imagen);
				
				 
				 	
		     
		     }
				
        	
    	}catch (Exception excep){
    		System.out.println("Excepcion obtener personas desaparecidas: " + excep.getMessage());      		 			       	           	
    	} 		
	}
	////////////////////////////////////
	/*---------------------------------------------------------------------------------------------------------*/
	public void editar(){
			
		PersonasDesaparecidasEBR manager = null;
		
		Context context = null;
		
		
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
					
		String idEventoString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idPersonaDesaparecida");
		if ((idEventoString == null) || (idEventoString == ""))
		{	
						
			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarPersonasDesaparecidas?faces-redirect=true");
		}
		else	
		{
			String nombre = personasdesaparecidasBean.getNombre();
			String apellido= personasdesaparecidasBean.getApellido();
			String descripcion = personasdesaparecidasBean.getDescripcion();
			Long idCatastrofe = personasdesaparecidasBean.getIdCatastrofe();
			
			String telefono= personasdesaparecidasBean.getNumeroContacto();
			Date fechaNacimiento = personasdesaparecidasBean.getFechNac();
			boolean hallada = personasdesaparecidasBean.getHallada();
			System.out.println("*************2222222222222222222222: " + idCatastrofe);      
			System.out.println("*************Teefon 2222222222222222222222: " + telefono);     
			System.out.println("*************FEcha 2222222222222222222222: " + fechaNacimiento);     
			
			//String imagen = "tomarla";
			manager.ActualizarEstadoPersonaDesaparecida(apellido, descripcion, fechaNacimiento, hallada, nombre, telefono, idCatastrofe);			


			ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			handler.performNavigation("listarPersonasDesaparecidas?faces-redirect=true");
		}
	
	}

}
