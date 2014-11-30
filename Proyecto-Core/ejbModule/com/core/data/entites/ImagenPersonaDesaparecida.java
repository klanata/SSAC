package com.core.data.entites;


import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: ImagenPersonaDesaparecida
 *
 */
@Entity
@SequenceGenerator(name = "imagenPersonaDesa_sequence",
sequenceName = "imagenPersonaDesa_id_seq",
initialValue=1,
allocationSize=1)
@XmlRootElement
public class ImagenPersonaDesaparecida extends Imagen implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ImagenPersonaDesaparecida() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "imagenPersonaDesa_sequence")
	@Column(name= "id", nullable= false)
	private Long id;
	
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
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
