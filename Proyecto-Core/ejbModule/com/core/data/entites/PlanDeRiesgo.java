package com.core.data.entites;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PlanDeRiesgo
 *
 */
@Entity

public class PlanDeRiesgo implements Serializable {

	
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
