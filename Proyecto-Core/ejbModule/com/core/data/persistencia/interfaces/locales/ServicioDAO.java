package com.core.data.persistencia.interfaces.locales;

import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.Servicio;
import com.core.data.persistencia.JPAService;


@Local
public interface ServicioDAO extends JPAService{
		
	public boolean existeServicio(String fuente);
	
	public Servicio buscarServicioPorFuente(String fuente) throws Exception;
	
	public Servicio buscarServicioPorId(Long id) throws Exception;

	public List<Servicio> listarServicios() throws Exception;
	
	

}
