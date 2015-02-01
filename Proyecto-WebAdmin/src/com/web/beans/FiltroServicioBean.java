package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="filtroServicioBean")
@SessionScoped
public class FiltroServicioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;			
	private String descripcion;	
	private String fuente;
	private boolean bajaLogica;
	
	
	//	------------------ Constructors  --------------------------------
	
	public FiltroServicioBean() {	
	}	
	public FiltroServicioBean(Long id, String fuente, String descripcion, boolean bajaLogica) {
		super();
		this.id = id;		
		this.descripcion = descripcion;
		this.fuente = fuente;	
		this.setBajaLogica(bajaLogica);
	}	
	
	//	------------------ Getter and setter methods ---------------------
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}	
	

}
