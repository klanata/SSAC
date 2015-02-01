package com.core.data.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Filtro
 *
 */
@Entity

@SequenceGenerator(name = "filtro_sequence",
sequenceName = "filtro_id_seq",
initialValue=1,
allocationSize=1)

@NamedQueries({
	
@NamedQuery(name="Filtro.BuscarFiltro.Descripcion", 
query = "SELECT f "+
		"FROM Filtro f " +
		"WHERE f.descripcion = :descripcion"),

@NamedQuery(name="Filtro.BuscarFiltro.Id", 
query = "SELECT f "+
		"FROM Filtro f " +
		"WHERE f.id = :id"),
		
@NamedQuery(name="Filtro.BuscarTodos", 
query = "SELECT f "+
		"FROM Filtro f "+
		"WHERE f.bajaLogica = :bajaLogica"),

@NamedQuery(name = "Filtro.BuscarFiltroId.Descripcion",
query = "SELECT f.id "+
		"FROM Filtro f " +
		"WHERE f.descripcion = :descripcion"),

@NamedQuery(name="Filtro.BuscarYoutube", 
query = "SELECT f "+
		"FROM Filtro f, IN (f.servicios) s "+
		"WHERE f.bajaLogica = :bajaLogica AND " +
		"s.fuente = :fuente "),
		
@NamedQuery(name="Filtro.BuscarPorServicio", 
query = "SELECT f "+
		"FROM Filtro f, IN (f.servicios) s "+
		"WHERE f.bajaLogica = :bajaLogica AND " +
		"s.fuente = :fuente "),

})



@Table (name = "filtro")
@XmlRootElement
public class Filtro extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE , generator = "filtro_sequence")
	@Column(name= "id", nullable= false)
	private long id;		
	
	@Column(nullable= false)
	private String descripcion = "";				
		
	@ManyToMany(mappedBy="filtros",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Servicio> servicios = new HashSet<Servicio>(0);			
	
	private boolean bajaLogica;
	
	
	
	//	------------------ Constructors  --------------------------------
	
	public Filtro() {
		super();		
		this.descripcion = new String();		
		this.servicios = new HashSet<Servicio>();		
		this.bajaLogica = false;
	}		
	
	
	/**	 
	 * @param descripcion
	 * @param servicios	 
	 * @param bajaLogica	 	
	 */
	public Filtro(String descripcion,Set<Servicio> servicios, Set<Catastrofe> catastrofes, boolean bajaLogica) {
		super();
		
		this.descripcion = descripcion;
		this.servicios = servicios;		
		this.bajaLogica = bajaLogica;			
	}
	
	
	//	------------------ Getter and setter methods ---------------------


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}	
	
	
   
}
