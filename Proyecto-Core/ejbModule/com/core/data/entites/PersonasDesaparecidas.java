package com.core.data.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import cross_cuting.enums.EstadoPersona;
/**
 * Entity implementation class for Entity: PersonasDesaparecidas
 *
 */
@Entity

public class PersonasDesaparecidas  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public PersonasDesaparecidas() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
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
	
	@ManyToOne
	private ImagenPersonaDesaparecida imagenPersonaDesaparecida;

	public PersonasDesaparecidas(String nombre, String apellido,
			String numeroContacto, EstadoPersona descripcion, Date fechNac,
			ImagenPersonaDesaparecida imagenPersonaDesaparecida) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroContacto = numeroContacto;
		this.descripcion = descripcion;
		this.fechNac = fechNac;
		this.imagenPersonaDesaparecida = imagenPersonaDesaparecida;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public ImagenPersonaDesaparecida getImagenPersonaDesaparecida() {
		return imagenPersonaDesaparecida;
	}

	public void setImagenPersonaDesaparecida(
			ImagenPersonaDesaparecida imagenPersonaDesaparecida) {
		this.imagenPersonaDesaparecida = imagenPersonaDesaparecida;
	}
	
	
	
   
}
