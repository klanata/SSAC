package com.core.service.negocio.remote;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

import com.core.data.entites.Ong;



@Remote
public interface OngEBR {

	public Long ingesarOng(String nombre, String direccion,String descripcion, String email,BigDecimal telefono, String citioWeb)throws Exception;
	public List<Ong> listarTodasLasOng();
	
	
	
}
