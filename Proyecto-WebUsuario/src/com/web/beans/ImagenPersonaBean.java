package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="imagenPersonaBean")
@SessionScoped
public class ImagenPersonaBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String path;	
	
	
	ImagenPersonaBean(){
		super();
	}
	public ImagenPersonaBean(Long id, String path) {
		super();
		this.id = id;
		this.path = path;		
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

		

}
