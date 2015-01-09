package com.core.data.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@SequenceGenerator(name = "ong_sequence",
sequenceName = "ong_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	
@NamedQuery(name="Ong.BuscarOngNombre", 
query = "SELECT o "+
		"FROM Ong o " +
		"WHERE o.nombre = :nombre"),

@NamedQuery(name="Ong.ListarONGBajaLogicaFalse", 
query = "SELECT o "+
		"FROM Ong o " +
		"WHERE o.bajaLogica = false")

		

})
@XmlRootElement
public class Ong  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public Ong() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "ong_sequence")
	@Column(name= "id", nullable= false)
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
	
	
	private String telefono ="";
	
	private String email= "";
	
	private String citioWeb = "";
	
	@Column(length=10000,nullable= false)
	private String descripcion = "";
	
	@ManyToMany(mappedBy="ongs" ,cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Catastrofe> catastrofes = new HashSet<Catastrofe>(0);
		
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ong")
	private Set<Economica> donacionesEconomicas = new HashSet<Economica>(0);
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ong")
	private Set<DeBienes> donacionesDeBienes = new HashSet<DeBienes>(0);
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "ong")
	private Set<DeServicios> donacionesDeServicios = new HashSet<DeServicios>(0);

	
	private boolean bajaLogica;
	
	private String imagen;
	
	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean getBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}

	
	
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
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
