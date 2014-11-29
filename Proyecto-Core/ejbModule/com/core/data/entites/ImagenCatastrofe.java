package com.core.data.entites;



import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: ImagenCatastrofe
 *
 */
@Entity
@XmlRootElement
public class ImagenCatastrofe extends Imagen implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public ImagenCatastrofe() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false)
	private Long id;
	
	@Column()
	private String path = "";

	@ManyToOne
	private Catastrofe catastrofe;

	public ImagenCatastrofe(String path, Catastrofe catastrofe) {
		super();
		this.path = path;
		this.catastrofe = catastrofe;
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

	public Catastrofe getCatastrofe() {
		return catastrofe;
	}

	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	
	
}
