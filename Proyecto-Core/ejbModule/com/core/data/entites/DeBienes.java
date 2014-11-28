package com.core.data.entites;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: DeBienes
 *
 */
@Entity
@NamedQueries({
	
@NamedQuery(name="DeBienes.BuscarDonacion.DeOng", 
query = "SELECT d FROM DeBienes d WHERE d.ong.id = :idOng"),

@NamedQuery(name="DeBienes.BuscarDonacion", 
query = "SELECT d FROM DeBienes d WHERE d.id = :id")

})
@XmlRootElement
public class DeBienes extends AbstractEntity  implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public DeBienes() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false)
	private Long id;
	
	
	@Column()
	private String usuario = "";
	

	@Column(nullable =false)
	private Date fechaRealizada;
	
	@Column(nullable= false)
	private String nombreItem = "";
	
	@Column(nullable= false)
	private Integer cantidad;
	
	@ManyToOne
	private Ong ong;

	
	public DeBienes(String usuario, Date fechaRealizada, String nombreItem,
			Integer cantidad, Ong ong) {
		super();
		this.usuario = usuario;
		this.fechaRealizada = fechaRealizada;
		this.nombreItem = nombreItem;
		this.cantidad = cantidad;
		this.ong = ong;
	}

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
	
	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong Ong) {
		this.ong = Ong;
	}
	
}
