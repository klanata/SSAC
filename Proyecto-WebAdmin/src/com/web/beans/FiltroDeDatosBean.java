package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="filtroDeDatosBean")
@SessionScoped
public class FiltroDeDatosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String fuente;
	private String descripcion;
	private String nombreCatastrofe;
	private boolean bajaLogica;
	
	
	//	------------------ Constructors  --------------------------------
	
	public FiltroDeDatosBean() {	
	}	
	public FiltroDeDatosBean(Long id, String fuente, String descripcion, String nombreCatastrofe, Boolean bajaLogica) {
		super();
		this.id = id;	
		this.setFuente(fuente);
		this.descripcion = descripcion;
		this.nombreCatastrofe = nombreCatastrofe;
		this.bajaLogica = bajaLogica;		
	}
				
	//	------------------ Getter and setter methods ---------------------
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}		
	public String getNombreCatastrofe() {
		return nombreCatastrofe;
	}
	public void setNombreCatastrofe(String nombreCatastrofe) {
		this.nombreCatastrofe = nombreCatastrofe;
	}
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	
	
	
}
