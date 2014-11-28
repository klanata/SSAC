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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false, unique = true)
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
	
	@Enumerated(EnumType.STRING)
	private TipoCatastrofe tipoCatastrofe;
	
	@ManyToMany
	private Set<Servicio> servicios = new HashSet<Servicio>(0);   
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Ong> ongs = new HashSet<Ong>(0);
	
	@OneToMany
	private Set<PedidoDeAyuda> pedidosDeAyuda =  new HashSet<PedidoDeAyuda>(0);
	
	@OneToOne
	private PlanDeRiesgo planDeRiesgo ;
	
	
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
		this.servicios = new HashSet<Servicio>();
		this.setOngs(new HashSet<Ong>(0));
		//this.ongs = new HashSet<Ong>(0);
		this.planDeRiesgo = null;	
		this.pedidosDeAyuda = new HashSet<PedidoDeAyuda>();
		this.tipoCatastrofe= TipoCatastrofe.climaticas;
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
	public Catastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX,
		   double coordenadasY,Boolean activa, Boolean prioridad, Set<Servicio> servicios,
		   Set<Ong> ongs, Set<PedidoDeAyuda> pedidosDeAyuda, PlanDeRiesgo planDeRiesgo) {
		super();
		this.nombreEvento = nombreEvento;
		this.descripcion = descripcion;
		this.logo = logo;
		this.coordenadasX = coordenadasX;
		this.coordenadasY  = coordenadasY;
		this.activa = activa;
		this.prioridad = prioridad;		
		this.servicios = servicios;
		this.setOngs(ongs);
		//this.ongs = ongs;
		this.pedidosDeAyuda = pedidosDeAyuda;
		this.planDeRiesgo = planDeRiesgo;	
		this.tipoCatastrofe= TipoCatastrofe.climaticas;
	}
	
	
	//	------------------ Getter and setter methods ---------------------
	
	
	public TipoCatastrofe getTipoCatastrofe() {
		return tipoCatastrofe;
	}

	public void setTipoCatastrofe(TipoCatastrofe tipoCatastrofe) {
		this.tipoCatastrofe = tipoCatastrofe;
	}

	public Set<PedidoDeAyuda> getPedidosDeAyuda() {
		return pedidosDeAyuda;
	}

	public void setPedidosDeAyuda(Set<PedidoDeAyuda> pedidosDeAyuda) {
		this.pedidosDeAyuda = pedidosDeAyuda;
	}
		
	
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
	
   
}
