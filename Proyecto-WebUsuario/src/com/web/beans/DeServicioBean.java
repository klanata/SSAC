package com.web.beans;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.NamingException;
import clienteutility.ClienteUtility;

import com.core.service.negocio.remote.DeServiciosEBR;


@ManagedBean(name="deServicioBean")
@SessionScoped
public class DeServicioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idOng;
	private String usuario = "";
	private Date fechaRealizada;
	private String areaConocimient = "";
	private BigDecimal cantidadHoras ;
	
	
	
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
	
	
	public void registrarDonacionDeServicio(){
		DeServiciosEBR manager = null;
		Context context = null;
		try {
            // 1. Obtaining Context
            context = ClienteUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();
            // 3. Lookup and cast
            manager = (DeServiciosEBR) context.lookup("ejb:Proyecto-EAR/Proyecto-Core//DeServiciosEB!com.core.service.negocio.remote.DeServiciosEBR");
 
		 } catch (NamingException e) {
	            e.printStackTrace();
	        }
		try{    		   	    	    		
			Date fechaRealizada = new Date();
    		fechaRealizada.getTime();
       		manager.crearDonacionDeServicios(idOng, usuario, fechaRealizada, areaConocimient, cantidadHoras);    	
		}catch (Exception excep){
    		System.out.println("Excepcion en donacion de servicio: " + excep.getMessage());      		 			       
	        
    		
		}
	
	}
}