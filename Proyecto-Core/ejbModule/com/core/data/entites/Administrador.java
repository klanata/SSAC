package com.core.data.entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cross_cuting.enums.sexo;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity

public class Administrador extends Persona {

	
	

	public Administrador() {
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
	
	@Column()
	private sexo sexo;
	
	@Column(nullable= false)
	private Integer celular;
	
	@OneToMany
	private Collection<Catastrofe> catastrofes = new ArrayList<Catastrofe>(0);
	
}
