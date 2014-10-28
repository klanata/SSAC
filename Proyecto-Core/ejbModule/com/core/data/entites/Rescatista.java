package com.core.data.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import cross_cuting.enums.sexo;

/**
 * Entity implementation class for Entity: Rescatista
 *
 */
@Entity

public class Rescatista extends Persona {

	
	private static final long serialVersionUID = 1L;

	public Rescatista() {
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
	private String nick = "";
	
	@Column(nullable= false)
	private String email = "";
	
	@Column(nullable= false)
	private String password = "";
	
	@Column(nullable= false)
	private Date fechaNac;
	
	@Column(nullable= false)
	private sexo sexo;
	
	@Column(nullable= false)
	private Integer celular;
   
}
