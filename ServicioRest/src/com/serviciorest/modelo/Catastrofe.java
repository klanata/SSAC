package com.serviciorest.modelo;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Catastrofe {
	
	private Long id;
	private String nombreEvento;
	private String descripcion = "";
	private Boolean activa ;
	private double coordenadasX ;
	private double coordenadasY ;
	private String urlCSS;
	
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
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getCoordenadasX() {
		return coordenadasX;
	}
	public void setCoordenadasX(double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}
	public double getCoordenadasY() {
		return coordenadasY;
	}
	public void setCoordenadasY(double coordenadasY) {
		this.coordenadasY = coordenadasY;
	}
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	public String getUrlCSS() {
		return urlCSS;
	}
	public void setUrlCSS(String urlCSS) {
		this.urlCSS = urlCSS;
	}
	
}