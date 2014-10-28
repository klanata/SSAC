package com.core.data.entites;


import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DeBienes
 *
 */
@Entity

public class DeBienes extends Donacion {

	
	private static final long serialVersionUID = 1L;

	public DeBienes() {
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
	private String nombreItem = "";
	
	@Column(nullable= false)
	private Integer cantidad;
	
}
