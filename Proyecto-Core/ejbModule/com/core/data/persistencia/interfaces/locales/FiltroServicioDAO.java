package com.core.data.persistencia.interfaces.locales;

import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.FiltroServicio;
import com.core.data.persistencia.JPAService;


@Local
public interface FiltroServicioDAO extends JPAService{
	
	public Long insert(FiltroServicio filtroServicio)  throws Exception;	
	
	public FiltroServicio buscarFiltroServicioPorId(Long id) throws Exception;

	public List<FiltroServicio> listarFiltroServicios() throws Exception;
	
	public List<FiltroServicio> listarFiltrosServiciosPorIdServicio(Long idServicio) throws Exception;
	

}
