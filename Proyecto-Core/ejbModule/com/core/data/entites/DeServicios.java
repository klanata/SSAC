package com.core.data.entites;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: DeServicios
 *
 */
@Entity
@NamedQueries({
	
@NamedQuery(name="DeServicios.BuscarDonacion.DeOng", 
query = "SELECT d FROM DeServicios d WHERE d.ong.id = :idOng"),

@NamedQuery(name="DeServicios.BuscarDonacion", 
query = "SELECT d FROM DeServicios d WHERE d.id = :id")

})

@XmlRootElement
public class DeServicios extends AbstractEntity  implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public DeServicios() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false)
	private Long id;
	
	private String usuario = "";
	

	@Column(nullable =false)
	private Date fechaRealizada;
	
	@Column(nullable= false)
	private String areaConocimient = "";
	
	@Column(nullable= false)
	private BigDecimal cantidadHoras ;
	
	@ManyToOne 
	private Ong ong;


	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong Ong) {
		this.ong = Ong;
	}
   
}
