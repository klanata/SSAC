package com.web.beans.administrador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import clienteutility.ClienteUtility;

import com.core.data.entites.Administrador;
import com.core.service.negocio.remote.AdministradorEBR;



@ManagedBean(name="listaAdmBean")
@RequestScoped
public class ListarAdministradoresBean_ implements Serializable {


	
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<AdministradorBean> adminBean = new ArrayList<AdministradorBean>();    

	private List<AdministradorBean> filtroAdministradorBean_;
    
    private AdministradorBean selectedAdministrador_;    
    
    @ManagedProperty("#{administradorBean}")
    private AdministradorBean administradorBean;
    
    
	@PostConstruct
    public void init() {
    	
		AdministradorEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (AdministradorEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }		
					
		try{			
			//
			Collection<Administrador> res = new ArrayList<Administrador>();
			res = manager.listarTodosLosAdministradores();
			//Administrador administrador;
			
			Long id;
			String nombre;
			String apellido;
			String nick ;
			String email;
			String password ;
			Date fechaNac;
			String sexo ;
			String celular;
			Administrador a;
			Iterator< Administrador> it = res.iterator();
			int i = 0;
			while(it.hasNext())
		    {
				  a  = it.next();
				  id = a.getId();
				  nombre= a.getNombre();
				  apellido= a.getApellido();
				  nick = a.getNick();
				  email = a.getEmail();
				  password = a.getPassword();
				  fechaNac = a.getFechaNac();
				  sexo = a.getSexo();
				  celular = a.getCelular();
				  adminBean.add(i, new AdministradorBean(id,nombre, apellido, nick, email, password, fechaNac, sexo, celular));
				  i++;
				  //System.out.println("obtengo administradores: " + i);      			     
		     }																		    			
			
    	}catch (Exception excep){
    		System.out.println("Excepci�n al listar los administradores: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }    
    
    //	------------------ Getter and setter methods ---------------------
        
  

	public List<AdministradorBean> getFiltroAdministradorBean() {
		return filtroAdministradorBean_;
	}

	public void setFiltroAdministradorBean(
			List<AdministradorBean> filtroAdministradorBean) {
		this.filtroAdministradorBean_ = filtroAdministradorBean;
	}

	public AdministradorBean getSelectedAdministrador() {
		return selectedAdministrador_;
	}

	public void setSelectedAdministrador(AdministradorBean selectedAdministrador) {
		this.selectedAdministrador_ = selectedAdministrador;
	}

	
	
	public ArrayList<AdministradorBean> getAdministradoresBean() {
		return adminBean;
	}

	public void setAdministradoresBean(
			ArrayList<AdministradorBean> administradoresBean) {
		this.adminBean= administradoresBean;
	}

	public AdministradorBean getAdministradorBean() {
		return administradorBean;
	}

	public void setAdministradorBean(AdministradorBean administradorBean) {
		this.administradorBean = administradorBean;
	}


	//	------------------ Operaciones ---------------------
	
	public void onRowSelect(SelectEvent event) {
		
		///Obtenego el string con el id del objeto
		Long id = ((AdministradorBean) event.getObject()).getId();
		//System.out.println("id del Administrador seleccionada: " + id);
		//Pasarlo a string cuando lo mandemos por sesion
		String idEvento = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoAdministradorEliminar", idEvento); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("eliminarAdmin?faces-redirect=true");						 														

		            
    }
	
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Administrador No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    	
}
