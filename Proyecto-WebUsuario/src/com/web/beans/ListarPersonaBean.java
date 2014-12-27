package com.web.beans;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;

import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;


@ManagedBean(name="listapersonaBean")
@RequestScoped
public class ListarPersonaBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private ArrayList<PersonasDesaparecidasBean> personasBean = new ArrayList<PersonasDesaparecidasBean>();    
    private List<PersonasDesaparecidas> filtroPersonafeBean;
    
    private PersonasDesaparecidas selectedPersona;    
    
    @ManagedProperty("#{personasdesaparecidasBean}")
    private PersonasDesaparecidasBean personaBean;
    
    @PostConstruct
    public void init() {
    	
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
					
		try{			
			List<PersonasDesaparecidas> res = new ArrayList<PersonasDesaparecidas>();
			res = manager.listarPersonas();   				
			PersonasDesaparecidas persona;
	    	Long id;
	    	String nombre;
	    	String apellido;
	    	String numeroContacto;
	    	String descripcion;
	    	Date fechnac;
	    	Boolean hallada;
	    	Set<ImagenPersonaDesaparecida> imagenes;
			for (int i=0; i<=res.size()-1; i++){    		
				persona = res.get(i);
				id = persona.getId();
				nombre = persona.getNombre();
				apellido = persona.getApellido();												
				numeroContacto = persona.getNumeroContacto();
				System.out.println("despues de numeroContacto");
				descripcion = persona.getDescripcion();
				fechnac = persona.getFechNac();
				hallada = persona.isHallada();
				imagenes = persona.getImagenes();
				System.out.println("aca ");			
				personasBean.add(i, new PersonasDesaparecidasBean(id,nombre, apellido, numeroContacto, descripcion, fechnac,  imagenes, hallada));									    		
			}	
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar personas: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }    
    
    //	------------------ Getter and setter methods ---------------------
        
    public ArrayList<PersonasDesaparecidasBean> getPersonasBean() {
		return personasBean;
	}

	public void setPersonasBean(ArrayList<PersonasDesaparecidasBean> personasBean) {
		this.personasBean = personasBean;
	}

	public List<PersonasDesaparecidas> getFiltroPersonafeBean() {
		return filtroPersonafeBean;
	}

	public void setFiltroPersonafeBean(
			List<PersonasDesaparecidas> filtroPersonafeBean) {
		this.filtroPersonafeBean = filtroPersonafeBean;
	}

	public PersonasDesaparecidas getSelectedPersona() {
		return selectedPersona;
	}

	public void setSelectedPersona(PersonasDesaparecidas selectedPersona) {
		this.selectedPersona = selectedPersona;
	}

	public PersonasDesaparecidasBean getPersonaBean() {
		return personaBean;
	}

	public void setPersonaBean(PersonasDesaparecidasBean personaBean) {
		this.personaBean = personaBean;
	}
    		
	
	//	------------------ Operaciones ---------------------
	
	public void onRowSelect(SelectEvent event) {
		
		Long id = ((PersonasDesaparecidasBean) event.getObject()).getId();
		System.out.println("id de la persona seleccionada: " + id);
		String idP = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idP", idP); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("Index?faces-redirect=true");						 														
		
		//ConfigurableNavigationHandler.performNavigation("asignarOngCatastrofe?faces-redirect=true");
		//return "asignarOngCatastrofe?faces-redirect=true";
		            
    }
 
   

	public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Persona No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
    
    
    
    
    
	
}
