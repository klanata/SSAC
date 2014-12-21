package com.core.data.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: PersonasDesaparecidas
 *
 */
@Entity
@SequenceGenerator(name = "personaDesaparecida_sequence",
sequenceName = "personaDesaparecida_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	
@NamedQuery(name="PersonasDesaparecidas.BuscarPer.Nombre.Apellido", 
			query = "SELECT e "+
			"FROM PersonasDesaparecidas e " +
			"WHERE e.nombre = :nombre AND e.apellido = :apellido"),
			
@NamedQuery(name="PersonasDesaparecidas.BuscarPorNombreyApellido.Nombre.Apellido.Cat", 
			query = "SELECT e "+
			"FROM PersonasDesaparecidas e " +
			"WHERE e.nombre = :nomPer AND e.apellido = :apePer AND e.catastrofe = :idCatastrofe"),
					
@NamedQuery(name="PersonasDesaparecidas.BuscarPersona.Id", 
				query = "SELECT c "+
				"FROM PersonasDesaparecidas c " +
				"WHERE c.id = :id"),

@NamedQuery(name="PersonasDesaparecidas.findAll", 
				query = "SELECT c "+
				"FROM PersonasDesaparecidas c "),				
	
})
@XmlRootElement
public class PersonasDesaparecidas  extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public PersonasDesaparecidas() {
		super();
		}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "personaDesaparecida_sequence")
	@Column(name= "id", nullable= false)
	private Long id;
	
	@Column(nullable= false)
	private String nombre = "";
	
	@Column(nullable= false)
	private String apellido = "";
	
	@Column(nullable= false)
	private String numeroContacto = "";
	
	@Column(nullable= false)
	private String descripcion = "" ;
	
	@Column(nullable= false)
	private Date fechNac ;
	
	@Column(nullable= false)
	private boolean hallada;

	@OneToMany(fetch=FetchType.EAGER)
	private Set<ImagenPersonaDesaparecida> imagenes = new HashSet<ImagenPersonaDesaparecida>(0);
	
	@ManyToOne
	private Catastrofe catastrofe;
	
	//////////////CONSTRUCTORES///////////////////////////////
	
	
	public PersonasDesaparecidas (String nombre, String apellido, String numeroContacto, String descripcion, Set<ImagenPersonaDesaparecida> imagenes, Date fechNac, boolean hallada){
		super();
		this.nombre=nombre;
		this.apellido=apellido;
		this.numeroContacto=numeroContacto;
		this.descripcion=descripcion;
		this.fechNac=fechNac;
		this.imagenes=imagenes;
		this.hallada=hallada;
		
	}
	
	
	public Catastrofe getCatastrofe() {
		return catastrofe;
	}


	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getNumeroContacto() {
		return numeroContacto;
	}



	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Date getFechNac() {
		return fechNac;
	}



	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}


	



	public boolean isHallada() {
		return hallada;
	}


	public void setHallada(boolean hallada) {
		this.hallada = hallada;
	}


	public Set<ImagenPersonaDesaparecida> getImagenes() {
		return imagenes;
	}



	public void setImagenes(Set<ImagenPersonaDesaparecida> imagenes) {
		this.imagenes = imagenes;
	}
}