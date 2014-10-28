package com.core.data.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Servicio
 *
 */
@Entity
@Table (name = "Servicio")
public class Servicio implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Servicio() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column(name= "url",  nullable= false)
	private String url = "";
	
	@Column(name= "fuente",  nullable= false)
	private String fuente = "";
	
	//private Collection<String> filtros = new ArrayList<String>(0);
	
	@ManyToMany
	private Collection<Catastrofe> catastrofes = new ArrayList<Catastrofe>(0);
   
}
