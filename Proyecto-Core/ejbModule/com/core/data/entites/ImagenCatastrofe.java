package com.core.data.entites;



import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenCatastrofe
 *
 */
@Entity

public class ImagenCatastrofe extends Imagen implements Serializable{

	
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

	@ManyToOne
	private Catastrofe catastrofe;

	public ImagenCatastrofe(String path, Catastrofe catastrofe) {
		super();
		this.path = path;
		this.catastrofe = catastrofe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Catastrofe getCatastrofe() {
		return catastrofe;
	}

	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	
	
}
