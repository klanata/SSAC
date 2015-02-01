package com.core.service.negocio.remote;

import java.util.List;

import javax.ejb.Remote;

import com.core.data.entites.FiltroServicio;
import com.core.data.entites.Filtro;
import com.core.data.entites.Servicio;

@Remote
public interface FiltroServicioEBR {

	public Long ingesarFiltroServicio(Filtro filtro,Servicio servicio)throws Exception;
	//Retorna 0 si no puede ingresar la pareja filtro y servicio, de lo contrario devuelve el id 	
	
	public FiltroServicio buscaFiltroServicioPorId(Long id) throws Exception;
	
	public List<FiltroServicio> listaFiltroServicios() throws Exception;
	
	public List<FiltroServicio> listaFiltroServiciosSinCatastrofe() throws Exception;
	
	public List<FiltroServicio> listaFiltroServicioAsignadosCatastrofe(long idCatastrofe) throws Exception;
		
	
}
