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

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Collection<Ong> getCatastrofes() {
		return catastrofes;
	}
	public void setCatastrofes(Collection<Ong> catastrofes) {
		this.catastrofes = catastrofes;
	}
	public PlanDeRiesgo getPlanDeRiesgo() {
		return planDeRiesgo;
	}
	public void setPlanDeRiesgo(PlanDeRiesgo planDeRiesgo) {
		this.planDeRiesgo = planDeRiesgo;
	}
	
   
}
