package com.core.data.entites;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DeServicios
 *
 */
@Entity

public class DeServicios extends Donacion implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public DeServicios() {
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
	private String areaConocimient = "";
	
	@Column(nullable= false)
	private BigDecimal cantidadHoras ;

	public DeServicios(String usuario, Date fechaRealizada,
			String areaConocimient, BigDecimal cantidadHoras) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.areaConocimient = areaConocimient;
		this.cantidadHoras = cantidadHoras;
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

	public String getAreaConocimient() {
		return areaConocimient;
	}

	public void setAreaConocimient(String areaConocimient) {
		this.areaConocimient = areaConocimient;
	}

	public BigDecimal getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(BigDecimal cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}
	
   
}
