package com.core.data.entites;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DeBienes
 *
 */
@Entity

public class DeBienes extends Donacion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public DeBienes() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	
	@Column()
	private String usuario = "";
	

	@Column(nullable =false)
	private Date fechaRealizada;
	
	@Column(nullable= false)
	private String nombreItem = "";
	
	@Column(nullable= false)
	private Integer cantidad;

	public DeBienes(String usuario, Date fechaRealizada, String nombreItem,
			Integer cantidad) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.nombreItem = nombreItem;
		this.cantidad = cantidad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaRealizada(Date fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public String getNombreItem() {
		return nombreItem;
	}

	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
