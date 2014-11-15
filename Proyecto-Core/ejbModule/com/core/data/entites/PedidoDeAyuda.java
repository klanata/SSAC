package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoDeAyuda
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="PedidoDeAyuda.BuscarPedidoPorId", 
			query = "SELECT e "+
					"FROM PedidoDeAyuda e " +
					"WHERE e.id = :idPedido"),
					
@NamedQuery(name="PedidoDeAyuda.findAll", 
				query = "SELECT c "+
				"FROM PedidoDeAyuda c ")
})
public class PedidoDeAyuda  extends AbstractEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public PedidoDeAyuda() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Long id;
	
	@Column(nullable= false)
	private String descripcion = "";
	
	@Column( nullable= false)
	private BigDecimal coordenadasX ;
	
	@Column(nullable= false)
	private BigDecimal coordenadasY ;
	
	@Column(nullable= false)
	private Date fechaPublicacion ;
	
	@OneToMany
	private Collection<Catastrofe> catastrofeId = new ArrayList<Catastrofe>(0) ;

	public PedidoDeAyuda(Catastrofe catastrofeId, String descripcion, BigDecimal coordenadasX, BigDecimal coordenadasY,
			Date fechaPublicacion) {
		super();
		
		this.catastrofeId = new ArrayList<Catastrofe>(0);
		this.descripcion = descripcion;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.fechaPublicacion = fechaPublicacion;
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

	public BigDecimal getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(BigDecimal coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public BigDecimal getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(BigDecimal coordenadasY) {
		this.coordenadasY = coordenadasY;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Collection<Catastrofe> getCatastrofeId() {
		return catastrofeId;
	}

	public void setCatastrofeId(Collection<Catastrofe> catastrofeId) {
		this.catastrofeId = catastrofeId;
	}
	
    
}
