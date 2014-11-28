package com.core.data.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Servicio
 *
 */
@Entity
@Table (name = "Servicio")
@XmlRootElement
public class Servicio  extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Servicio() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column(name= "url",  nullable= false)
	private String url = "";
	
	@Column(name= "fuente",  nullable= false)
	private String fuente = "";
	
	//private Collection<String> filtros = new ArrayList<String>(0);
	
	@ManyToMany(mappedBy="servicios")
	private Set<Catastrofe> catastrofes = new  HashSet<Catastrofe>(0);

	

	public Set<Catastrofe> getCatastrofes() {
		return catastrofes;
	}

	public void setCatastrofes(Set<Catastrofe> catastrofes) {
		this.catastrofes = catastrofes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	
	
   
}
