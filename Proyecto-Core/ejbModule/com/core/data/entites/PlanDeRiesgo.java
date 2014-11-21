package com.core.data.entites;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: PlanDeRiesgo
 *
 */
@Entity
@XmlRootElement
public class PlanDeRiesgo  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public PlanDeRiesgo() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@OneToOne
	private Catastrofe catastrofe ;
   
}
