package com.core.service.negocio.remote;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.core.data.entites.Filtro;
import com.core.data.entites.Servicio;


@Remote
public interface FiltroEBR {

	public Long ingesarFiltro(String descripcion)throws Exception;
	//Retorna 0 si no puede ingresar el filtro, de lo contrario devuelve el id 
	
	public Filtro buscaFiltroPorDescripcion(String descripcion) throws Exception;
	
	public Filtro buscaFiltroPorId(Long id) throws Exception;
	
	public List<Filtro> listaFiltros() throws Exception;	
	//Lista todos los filtros, los de Youtube y Rss
	
	public List<Filtro> listaFiltrosYoutube() throws Exception;
	//Lista los filtros de Youtube
	
	public void asignarServicioFiltro(String descripcion,String fuente) throws Exception;
	
}
