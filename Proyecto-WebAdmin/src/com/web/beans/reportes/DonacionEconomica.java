package com.web.beans.reportes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import com.core.data.entites.Ong;

@ManagedBean(name="DonacionEconomica")
@RequestScoped
public class DonacionEconomica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	
	
	private String usuario = "";
		
	private Date fechaRealizada;
	

	
	private BigDecimal monto;
	
	 
	private Ong ong;


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


	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}


	public Ong getOng() {
		return ong;
	}


	public void setOng(Ong ong) {
		this.ong = ong;
	}

	public DonacionEconomica() {
		super();
	}
	
	public DonacionEconomica(String usuario, Date fechaRealizada,
			BigDecimal monto) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.monto = monto;
		
	}
	

}
