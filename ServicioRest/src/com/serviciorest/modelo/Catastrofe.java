package com.serviciorest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Catastrofe {
	
	private Long id;
	private String nombreEvento;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	
}
