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
	
	public List<FiltroServicio> listaAllFiltroServicios() throws Exception;
	//Lista la tabla FiltroServicio registros que no estan dados de baja
	//tambien tiene en cuenta que la Catastrofe no este dada de baja
	
	public List<FiltroServicio> listaFiltroServiciosCatastrofesNoDadasDeBaja() throws Exception;
	//Lista la tabla FiltroServicio registros que no estan dados de baja y
	//tambien tiene en cuenta que la Catastrofe no este dada de baja
	
	public List<FiltroServicio> listaFiltroServiciosSinCatastrofe() throws Exception;
	
	public List<FiltroServicio> listaFiltroServicioAsignadosCatastrofe(long idCatastrofe) throws Exception;
	
	public void EliminarFiltroServicio(Long idFiltroServicio) throws Exception;
		
	
}
