package com.core.data.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Servicio
 *
 */
@Entity
@SequenceGenerator(name = "servicio_sequence",
sequenceName = "servicio_id_seq",
initialValue=1,
allocationSize=1)


@NamedQueries({
	
@NamedQuery(name="Servicio.BuscarServicio.Fuente", 
query = "SELECT s "+
		"FROM Servicio s " +
		"WHERE s.fuente = :fuente"),

@NamedQuery(name="Servicio.BuscarServicio.Id", 
query = "SELECT s "+
		"FROM Servicio s " +
		"WHERE s.id = :id"),
		
@NamedQuery(name="Servicio.BuscarTodos", 
query = "SELECT s "+
		"FROM Servicio s "),

@NamedQuery(name = "Servicio.BuscarServicioId.Fuente",
query = "SELECT s.id "+
		"FROM Servicio s " +
		"WHERE s.fuente = :fuente"),

})


@Table (name = "Servicio")
@XmlRootElement
public class Servicio  extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "servicio_sequence")
	@Column(name= "id", nullable= false)
	private Long id;
	
	@Column(name= "url",  nullable= false)
	private String url = "";
	
	@Column(name= "fuente",  nullable= false)
	private String fuente = "";		
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Filtro> filtros = new  HashSet<Filtro>(0);
	
	
	
	//	------------------ Constructors  --------------------------------
	public Servicio() {
		super();
		this.url = new String();
		this.fuente = new String();		
		this.filtros = new HashSet<Filtro>();		
	}
	
	public Servicio(String url, String fuente, Set<Filtro> filtros) {
		super();
		this.url = url;
		this.fuente = fuente;		
		this.filtros = filtros;
	}
	
	
	//	------------------ Getter and setter methods ---------------------
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public Set<Filtro> getFiltros() {
		return filtros;
	}

	public void setFiltros(Set<Filtro> filtros) {
		this.filtros = filtros;
	}
	
	
	
   
}
