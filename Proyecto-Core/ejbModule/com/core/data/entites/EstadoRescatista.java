
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
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@SequenceGenerator(name = "estadoRescatista_sequence",
sequenceName = "estadoRescatista_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	
@NamedQuery(name="EstadoRescatista.FindPendientes", 
query = "SELECT e "+
		"FROM EstadoRescatista e, Rescatista r " +
		"WHERE e.pendiente = true AND r.id = :idRescatista AND e.rescatista.id = r.id"),

@NamedQuery(name="EstadoRescatista.FindEstadoRescatista", 
query = "SELECT e "+
		"FROM EstadoRescatista e " +
		"WHERE e.id = :idEstado"),
		
		
})
@XmlRootElement
public class EstadoRescatista  extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "estadoRescatista_sequence")
	@Column( nullable= false)
	private Long id;
	
	public EstadoRescatista() {
		super();
	}
	@Column( nullable= false)
	private Boolean pendiente;
	
	
	private Long idPedidoAyuda;

	
	@ManyToOne
	private Rescatista rescatista;
	
	private String nombreTarea;

	
	
	
	public Long getIdPedidoAyuda() {
		return idPedidoAyuda;
	}
	public void setIdPedidoAyuda(Long idPedidoAyuda) {
		this.idPedidoAyuda = idPedidoAyuda;
	}
	public String getNombreTarea() {
		return nombreTarea;
	}
	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getPendiente() {
		return pendiente;
	}
	public void setPendiente(Boolean pendiente) {
		this.pendiente = pendiente;
	}
	
	public Rescatista getRescatista() {
		return rescatista;
	}
	public void setRescatista(Rescatista rescatista) {
		this.rescatista = rescatista;
	}
	
	
}
