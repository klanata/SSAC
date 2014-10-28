package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Catastrofe
 *
 */
@Entity
@Table (name = "Catastrofe")
public class Catastrofe implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Catastrofe() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( nullable= false)
	private Integer id;
	
	@Column(nullable= false)
	private String nombreEvento = "";
	
	@Column( nullable= false)
	private String descripcion = "";
	
	@Column(nullable= false)
	private String logo = "";
	
	@Column( nullable= false)
	private BigDecimal coordenadasX ;
	
	@Column(nullable= false)
	private BigDecimal coordenadasY ;
	
	@Column(nullable= false)
	private Boolean activa ;
	
	@Column(nullable= false)
	private Boolean prioridad ;
	
	@ManyToMany
	private Collection<Servicio> servicios = new ArrayList<Servicio>(0);
   
	@ManyToMany
	private Collection<Ong> catastrofes = new ArrayList<Ong>(0);
	@OneToOne
	private PlanDeRiesgo planDeRiesgo ;
   
}
