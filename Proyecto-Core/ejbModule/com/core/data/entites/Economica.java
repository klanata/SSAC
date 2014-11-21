package com.core.data.entites;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Economica
 *
 */
@Entity
@XmlRootElement
public class Economica extends Donacion implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Long id;
	
	
	@Column()
	private String usuario = "";
	

	@Column(nullable =false)
	private Date fechaRealizada;
	

	@Column()
	private BigDecimal monto;
	
	@ManyToOne 
	private Ong ong;
	
	public Economica() {
		super();
		this.ong = null; 
		this.usuario = new String();;
		this.fechaRealizada = new Date();
		this.monto = new BigDecimal(0);
	}
/*	public Economica(String usuario, Date fechaRealizada, BigDecimal monto) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.monto = monto;
	}*/

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
	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong Ong) {
		this.ong = Ong;
	}
}
