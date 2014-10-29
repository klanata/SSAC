package com.core.data.entites;



import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenCatastrofe
 *
 */
@Entity

public class ImagenCatastrofe extends Imagen {

	
	private static final long serialVersionUID = 1L;

	public ImagenCatastrofe() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column()
	private String path = "";
	
}
