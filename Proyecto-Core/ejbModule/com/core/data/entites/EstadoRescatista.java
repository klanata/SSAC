package com.core.data.entites;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	
@NamedQuery(name="EstadoRescatista.FindPendientes", 
query = "SELECT e "+
		"FROM EstadoRescatista e, Rescatista r " +
		"WHERE e.pendiente = :true AND r.id= :idRescatista AND e.rescatista.id = r.id"),

@NamedQuery(name="EstadoRescatista.FindEstadoRescatista", 
query = "SELECT e "+
		"FROM EstadoRescatista e " +
		"WHERE e.id = :idEstado"),
		
		
})

public class EstadoRescatista  extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( nullable= false)
	private Integer id;
	
	public EstadoRescatista() {
		super();
	}
	@Column( nullable= false)
	private Boolean pendiente;
	@Column( nullable= false)
	private Catastrofe catastrofe;

	@ManyToOne
	private Rescatista rescatista;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getPendiente() {
		return pendiente;
	}
	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}
	public Catastrofe getCatastrofe() {
		return catastrofe;
	}
	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	public Rescatista getRescatista() {
		return rescatista;
	}
	public void setRescatista(Rescatista rescatista) {
		this.rescatista = rescatista;
	}
	
	
}
