package com.core.data.entites;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Economica
 *
 */
@Entity

public class Economica extends Donacion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Economica() {
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
	

	@Column()
	private BigDecimal monto;

	public Economica(String usuario, Date fechaRealizada, BigDecimal monto) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.monto = monto;
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

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
   
}
