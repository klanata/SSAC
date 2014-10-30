package com.core.data.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

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

	public Ong(String nombre, Collection<Catastrofe> catastrofes,
			Collection<Economica> donacionesEconomicas,
			Collection<DeBienes> donacionesDeBienes,
			Collection<DeServicios> donacionesDeServicios) {
		super();
		this.nombre = nombre;
		this.catastrofes = catastrofes;
		this.donacionesEconomicas = donacionesEconomicas;
		this.donacionesDeBienes = donacionesDeBienes;
		this.donacionesDeServicios = donacionesDeServicios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Catastrofe> getCatastrofes() {
		return catastrofes;
	}

	public void setCatastrofes(Collection<Catastrofe> catastrofes) {
		this.catastrofes = catastrofes;
	}

	public Collection<Economica> getDonacionesEconomicas() {
		return donacionesEconomicas;
	}

	public void setDonacionesEconomicas(Collection<Economica> donacionesEconomicas) {
		this.donacionesEconomicas = donacionesEconomicas;
	}

	public Collection<DeBienes> getDonacionesDeBienes() {
		return donacionesDeBienes;
	}

	public void setDonacionesDeBienes(Collection<DeBienes> donacionesDeBienes) {
		this.donacionesDeBienes = donacionesDeBienes;
	}

	public Collection<DeServicios> getDonacionesDeServicios() {
		return donacionesDeServicios;
	}

	public void setDonacionesDeServicios(
			Collection<DeServicios> donacionesDeServicios) {
		this.donacionesDeServicios = donacionesDeServicios;
	}
	
	
	
}
