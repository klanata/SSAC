package com.web.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.EconomicaEBR;
import com.core.service.negocio.remote.OngEBR;


@ManagedBean(name="economicaBean")
@SessionScoped
public class EconomicaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private String usuario = "";
	private Date fechaRealizada;
	private String ong;  
	private ArrayList<SelectItem> listaongs = new ArrayList<SelectItem>();
	
	
	
	
	public String getOng() {
		return ong;
	}
	public void setOng(String ong) {
		this.ong = ong;
	}
	public ArrayList<SelectItem> getListaongs() {
		return listaongs;
	}
	public void setListaongs(ArrayList<SelectItem> listaongs) {
		this.listaongs = listaongs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	
	
	
	@PostConstruct
    public void init() {
		
		CatastrofeEBR managerCatastrofe = null;		
		Context contextCatastrofe = null;
		
		OngEBR managerOng = null;		
		Context contextOng = null;
		
		
		try {
            
            contextCatastrofe = ClienteUtility.getInitialContext();
            managerCatastrofe = (CatastrofeEBR) contextCatastrofe.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
 
            contextOng = ClienteUtility.getInitialContext();
            managerOng = (OngEBR) contextOng.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
            
		 } catch (NamingException e) {
	            e.printStackTrace();
	     }
		try{    		   	    	  
			
			/**CARGO LAS ONG**/
			Collection<Ong> res = new ArrayList<Ong>();
			FacesContext contexto = FacesContext.getCurrentInstance();
			HttpSession sesion = (HttpSession)contexto.getExternalContext().getSession(true);
			Long idCatastrofe = (Long)sesion.getAttribute("idmongo");

			//Long idCatastrofe=  new Long(1);
			res = managerCatastrofe.listaOngDeCatastrofe(idCatastrofe); 
			Catastrofe c = managerCatastrofe.buscaCatastrofePorId(idCatastrofe);
			String nombre = c.getNombreEvento().toLowerCase();
			//ver los espacios
			String nombreSinEspacio = nombre.replaceAll(" ", "_");
			
			 
			Iterator<Ong> it = res.iterator();
	    	int i = 0;
	    	Ong pong= null;
	    	while(it.hasNext()){
			//for (int i=0; i<=res.size()-1; i++){    		
				pong = it.next();
							
				listaongs.add(i, new SelectItem(pong.getNombre(),pong.getNombre()));
				i++;
			}

			Date fechaRealizada = new Date();
    		fechaRealizada.getTime();
    		/**fin **/
    		
    		/*
    		 * Busco la ong por el nombre seleccionado
    		*/
    		managerOng.buscarOngPorNick_EB(ong);
    		
    		
       		    	
		}catch (Exception excep){
    		System.out.println("Excepcion en donacion de servicio: " + excep.getMessage());      		 			       
	        
    		
		}
	
	}

	/**************************************************************************************/
	
	public void  registrarDonacionEconomica(){		
		

		EconomicaEBR manager = null;
		Context context = null;
		CatastrofeEBR managerCatastrofe = null;		
		//boolean encontre= false;
		Context contextCatastrofe = null;
		
		OngEBR managerOng = null;		
		Context contextOng = null;
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (EconomicaEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//EconomicaEB!com.core.service.negocio.remote.EconomicaEBR");
            contextCatastrofe = ClienteUtility.getInitialContext();
            managerCatastrofe = (CatastrofeEBR) contextCatastrofe.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
 
            contextOng = ClienteUtility.getInitialContext();
            managerOng = (OngEBR) contextOng.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
		 } catch (NamingException e) {
	            e.printStackTrace();
	        }
		try{    		   	    	  
    	   		
			Ong aux= managerOng.buscarOngPorNick_EB(ong);
    		Long idOng = aux.getId();
    		
    		if (usuario.isEmpty())
    		{
    			usuario= "Anonimo";
    		}
    		Date fechaRealizada = new Date();
    		fechaRealizada.getTime();
    		
    		
    		
    		
       		manager.crearDonacionEconomica(idOng, usuario, fechaRealizada);    	
    		
    		
    	}catch (Exception excep){
    		System.out.println("Excepcion en donacion economica: " + excep.getMessage());      		 			       
	        
    		
    	}    
	}
}
	
