package com.web.beans.reportes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



@ManagedBean(name="DonacionDeServicioBean")
@RequestScoped
public class DonacionDeServicioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String usuario ;
	private Date fechaRealizada;
	
	
	private String areaConocimient;
	
	
	private BigDecimal cantidadHoras ;
	
	 
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	


	public DonacionDeServicioBean(String usuario, Date fechaRealizada,
			String areaConocimient, BigDecimal cantidadHoras) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.areaConocimient = areaConocimient;
		this.cantidadHoras = cantidadHoras;
		
	}
	
	public DonacionDeServicioBean() {
		super();
	}

}
