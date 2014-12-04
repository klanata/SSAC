package com.core.service.negocio.remote;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Servicio;


@Remote
public interface CatastrofeEBR {

	public Long ingesarCatastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX,double coordenadasY, Boolean activa, Boolean prioridad, String css,  Set<ImagenCatastrofe> imagenes, Set<Servicio> servicios, Set<Ong> ongs,PlanDeRiesgo planDeRiesgo)throws Exception;
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
	
}
