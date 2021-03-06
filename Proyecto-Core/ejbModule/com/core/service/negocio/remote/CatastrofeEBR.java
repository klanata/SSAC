package com.core.service.negocio.remote;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Filtro;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;


@Remote
public interface CatastrofeEBR {

	public Long ingresarCatastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX,double coordenadasY, Boolean activa, Boolean prioridad, String css,  Set<ImagenCatastrofe> imagenes, Set<Filtro> filtros, Set<Ong> ongs,PlanDeRiesgo planDeRiesgo,String poligono)throws Exception;
	//Retorna 0 si no puede ingresar la catastrofe, de lo contrario devuelve el id 
	
	public Catastrofe buscaCatastrofePorNombre(String nombreEvento) throws Exception;
	
	public Catastrofe buscaCatastrofePorId(Long id) throws Exception;
	
	public List<Catastrofe> listaCatastrofes() throws Exception;
		
	public void agregarOngALaCatastrofe(Long idCatastrofe, Long idOng) throws Exception;
	
	public void agregarImagenALaCatastrofe(Long idCatastrofe, String nombImagen) throws Exception;
	
	public Collection<Ong> listaOngDeCatastrofe(Long id);
	
	public void eliminarOngDeCatastrofe(Long idCatastrofe, Long idOng);
	
	public Collection<ImagenCatastrofe> listaImagenesDeCatastrofe(Long id) throws Exception;
	
	public void eliminarImgDeCatastrofe(Long idCatastrofe, Long idImg) throws Exception;
	
	public void agregarPlanDeRiesgoALaCatastrofe(Long idCatastrofe, String nombArchivo) throws Exception;
	
	public void eliminarPlanDeRiesgoCatastrofe(Long idCatastrofe, Long idPlan) throws Exception;	
	
	public void agregarCSSALaCatastrofe(Long idCatastrofe, String css) throws Exception;
	
	public void eliminarCSSDeLaCatastrofe(Long idCatastrofe) throws Exception;
	
	//public void asignarFiltroALaCatastrofe(Long idCatastrofe, Long idFiltro) throws Exception;
	
	//public List<Filtro> filtrosAsingadosACatastrofe(Long idCatastrofe, String fuente) throws Exception;
	//Devuelve los filtros por los que se hace la busqueda para la catastrofe con idCatastrofe
	
	//public List<String> BusquedaAsingadasACatastrofe(Long idCatastrofe, String fuente) throws Exception;
	//Devuelve en una lista de string los filtros por los que se hace la busqueda para la catastrofe con idCatastrofe
	
	public List<Double> ListarCoordenasCatastrofe(Long idCatastrofe) throws Exception;
	//Devuelve la lista con las coordenadas de la catastrofe con idCatastrofe
	
	public List<Ong> listaOngsNoAsignadosALaCatastrofe(Long idCatastrofe) throws Exception;
	//Devuelve la lista con las Ongs no asignadas de la catastrofe con idCatastrofe
	
	public void asignarFiltroServicioALaCatastrofe(Long idCatastrofe, Long idFiltroServicio) throws Exception;
	
	public void eliminarFiltroServicioDeCatastrofe(Long idFiltroServicio) throws Exception;
	
	public List<String> listarFiltrosDeCatastrofe(long idCatastrofe, String fuente) throws Exception;
	
	public void EliminarCatastrofe(Long idCatastofe) throws Exception;	
	
	public void bajaFiltroServicioDeCatastrofe(Long idFiltroServicio) throws Exception;
	
}
