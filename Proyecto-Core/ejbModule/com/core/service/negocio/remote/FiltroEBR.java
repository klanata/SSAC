package com.core.service.negocio.remote;

import java.util.List;

import javax.ejb.Remote;

import com.core.data.entites.Filtro;


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
	
	public void asignarFiltroServicio(Long idFiltro,String fuente) throws Exception;
		
	public List<Filtro> listaFiltrosNoAsignadosAYoutube() throws Exception;
	//Lista los filtros que no estan asignados a Youtube
	
	public List<Filtro> listarFiltrosQueTieneAlMenosUnServicio() throws Exception;
	//Lista los filtros que tienen asignado al menos un servicio
	
	public List<Filtro> listaFiltrosNoAsignadosAlServicio(String nombreServicio) throws Exception;
	//Lista los filtros que no estan asignados al servicio nombreServicio
	
	public void EliminarFiltro(Long idFiltro) throws Exception;
}
