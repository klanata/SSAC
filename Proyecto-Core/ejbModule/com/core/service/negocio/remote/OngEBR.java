package com.core.service.negocio.remote;

import java.math.BigDecimal;

import javax.ejb.Remote;



@Remote
public interface OngEBR {

	public Long ingesarOng(String nombre, String direccion,String descripcion, String email,BigDecimal telefono, String citioWeb)throws Exception;

	
	
	
}
