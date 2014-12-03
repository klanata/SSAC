package com.core.service.negocio.remote;


import java.util.List;
import javax.ejb.Remote;
import com.core.data.entites.Ong;



@Remote
public interface OngEBR {

	public Long ingesarOng(String nombre, String direccion,String descripcion, String email,String telefono, String citioWeb)throws Exception;
	
	public List<Ong> listarTodasLasOng();
	
	public boolean existeOng_EB(String nombre);
	
	public Ong buscarOngPorID_EB(Long id);
		
	public Ong buscarOngPorNick_EB(String nombreOng);
	
	public void modificarOng(String nombre, String direccion,String descripcion, String email,String telefono, String citioWeb);
	
	public void EliminarONG(Long id);
	
	
}
