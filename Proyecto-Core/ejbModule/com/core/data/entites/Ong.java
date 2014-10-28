package com.core.data.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import cross_cuting.enums.TipoCatastrofe;

/**
 * Entity implementation class for Entity: Ong
 *
 */
@Entity

public class Ong implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Ong() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column(nullable= false)
	private String nombre = "";
	
	@ManyToMany
	private Collection<Catastrofe> catastrofes = new ArrayList<Catastrofe>(0);
	
	//private List<TipoCatastrofe> TiposdeCatastrofes = new ArrayList<TipoCatastrofe>(0);
	
	@OneToMany
	private Collection<Economica> donacionesEconomicas = new ArrayList<Economica>(0);
	
	@OneToMany
	private Collection<DeBienes> donacionesDeBienes = new ArrayList<DeBienes>(0);
	
	@OneToMany
	private Collection<DeServicios> donacionesDeServicios = new ArrayList<DeServicios>(0);
	
}
