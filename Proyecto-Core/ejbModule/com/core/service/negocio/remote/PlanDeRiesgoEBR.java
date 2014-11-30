package com.core.service.negocio.remote;

import javax.ejb.Local;

import com.core.data.entites.PlanDeRiesgo;

@Local
public interface PlanDeRiesgoEBR {
	
	
	
	public PlanDeRiesgo obtenerPlanDeRiesgoPorID (Long id);
	public Long crearPlanDeRiesgo(String rutaArchivo, Long idCatastrofe) throws Exception;
	
	public void modificarPlanDeRiesgo(Long idCatastrofe, String nombreArchivo);

}
