package com.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.core.data.entites.Catastrofe;


@ManagedBean(name="imagenCatastrofeBean")
@SessionScoped
public class ImagenCatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String path;	
	private Catastrofe catastrofe;
		
	
	//	------------------ Constructors  --------------------------------
	
	
	public ImagenCatastrofeBean() {	
	}	
	public ImagenCatastrofeBean(Long id, String path, Catastrofe catastrofe) {
		super();
		this.id = id;
		this.path = path;
		this.setCatastrofe(catastrofe);
		
	}
	
	
	//	------------------ Getter and setter methods ---------------------
	
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
	public Catastrofe getCatastrofe() {
		return catastrofe;
	}
	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	
	
		

}
