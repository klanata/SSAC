
package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: PedidoDeAyuda
 *
 */ 
@Entity
@SequenceGenerator(name = "pedidoDeAyuda_sequence",
sequenceName = "pedidoDeAyuda_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	@NamedQuery(name="PedidoDeAyuda.BuscarPedidoid.id", 
			query = "SELECT e "+
					"FROM PedidoDeAyuda e " +
					"WHERE e.id = :id"),
					
@NamedQuery(name="PedidoDeAyuda.findAll", 
				query = "SELECT c "+
				"FROM PedidoDeAyuda c ")
})
@XmlRootElement
public class PedidoDeAyuda  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public PedidoDeAyuda() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "pedidoDeAyuda_sequence")
	@Column(name= "id", nullable= false)
	private Long id;
	
	@Column(nullable= false)
	private String descripcion = "";
	
	@Column( nullable= false)
	private Double coordenadasX ;
	
	@Column(nullable= false)
	private Double coordenadasY ;
	
	@Column(nullable= false)
	private Date fechaPublicacion ;
	
	@ManyToOne
	private Catastrofe catastrofe;
	
	private boolean bajaLogica;

	public boolean getBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}

	

	public PedidoDeAyuda(Catastrofe catastrofe, String descripcion, Double coordenadasX, Double coordenadasY,
			Date fechaPublicacion) {
		super();
		
		this.catastrofe = catastrofe;
		this.descripcion = descripcion;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.fechaPublicacion = fechaPublicacion;
		this.bajaLogica = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(Double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public Double getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(Double coordenadasY) {
		this.coordenadasY = coordenadasY;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Catastrofe getCatastrofe() {
		return catastrofe;
	}

	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	
    
}
