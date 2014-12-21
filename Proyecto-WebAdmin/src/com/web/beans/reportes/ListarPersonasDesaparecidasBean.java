package com.web.beans.reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.data.entites.Rescatista;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;
import com.core.service.negocio.remote.RescatistaEBR;
import com.web.beans.rescatista.RescatistaBean;



@ManagedBean(name="listarPersonasDesaparecidasBean")
@RequestScoped
public class ListarPersonasDesaparecidasBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	
	private ArrayList<PersonasDesaparecidasBean> personasBean = new ArrayList<PersonasDesaparecidasBean>();    

	private List<PersonasDesaparecidasBean> filtroPersonasBean;
    
    private PersonasDesaparecidasBean selectedPersonas;    
    
    @ManagedProperty("#{personasdesaparecidasBean}")
    private PersonasDesaparecidasBean personaBean;
    
    
    
    
    
    
	public ArrayList<PersonasDesaparecidasBean> getPersonasBean() {
		return personasBean;
	}

	public void setPersonasBean(ArrayList<PersonasDesaparecidasBean> personasBean) {
		this.personasBean = personasBean;
	}

	public List<PersonasDesaparecidasBean> getFiltroPersonasBean() {
		return filtroPersonasBean;
	}

	public void setFiltroPersonasBean(
			List<PersonasDesaparecidasBean> filtroPersonasBean) {
		this.filtroPersonasBean = filtroPersonasBean;
	}

	public PersonasDesaparecidasBean getSelectedPersonas() {
		return selectedPersonas;
	}

	public void setSelectedPersonas(PersonasDesaparecidasBean selectedPersonas) {
		this.selectedPersonas = selectedPersonas;
	}

	public PersonasDesaparecidasBean getPersonaBean() {
		return personaBean;
	}

	public void setPersonaBean(PersonasDesaparecidasBean personaBean) {
		this.personaBean = personaBean;
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
			//
			Collection<PersonasDesaparecidas> res = new ArrayList<PersonasDesaparecidas>();
			res = manager.listarPersonas();
			
			Long id;
			String nombreCatastrofe;
			String nombre;
			String apellido ;
			String numeroContacto ;	
			Date fechNac;
			String descripcion ;
			Boolean hallada;
			String imagen;
			
			PersonasDesaparecidas a;
			Iterator<PersonasDesaparecidas> it = res.iterator();
			int i = 0;
			 while(it.hasNext())
		     {
				  a  = it.next();
				  id = a.getId();
				  nombre= a.getNombre();
				  apellido= a.getApellido();
				  nombreCatastrofe =  a.getCatastrofe().getNombreEvento();
				  numeroContacto =  a.getNumeroContacto();
				  fechNac = a.getFechNac();
				  descripcion = a.getDescripcion();
				  hallada = a.isHallada();
				 /* Set<ImagenPersonaDesaparecida> lista =  a.getImagenes();
				  Iterator<ImagenPersonaDesaparecida> it = lista.iterator();
				  imagen = it.next();*/
				  personasBean.add(i, new PersonasDesaparecidasBean(nombreCatastrofe, nombre, apellido, numeroContacto, fechNac, descripcion, hallada));
				  
				  i++;
				
		     
		     }
			
		
													    		
			
			
    	}catch (Exception excep){
    		System.out.println("Excepción al listar las personas Desaparecidas: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }    
    
  
	
	
	public void onRowSelect(SelectEvent event) {
		
		///Obtenego el string con el id del objeto
		Long id = ((PersonasDesaparecidasBean) event.getObject()).getId();
		
		//Pasarlo a string cuando lo mandemos por sesion
		String idEvento = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idPersonaDesaparecida", idEvento); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("actualizarPersona?faces-redirect=true");						 														

		            
    }
	
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Persona No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    		
	
	
}
