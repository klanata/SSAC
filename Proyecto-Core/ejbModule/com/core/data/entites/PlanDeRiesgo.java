package com.core.data.entites;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: PlanDeRiesgo
 *
 */
@Entity

@NamedQueries({
	
@NamedQuery(name="PlanDeRiesgo.BuscarPlanDeRiesgo", 
query = "SELECT p "+
		"FROM PlanDeRiesgo p " +
		"WHERE p.id = :id"),
@NamedQuery(name="PlanDeRiesgo.BuscarPlanDeRiesgoArchivo", 
query = "SELECT p "+
		"FROM PlanDeRiesgo p " +
		"WHERE p.rutaArchivo = :archivo")

})


@XmlRootElement
public class PlanDeRiesgo  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false)
	private Long id;

	@OneToOne
	private Catastrofe catastrofe ;
	
	private String rutaArchivo;		

	
	public PlanDeRiesgo() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Catastrofe getCatastrofe() {
		return catastrofe;
	}

	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}
   
}
