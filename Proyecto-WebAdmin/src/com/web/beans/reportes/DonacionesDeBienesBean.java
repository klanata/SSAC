package com.web.beans.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;

@ManagedBean(name="DonacionesDeBienesBean")
@RequestScoped
public class DonacionesDeBienesBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fechaRealizada;
	
	
	private String nombreItem ;
	
	private Integer cantidad;

	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public String getNombreItem() {
		return nombreItem;
	}

	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
	public DonacionesDeBienesBean() {
		super();
		
	}
	public DonacionesDeBienesBean(Date fechaRealizada, String nombreItem,
			Integer cantidad) {
		super();
		this.fechaRealizada = fechaRealizada;
		this.nombreItem = nombreItem;
		this.cantidad = cantidad;
	}
	
	

}
