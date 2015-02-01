package com.core.service.negocio.remote;

import java.util.List;

import javax.ejb.Remote;

import com.core.data.entites.Servicio;


@Remote
public interface ServicioEBR {
	
	public Long ingresarServicio(String nombreServicio)throws Exception;
	
	public List<Servicio> listaServicios() throws Exception;	
	//Lista todos los servicios, Youtube, Rss, etc
	
	public boolean ExisteServicio(String nombreServicio)throws Exception;
	
	public Servicio buscarServicioPorFuente(String nombreServicio) throws Exception;
	
	public Servicio buscarServicioPorId(Long idServicio) throws Exception;
	
}
