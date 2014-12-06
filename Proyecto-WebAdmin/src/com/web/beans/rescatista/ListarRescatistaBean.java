package com.web.beans.rescatista;

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


import com.core.data.entites.Rescatista;

import com.core.service.negocio.remote.RescatistaEBR;



@ManagedBean(name="listaRescatistaBean")
@RequestScoped
public class ListarRescatistaBean implements Serializable {


	
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<RescatistaBean> rescatistasBean = new ArrayList<RescatistaBean>();    

	private List<RescatistaBean> filtroRescatistaBean;
    
    private RescatistaBean selectedRescatista;    
    
    @ManagedProperty("#{rescatistaBean}")
    private RescatistaBean rescatistaBean;
    
    
	@PostConstruct
    public void init() {
    	
	RescatistaEBR manager = null;		
		
		Context context = null;
		 
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (RescatistaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//RescatistaEB!com.core.service.negocio.remote.RescatistaEBR");
 
        } catch (NamingException e) {
            e.printStackTrace();
        }				
    		
					
		try{			
			//
			Collection<Rescatista> res = new ArrayList<Rescatista>();
			res = manager.listarTodosLosRescatistasActivos();
		
			
			if(res == null){}
			else{
			
			
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
				Rescatista a;
				Iterator< Rescatista> it = res.iterator();
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
					  rescatistasBean.add(i, new RescatistaBean(id,nombre, apellido, nick, email, password, fechaNac, sexo, celular));
					  i++;
					      	
			    } 
		     }
			
		
													    		
			
			
    	}catch (Exception excep){
    		System.out.println("Excepci�n al listar los administradores: " + excep.getMessage());      		 			       	           	
    	}  					
				
    }    
    
    //	------------------ Getter and setter methods ---------------------
        
	


	//	------------------ Operaciones ---------------------
	
	public ArrayList<RescatistaBean> getRescatistasBean() {
		return rescatistasBean;
	}

	public void setRescatistasBean(ArrayList<RescatistaBean> rescatistasBean) {
		this.rescatistasBean = rescatistasBean;
	}

	public List<RescatistaBean> getFiltroRescatistaBean() {
		return filtroRescatistaBean;
	}

	public void setFiltroRescatistaBean(List<RescatistaBean> filtroRescatistaBean) {
		this.filtroRescatistaBean = filtroRescatistaBean;
	}

	public RescatistaBean getSelectedRescatista() {
		return selectedRescatista;
	}

	public void setSelectedRescatista(RescatistaBean selectedRescatista) {
		this.selectedRescatista = selectedRescatista;
	}

	public RescatistaBean getRescatistaBean() {
		return rescatistaBean;
	}

	public void setRescatistaBean(RescatistaBean rescatistaBean) {
		this.rescatistaBean = rescatistaBean;
	}

	public void onRowSelect(SelectEvent event) {
		
		///Obtenego el string con el id del objeto
		Long id = ((RescatistaBean) event.getObject()).getId();
		
		//Pasarlo a string cuando lo mandemos por sesion
		String idEvento = id.toString();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idEventoRescatista", idEvento); 		
					
		ConfigurableNavigationHandler handler=(ConfigurableNavigationHandler)FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		handler.performNavigation("modificacionRescatista?faces-redirect=true");						 														

		            
    }
	
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Rescatista No Seleccionada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    	
}
