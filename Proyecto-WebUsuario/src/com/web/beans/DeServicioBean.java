package com.web.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.core.service.negocio.remote.DeServiciosEBR;
import com.core.service.negocio.remote.OngEBR;
import com.web.beans.infoCatastrofe.OngBean;


@ManagedBean(name="deServicioBean")
@SessionScoped
public class DeServicioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idOng;
	private String usuario = "";
	private Date fechaRealizada;
	private String areaConocimient = "";
	private BigDecimal cantidadHoras ;
	private String ong;  
	private ArrayList<SelectItem> listaongs = new ArrayList<SelectItem>();
	
	private String index;
	
	
	
	
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
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
	public Long getIdOng() {
		return idOng;
	}
	public void setIdOng(Long idOng) {
		this.idOng = idOng;
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
	public String getAreaConocimient() {
		return areaConocimient;
	}
	public void setAreaConocimient(String areaConocimient) {
		this.areaConocimient = areaConocimient;
	}
	public BigDecimal getCantidadHoras() {
		return cantidadHoras;
	}
	public void setCantidadHoras(BigDecimal cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}
	
	
	/*****************************************************************/
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
			
			 index = new String("http://localhost:8080/proyecto-webusuario/"+nombreSinEspacio +".xhtml");
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

    	
	
	/*****************************************************************/
	
	public void registrarDonacionDeServicio(){
		DeServiciosEBR manager = null;
		Context context = null;
		CatastrofeEBR managerCatastrofe = null;		
		//boolean encontre= false;
		Context contextCatastrofe = null;
		
		OngEBR managerOng = null;		
		Context contextOng = null;
		
		
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            manager = (DeServiciosEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//DeServiciosEB!com.core.service.negocio.remote.DeServiciosEBR");
            
            contextCatastrofe = ClienteUtility.getInitialContext();
            managerCatastrofe = (CatastrofeEBR) contextCatastrofe.lookup("ejb:Proyecto-EAR/Proyecto-Core//CatastrofeEB!com.core.service.negocio.remote.CatastrofeEBR");
 
            contextOng = ClienteUtility.getInitialContext();
            managerOng = (OngEBR) contextOng.lookup("ejb:Proyecto-EAR/Proyecto-Core//OngEB!com.core.service.negocio.remote.OngEBR");
 
            
		 } catch (NamingException e) {
	            e.printStackTrace();
	        }
		try{    		   	    	  
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
            
           requestContext.update("form:display");
            requestContext.execute("PF('dlg').show()");
           
			Date fechaRealizada = new Date();
    		fechaRealizada.getTime();
    		/**fin **/
    		
    		/*
    		 * Busco la ong por el nombre seleccionado
    		*/
    		Ong aux= managerOng.buscarOngPorNick_EB(ong);
    		idOng = aux.getId();
    		
    		if (usuario.isEmpty())
    		{
    			usuario= "Anonimo";
    		}
    		
       		manager.crearDonacionDeServicios(idOng, usuario, fechaRealizada, areaConocimient, cantidadHoras);    	
       		
       		this.usuario= "";
       		this.areaConocimient = "";
       		FacesContext contexto = FacesContext.getCurrentInstance(); 
	        FacesMessage messages = new FacesMessage("Donación realizada con exito !!"); 
	        requestContext.addCallbackParam("view", "Index.xhtml");
	        
       		
       		
       		
       		
		}catch (Exception excep){
    		System.out.println("Excepcion en donacion de servicio: " + excep.getMessage());      		 			       
	        
    		
		}
	
	}
}