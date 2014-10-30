package com.core.data.entites;


import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenPersonaDesaparecida
 *
 */
@Entity

public class ImagenPersonaDesaparecida extends Imagen implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ImagenPersonaDesaparecida() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column()
	private String path = "";
    @ManyToOne
    private PersonasDesaparecidas personasDesaparecidas;

	public ImagenPersonaDesaparecida(String path,
			PersonasDesaparecidas personasDesaparecidas) {
		super();
		this.path = path;
		this.personasDesaparecidas = personasDesaparecidas;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public PersonasDesaparecidas getPersonasDesaparecidas() {
		return personasDesaparecidas;
	}

	public void setPersonasDesaparecidas(PersonasDesaparecidas personasDesaparecidas) {
		this.personasDesaparecidas = personasDesaparecidas;
	}
    
}
