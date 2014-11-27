package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@NamedQueries({
	
@NamedQuery(name="Ong.BuscarOngNombre", 
query = "SELECT o "+
		"FROM Ong o " +
		"WHERE o.nombre = :nombre")

		

})

public class Ong  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public Ong() {
		super();
	}
	@Id
	@Column(name= "id", nullable= false, unique = true)
	
	private Long id;
	
	public  Long getId() {
		return id;
	}

	public void setId( Long id) {
		this.id = id;
	}
	@Column(nullable= false)
	private String nombre = "";
	
	@Column(nullable= false)
	private String direccion = "";
	
	
	private BigDecimal telefono = BigDecimal.ZERO;
	
	private String email= "";
	
	private String citioWeb = "";
	
	@Column(nullable= false)
	private String descripcion = "";
	
	@ManyToMany(mappedBy="ongs" ,cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Catastrofe> catastrofes = new HashSet<Catastrofe>(0);
		
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ong")
	private Set<Economica> donacionesEconomicas = new HashSet<Economica>(0);
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ong")
	private Set<DeBienes> donacionesDeBienes = new HashSet<DeBienes>(0);
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ong")
	private Set<DeServicios> donacionesDeServicios = new HashSet<DeServicios>(0);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public BigDecimal getTelefono() {
		return telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCitioWeb() {
		return citioWeb;
	}

	public void setCitioWeb(String citioWeb) {
		this.citioWeb = citioWeb;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Catastrofe> getCatastrofes() {
		return catastrofes;
	}

	public void setCatastrofes(Set<Catastrofe> catastrofes) {
		this.catastrofes = catastrofes;
	}

	public Set<Economica> getDonacionesEconomicas() {
		return donacionesEconomicas;
	}

	public void setDonacionesEconomicas(Set<Economica> donacionesEconomicas) {
		this.donacionesEconomicas = donacionesEconomicas;
	}

	public Set<DeBienes> getDonacionesDeBienes() {
		return donacionesDeBienes;
	}

	public void setDonacionesDeBienes(Set<DeBienes> donacionesDeBienes) {
		this.donacionesDeBienes = donacionesDeBienes;
	}

	public Collection<DeServicios> getDonacionesDeServicios() {
		return donacionesDeServicios;
	}

	public void setDonacionesDeServicios(
			Set<DeServicios> donacionesDeServicios) {
		this.donacionesDeServicios = donacionesDeServicios;
	}

	

	
	
}
