package com.web.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;

import clienteutility.ClienteUtility;

import com.core.data.entites.ImagenPersonaDesaparecida;
import com.core.data.entites.PersonasDesaparecidas;
import com.core.service.negocio.remote.PersonasDesaparecidasEBR;



@ManagedBean(name="imagenesPersonaDesaparecidasBean")
@RequestScoped
public class ImagenesPersonaDesaparecidasBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private List<ImagenPersonaBean> listaNombreImagenes = new ArrayList<ImagenPersonaBean>();
	private String imagen;
	

		
	public ImagenesPersonaDesaparecidasBean(){}
	
	
	public ImagenesPersonaDesaparecidasBean(Long id, String nombre, List<ImagenPersonaBean> listaNombreImagenes,
			String imagen) {
		super();
		this.id=id;
		this.listaNombreImagenes = listaNombreImagenes;
		this.imagen = imagen;		
	}
	
	@PostConstruct
    public void init() {
		
		String idPersonaString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idP");				 	
		
		if (idPersonaString != null) {
			
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
				PersonasDesaparecidas persona;
				Long idPersona = new Long(idPersonaString);
				persona = manager.buscaPersonaPorId(idPersona);
				
				id = idPersona;
				nombre = persona.getNombre();
				
				Set<ImagenPersonaDesaparecida> imagenes;
				imagenes = persona.getImagenes();
				String path;	
				
				Long idImagPersDep;
				int i = 0;
				for (ImagenPersonaDesaparecida img : imagenes){	
					idImagPersDep = img.getId();
					path = img.getPath();					
					listaNombreImagenes.add(i, new ImagenPersonaBean(idImagPersDep,path));										
					i = i + 1;
				}				
				
			}catch (Exception excep){
	    		System.out.println("Excepción al obtener imagenes de las personas: " + excep.getMessage());      		 			       	           	
	    	} 
			
		}		
		
	}

	
	//	------------------ Getter and setter methods ---------------------

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<ImagenPersonaBean> getListaNombreImagenes() {
		return listaNombreImagenes;
	}


	public void setListaNombreImagenes(List<ImagenPersonaBean> listaNombreImagenes) {
		this.listaNombreImagenes = listaNombreImagenes;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
		
	
		
}
       		
    	