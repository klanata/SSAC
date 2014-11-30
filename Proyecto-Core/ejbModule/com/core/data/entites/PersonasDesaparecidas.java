package com.core.data.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import cross_cuting.enums.EstadoPersona;
import cross_cuting.enums.TipoCatastrofe;
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
			
@NamedQuery(name="PersonasDesaparecidas.BuscarPorNombreyApellido.Nombre.Apellido", 
			query = "SELECT e "+
			"FROM PersonasDesaparecidas e " +
			"WHERE e.nombre = :nomPer AND e.apellido = :apePer"),
					
//@NamedQuery(name="EstadoPersona.findHalladas", 
				//query = "SELECT c "+
				//"FROM PersonasDesaparecidas c " +
				//"WHERE c.id = : idPersona AND EstadoPersona = 'hallada'),

@NamedQuery(name="PersonasDesaparecidas.findAll", 
				query = "SELECT c "+
				"FROM PersonasDesaparecidas c "),				
	
})
@XmlRootElement
public class PersonasDesaparecidas  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

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
	private EstadoPersona descripcion ;
	
	@Column(nullable= false)
	private Date fechNac ;
	
	@Column(nullable= false)
	private String foto;

	@OneToMany(fetch=FetchType.EAGER)
	private Set<ImagenPersonaDesaparecida> imagenes = new HashSet<ImagenPersonaDesaparecida>(0);
	
	//////////////CONSTRUCTORES///////////////////////////////
	
	public PersonasDesaparecidas() {
		super();
		
		this.nombre = new String();
		this.apellido = new String();
		this.numeroContacto = new String();
		this.descripcion = EstadoPersona.desaparecida;
		this.fechNac = new Date();
		this.imagenes = new HashSet<ImagenPersonaDesaparecida>();
	}
	
	

	public PersonasDesaparecidas(String nombre, String apellido,
			String numeroContacto, EstadoPersona descripcion, Date fechNac,
			Set<ImagenPersonaDesaparecida>  imagenes) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroContacto = numeroContacto;
		this.descripcion = EstadoPersona.desaparecida;
		this.fechNac = fechNac;
		this.setImagenes(imagenes);
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

	public EstadoPersona getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(EstadoPersona descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechNac() {
		return fechNac;
	}

	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Set<ImagenPersonaDesaparecida> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<ImagenPersonaDesaparecida> imagenes) {
		this.imagenes = imagenes;
	}
	
	
	
	
   
}
