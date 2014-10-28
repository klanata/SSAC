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

public class PersonasDesaparecidas implements Serializable {

	
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
	
   
}
