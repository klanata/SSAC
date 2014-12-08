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
	
	
	//	------------------ Constructors  --------------------------------
	
	public FiltroServicioBean() {	
	}	
	public FiltroServicioBean(Long id, String descripcion,String fuente) {
		super();
		this.id = id;		
		this.descripcion = descripcion;
		this.fuente = fuente;	
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
	

}
