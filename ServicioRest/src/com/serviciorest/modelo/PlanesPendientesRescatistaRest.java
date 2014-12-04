package com.serviciorest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlanesPendientesRescatistaRest {

	private Long IdEstadoRescatista;
	private String urlArchivo;
	private double coordPedidoAyudaX;
	private double coordPedidoAyudaY;
	private String descripcion;
	
	
	public PlanesPendientesRescatistaRest() {
		super();
		IdEstadoRescatista = new Long(0);
		this.urlArchivo = "dePrueba";
		this.coordPedidoAyudaX = 0;
		this.coordPedidoAyudaY = 0;
		this.descripcion = "descripcionPrueba";
	}
	
	public Long getIdEstadoRescatista() {
		return IdEstadoRescatista;
	}
	public void setIdEstadoRescatista(Long idEstadoRescatista) {
		IdEstadoRescatista = idEstadoRescatista;
	}
	public String getUrlArchivo() {
		return urlArchivo;
	}
	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}
	public double getCoordPedidoAyudaX() {
		return coordPedidoAyudaX;
	}
	public void setCoordPedidoAyudaX(double coordPedidoAyudaX) {
		this.coordPedidoAyudaX = coordPedidoAyudaX;
	}
	public double getCoordPedidoAyudaY() {
		return coordPedidoAyudaY;
	}
	public void setCoordPedidoAyudaY(double coordPedidoAyudaY) {
		this.coordPedidoAyudaY = coordPedidoAyudaY;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
