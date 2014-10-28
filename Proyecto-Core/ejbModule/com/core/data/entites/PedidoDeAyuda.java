package com.core.data.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoDeAyuda
 *
 */
@Entity

public class PedidoDeAyuda implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public PedidoDeAyuda() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	@Column(nullable= false)
	private String descripcion = "";
	
	@Column(nullable= false)
	private Boolean procesando ;
	

	@Column(nullable= false)
	private Boolean valido ;
	
	@Column( nullable= false)
	private BigDecimal coordenadasX ;
	
	@Column(nullable= false)
	private BigDecimal coordenadasY ;
	
	@Column(nullable= false)
	private Date fechaPublicacion ;
	
   
}
