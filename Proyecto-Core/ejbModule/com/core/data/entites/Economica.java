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

@SequenceGenerator(name = "economica_sequence",
sequenceName = "economica_id_seq",
initialValue=1,
allocationSize=1)

@NamedQueries({
	
@NamedQuery(name="Economica.BuscarDonacion.DeOng", 
query = "SELECT d FROM Economica d WHERE d.ong.id = :idOng"),

@NamedQuery(name="Economica.BuscarDonacion", 
query = "SELECT d FROM Economica d WHERE d.id = :id")

})

@XmlRootElement
public class Economica extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "economica_sequence")
	@Column(name= "id", nullable= false)
	private Long id;
	
	
	@Column()
	private String usuario = "";
	

	@Column(nullable =false)
	private Date fechaRealizada;
	

	@Column(nullable =false)
	private BigDecimal monto;
	
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
