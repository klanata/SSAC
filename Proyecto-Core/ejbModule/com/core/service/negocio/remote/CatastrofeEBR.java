package com.core.service.negocio.remote;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Servicio;


@Remote
public interface CatastrofeEBR {

	public Long ingesarCatastrofe(String nombreEvento, String descripcion, String logo, double coordenadasX,double coordenadasY, Boolean activa, Boolean prioridad, Set<Servicio> servicios, Set<Ong> ongs,PlanDeRiesgo planDeRiesgo)throws Exception;
	//Retorna 0 si no puede ingresar la catastrofe, de lo contrario devuelve el id 
	
	public Catastrofe buscaCatastrofePorNombre(String nombreEvento) throws Exception;
	
	public Catastrofe buscaCatastrofePorId(Long id) throws Exception;
	
	public List<Catastrofe> listaCatastrofes() throws Exception;
		
	public void agregarOngALaCatastrofe(Long idCatastrofe, Long idOng) throws Exception;
	
}
