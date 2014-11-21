package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@NamedQueries({
	
@NamedQuery(name="Ong.BuscarOngNombre", 
query = "SELECT o "+
		"FROM Ong o " +
		"WHERE o.nombre = :nombre")

		

})
@XmlRootElement
public class Ong  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public Ong() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	@ManyToMany
	private Collection<Catastrofe> catastrofes = new ArrayList<Catastrofe>(0);
		
	
	@OneToMany
	private Collection<Economica> donacionesEconomicas = new ArrayList<Economica>(0);
	
	@OneToMany
	private Collection<DeBienes> donacionesDeBienes = new ArrayList<DeBienes>(0);
	
	@OneToMany
	private Collection<DeServicios> donacionesDeServicios = new ArrayList<DeServicios>(0);

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
