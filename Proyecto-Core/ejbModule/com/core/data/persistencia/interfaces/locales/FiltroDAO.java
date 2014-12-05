package com.core.data.persistencia.interfaces.locales;

import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.Filtro;
import com.core.data.persistencia.JPAService;


@Local
public interface FiltroDAO extends JPAService{
	
	public Long insert(Filtro filtro)  throws Exception;
	
	public boolean existeFiltro(String descripcion);
	
	public Filtro buscarFiltroPorDescripcion(String descripcion) throws Exception;
	
	public Filtro buscarFiltroPorId(Long id) throws Exception;

	public List<Filtro> listarFiltros() throws Exception;
	
	public List<Filtro> listarFiltrosYoutube() throws Exception;
	
	

}
