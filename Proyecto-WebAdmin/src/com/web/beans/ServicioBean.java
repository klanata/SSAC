package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="servicioBean")
@SessionScoped
public class ServicioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String url;
	private String fuente;
	
	
	
	//	------------------ Constructors  --------------------------------
	
	public ServicioBean() {	
	}	
	public ServicioBean(Long id, String url, String fuente) {
		super();
		this.id = id;	
		this.url = url;
		this.fuente = fuente;
			
	}	
				
	//	------------------ Getter and setter methods ---------------------
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	
	
	
}
