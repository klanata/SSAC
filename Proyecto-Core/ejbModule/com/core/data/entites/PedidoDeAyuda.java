package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoDeAyuda
 *
 */
@Entity

public class PedidoDeAyuda implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public PedidoDeAyuda() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column(nullable= false)
	private String descripcion = "";
	
	@Column(nullable= false)
	private Boolean procesando ;
	

	@Column(nullable= false)
	private Boolean valido ;
	
	@Column( nullable= false)
	private BigDecimal coordenadasX ;
	
	@Column(nullable= false)
	private BigDecimal coordenadasY ;
	
	@Column(nullable= false)
	private Date fechaPublicacion ;

	public PedidoDeAyuda(String descripcion, Boolean procesando,
			Boolean valido, BigDecimal coordenadasX, BigDecimal coordenadasY,
			Date fechaPublicacion) {
		super();
		this.descripcion = descripcion;
		this.procesando = procesando;
		this.valido = valido;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.fechaPublicacion = fechaPublicacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getProcesando() {
		return procesando;
	}

	public void setProcesando(Boolean procesando) {
		this.procesando = procesando;
	}

	public Boolean getValido() {
		return valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public BigDecimal getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(BigDecimal coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public BigDecimal getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(BigDecimal coordenadasY) {
		this.coordenadasY = coordenadasY;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
    
}
