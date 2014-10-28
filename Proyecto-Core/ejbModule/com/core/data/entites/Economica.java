package com.core.data.entites;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Economica
 *
 */
@Entity

public class Economica extends Donacion {

	
	private static final long serialVersionUID = 1L;

	public Economica() {
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
	

	@Column()
	private BigDecimal monto;
   
}
