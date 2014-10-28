package com.core.data.entites;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DeServicios
 *
 */
@Entity

public class DeServicios extends Donacion {

	
	private static final long serialVersionUID = 1L;

	public DeServicios() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Integer id;
	
	
	@Column()
	private String usuario = "";
	

	@Column(nullable =false)
	private Date fechaRealizada;
	
	@Column(nullable= false)
	private String areaConocimient = "";
	
	@Column(nullable= false)
	private BigDecimal cantidadHoras ;
   
}
