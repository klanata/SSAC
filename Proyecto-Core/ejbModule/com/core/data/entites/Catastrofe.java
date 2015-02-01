package com.core.data.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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
		"FROM Catastrofe c "+
		"WHERE c.bajaLogica = :bajaLogica"),

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
	
	@Column(length=10000,nullable= false)
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
			
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Ong> ongs = new HashSet<Ong>(0);
	
	@OneToMany(fetch=FetchType.EAGER)
	private Set<PedidoDeAyuda> pedidosDeAyuda =  new HashSet<PedidoDeAyuda>(0);
	
	@OneToMany(fetch=FetchType.EAGER)
	private Set<PersonasDesaparecidas> personasDesaparecidas =  new HashSet<PersonasDesaparecidas>(0);
	
	@OneToOne
	private PlanDeRiesgo planDeRiesgo;
	
	private boolean bajaLogica;
	
	@Column(length=10000,nullable= false)
	private String poligono = "";
	
	@OneToMany(fetch=FetchType.EAGER)
	private Set<FiltroServicio> filtroServicio =  new HashSet<FiltroServicio>(0);
	
	
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
		
		this.setOngs(new HashSet<Ong>(0));
		//this.ongs = new HashSet<Ong>(0);
		this.planDeRiesgo = null;	
		this.pedidosDeAyuda = new HashSet<PedidoDeAyuda>();		
		this.personasDesaparecidas = new HashSet<PersonasDesaparecidas>();
		this.bajaLogica = false;
		this.poligono = new String();
	}		
	
	
	/**
	 * @param nombreEvento
	 * @param descripcion
	 * @param logo
	 * @param coordenadasX
	 * @param coordenadasY
	 * @param activa
	 * @param prioridad
	 * @param filtros	 
	 * @param ongs
	 * @param pedidosDeAyuda
	 * @param planDeRiesgo
	 * @param personasDesaparecidas	 
	 * @param bajaLogica	
	 * @param poligono
	 */
	public Catastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX, double coordenadasY,
			Boolean activa, Boolean prioridad, String css, Set<ImagenCatastrofe> imagenes, Set<Filtro> filtros,
			Set<Ong> ongs, Set<PedidoDeAyuda> pedidosDeAyuda, PlanDeRiesgo planDeRiesgo, boolean bajaLogica,
			Set<PersonasDesaparecidas> personasDesaparecidas,String poligono) {
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
		this.setOngs(ongs);
		//this.ongs = ongs;
		this.pedidosDeAyuda = pedidosDeAyuda;
		this.planDeRiesgo = planDeRiesgo;	
		this.personasDesaparecidas = personasDesaparecidas;
		this.bajaLogica = bajaLogica;
		this.poligono = poligono;
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
		
	public Set<PersonasDesaparecidas> getPersonasDesaparecidas() {
		return personasDesaparecidas;
	}
	public void setPersonasDesaparecidas(
			Set<PersonasDesaparecidas> personasDesaparecidas) {
		this.personasDesaparecidas = personasDesaparecidas;
	}
	public String getPoligono() {
		return poligono;
	}
	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}
	public Set<FiltroServicio> getFiltroServicio() {
		return filtroServicio;
	}
	public void setFiltroServicio(Set<FiltroServicio> filtroServicio) {
		this.filtroServicio = filtroServicio;
	}
	
   
}
