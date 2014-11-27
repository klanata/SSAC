package com.core.data.entites;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: PlanDeRiesgo
 *
 */
@Entity
@NamedQuery(name="PlanDeRiesgo.BuscarPlanDeRiesgo", 
query = "SELECT p "+
		"FROM PlanDeRiesgo p " +
		"WHERE p.id = :id")



@XmlRootElement
public class PlanDeRiesgo  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public PlanDeRiesgo() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catastrofe getCatastrofe() {
		return catastrofe;
	}

	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	@OneToOne
	private Catastrofe catastrofe ;
	
	private String rutaArchivo;

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
   
}
