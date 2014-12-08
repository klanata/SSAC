package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="filtroYoutubeBean")
@SessionScoped
public class FiltroYoutubeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String descripcion;
	private boolean bajaLogica;
	
	
//	------------------ Constructors  --------------------------------
	
	public FiltroYoutubeBean() {	
	}	
	public FiltroYoutubeBean(Long id, String descripcion, Boolean bajaLogica) {
		super();
		this.id = id;		
		this.descripcion = descripcion;
		this.bajaLogica = bajaLogica;		
	}
				
	//	------------------ Getter and setter methods ---------------------
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	
	

	
	
}
