package com.core.data.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import cross_cuting.enums.*;

/**
 * Entity implementation class for Entity: Catastrofe
 *
 */
@Entity

@SequenceGenerator(name = "catastrofe_sequence",
sequenceName = "catastrofe_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	
@NamedQuery(name="Catastrofe.BuscarCatastrofe.NombreEvento", 
query = "SELECT c "+
		"FROM Catastrofe c " +
		"WHERE c.nombreEvento = :nombreEvento"),

@NamedQuery(name="Catastrofe.BuscarCatastrofe.Id", 
query = "SELECT c "+
		"FROM Catastrofe c " +
		"WHERE c.id = :id"),
		
@NamedQuery(name="Catastrofe.BuscarTodas", 
query = "SELECT c "+
		"FROM Catastrofe c "),

@NamedQuery(name = "Catastrofe.BuscarCatastrofeId.NombreEvento",
query = "SELECT c.id "+
		"FROM Catastrofe c " +
		"WHERE c.nombreEvento = :nombre")

})

@Table (name = "Catastrofe")
@XmlRootElement
public class Catastrofe extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE , generator = "catastrofe_sequence")
	@Column(name= "id", nullable= false)
	private long id;
	
	@Column(unique=true, nullable=false)
	private String nombreEvento = "";
	
	@Column( nullable= false)
	private String descripcion = "";
	
	@Column(nullable= false)
	private String logo = "";
	
	@Column( nullable= false)
	private double coordenadasX ;
	
	@Column(nullable= false)
	private double coordenadasY ;
	
	@Column(nullable= false)
	private Boolean activa ;
		
	@Column(nullable= false)
	private Boolean prioridad ;
	
	@Column
	private String css = "";	
	
	@OneToMany(fetch=FetchType.EAGER)
	private Set<ImagenCatastrofe> imagenes = new HashSet<ImagenCatastrofe>(0);
		
	@ManyToMany
	private Set<Servicio> servicios = new HashSet<Servicio>(0);	
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Ong> ongs = new HashSet<Ong>(0);
	
	@OneToMany
	private Set<PedidoDeAyuda> pedidosDeAyuda =  new HashSet<PedidoDeAyuda>(0);
	
	@OneToOne
	private PlanDeRiesgo planDeRiesgo ;
	
	private boolean bajaLogica;
	
	
	
	//	------------------ Constructors  --------------------------------
	
	public Catastrofe() {
		super();
		this.nombreEvento = new String();
		this.descripcion = new String();
		this.logo = new String();
		this.coordenadasX = 0;
		this.coordenadasY  = 0;
		this.activa = false;
		this.prioridad = false;		
		this.css = new String();
		this.imagenes = new HashSet<ImagenCatastrofe>();
		this.servicios = new HashSet<Servicio>();
		this.setOngs(new HashSet<Ong>(0));
		//this.ongs = new HashSet<Ong>(0);
		this.planDeRiesgo = null;	
		this.pedidosDeAyuda = new HashSet<PedidoDeAyuda>();		
		this.bajaLogica = false;
	}		
	
	
	/**
	 * @param nombreEvento
	 * @param descripcion
	 * @param logo
	 * @param coordenadasX
	 * @param coordenadasY
	 * @param activa
	 * @param prioridad
	 * @param servicios	 
	 * @param ongs
	 * @param pedidosDeAyuda
	 * @param planDeRiesgo	 
	 */
	public Catastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX, double coordenadasY,
			Boolean activa, Boolean prioridad, String css, Set<ImagenCatastrofe> imagenes, Set<Servicio> servicios,
			Set<Ong> ongs, Set<PedidoDeAyuda> pedidosDeAyuda, PlanDeRiesgo planDeRiesgo) {
		super();
		this.nombreEvento = nombreEvento;
		this.descripcion = descripcion;
		this.logo = logo;
		this.coordenadasX = coordenadasX;
		this.coordenadasY  = coordenadasY;
		this.activa = activa;
		this.prioridad = prioridad;	
		this.css = css;
		this.setImagenes(imagenes);
		this.servicios = servicios;
		this.setOngs(ongs);
		//this.ongs = ongs;
		this.pedidosDeAyuda = pedidosDeAyuda;
		this.planDeRiesgo = planDeRiesgo;			
	}
	
	
	//	------------------ Getter and setter methods ---------------------
			
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getCoordenadasX() {
		return coordenadasX;
	}
	public void setCoordenadasX(double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}
	public double getCoordenadasY() {
		return coordenadasY;
	}
	public void setCoordenadasY(double coordenadasY) {
		this.coordenadasY = coordenadasY;
	}
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	public Boolean getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Boolean prioridad) {
		this.prioridad = prioridad;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public Set<Servicio> getServicios() {
		return servicios;
	}	
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}
	public Set<Ong> getOngs() {
		return ongs;
	}
	public void setOngs(Set<Ong> catastrofes) {
		this.ongs = catastrofes;
	}
	public PlanDeRiesgo getPlanDeRiesgo() {
		return planDeRiesgo;
	}
	public void setPlanDeRiesgo(PlanDeRiesgo planDeRiesgo) {
		this.planDeRiesgo = planDeRiesgo;
	}
	public Set<ImagenCatastrofe> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<ImagenCatastrofe> imagenes) {
		this.imagenes = imagenes;
	}
	public Set<PedidoDeAyuda> getPedidosDeAyuda() {
		return pedidosDeAyuda;
	}

	public void setPedidosDeAyuda(Set<PedidoDeAyuda> pedidosDeAyuda) {
		this.pedidosDeAyuda = pedidosDeAyuda;
	}
	public boolean getBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	
   
}
