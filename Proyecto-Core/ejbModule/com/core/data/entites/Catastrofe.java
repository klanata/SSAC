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
public class Catastrofe extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column Long id;
	
	@Column(unique=true, nullable=false)
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
	
	
	private String tipoCatastrofe;
	
	public String getTipoCatastrofe() {
		return tipoCatastrofe;
	}

	public void setTipoCatastrofe(String tipoCatastrofe) {
		this.tipoCatastrofe = tipoCatastrofe;
	}

	public Collection<PedidoDeAyuda> getPedidosDeAyuda() {
		return pedidosDeAyuda;
	}

	public void setPedidosDeAyuda(Collection<PedidoDeAyuda> pedidosDeAyuda) {
		this.pedidosDeAyuda = pedidosDeAyuda;
	}
	@ManyToMany
	private Collection<Servicio> servicios = new ArrayList<Servicio>(0);
   
	@ManyToMany
	private Collection<Ong> ongs = new ArrayList<Ong>(0);
	
	@OneToMany
	private Collection<PedidoDeAyuda> pedidosDeAyuda =  new ArrayList<PedidoDeAyuda>(0);
	
	@OneToOne
	private PlanDeRiesgo planDeRiesgo ;

	public Catastrofe() {
		super();
		this.nombreEvento = new String();
		this.descripcion = new String();
		this.logo = new String();
		this.coordenadasX = new BigDecimal(0);
		this.coordenadasY  = new BigDecimal(0);
		this.activa = false;
		this.prioridad = false;		
		this.servicios = new ArrayList<Servicio>();
		this.ongs = new ArrayList<Ong>();
		this.planDeRiesgo = null;	
		this.tipoCatastrofe= "";
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public BigDecimal getCoordenadasX() {
		return coordenadasX;
	}
	public void setCoordenadasX(BigDecimal coordenadasX) {
		this.coordenadasX = coordenadasX;
	}
	public BigDecimal getCoordenadasY() {
		return coordenadasY;
	}
	public void setCoordenadasY(BigDecimal coordenadasY) {
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
	public Collection<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(Collection<Servicio> servicios) {
		this.servicios = servicios;
	}
	public Collection<Ong> getOngs() {
		return ongs;
	}
	public void setOngs(Collection<Ong> catastrofes) {
		this.ongs = catastrofes;
	}
	public PlanDeRiesgo getPlanDeRiesgo() {
		return planDeRiesgo;
	}
	public void setPlanDeRiesgo(PlanDeRiesgo planDeRiesgo) {
		this.planDeRiesgo = planDeRiesgo;
	}
	
   
}
