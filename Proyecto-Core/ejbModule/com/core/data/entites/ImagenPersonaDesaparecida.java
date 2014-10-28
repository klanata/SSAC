package com.core.data.entites;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenPersonaDesaparecida
 *
 */
@Entity

public class ImagenPersonaDesaparecida extends Imagen {

	
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
   
}
