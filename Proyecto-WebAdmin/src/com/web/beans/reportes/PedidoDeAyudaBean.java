package com.web.beans.reportes;

import java.io.Serializable;
import java.util.Date;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.core.data.entites.Catastrofe;
@ManagedBean(name="PedidoAyudaBean")
@RequestScoped
public class PedidoDeAyudaBean  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	
	
	private String descripcion = "";
	
	private Double coordenadasX ;
	private Double coordenadasY ;
	
	private Date fechaPublicacion ;
	private Catastrofe catastrofe;
	private boolean bajaLogica;
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
	public Double getCoordenadasX() {
		return coordenadasX;
	}
	public void setCoordenadasX(Double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}
	public Double getCoordenadasY() {
		return coordenadasY;
	}
	public void setCoordenadasY(Double coordenadasY) {
		this.coordenadasY = coordenadasY;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Catastrofe getCatastrofe() {
		return catastrofe;
	}
	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	public PedidoDeAyudaBean(Long id, String descripcion, Double coordenadasX,
			Double coordenadasY, Date fechaPublicacion, Catastrofe catastrofe,
			boolean bajaLogica) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.fechaPublicacion = fechaPublicacion;
		this.catastrofe = catastrofe;
		this.bajaLogica = bajaLogica;
	}	
	
	public PedidoDeAyudaBean() {
		super();

	}
	}
