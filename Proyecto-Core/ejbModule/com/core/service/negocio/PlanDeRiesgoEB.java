package com.core.service.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.PlanDeRiesgoDAO;
import com.core.service.negocio.remote.PlanDeRiesgoEBR;



@Path("/administrador")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//PlanDeRiesgoEB!com.core.service.negocio.remote.PlanDeRiesgoEBR")

public class PlanDeRiesgoEB implements PlanDeRiesgoEBR{

	@EJB
	private DataService dataService;
	
	
	@EJB
	private PlanDeRiesgoDAO planDeRiesgoDo;

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public PlanDeRiesgo obtenerPlanDeRiesgoPorID(Long id) {
		
		PlanDeRiesgo plan = planDeRiesgoDo.obtenerPlanDeRiesgoPorID(id);
		
		
		return plan;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Long crearPlanDeRiesgo(String rutaArchivo, Long idCatastrofe)
			throws Exception {
		
			Long id = new Long(0);
			PlanDeRiesgo plan = new PlanDeRiesgo();
			id = planDeRiesgoDo.crearPlanDeRiesgo(plan);
		return id;
	}

	//////////////////////////////////////////////////////////////////////////////////
	public void modificarPlanDeRiesgo(Long idCatastrofe, String nombreArchivo) {
		
		
		Catastrofe c = dataService.find(Catastrofe.class, idCatastrofe);
		PlanDeRiesgo planviejo = c.getPlanDeRiesgo();
		
		///PlanDeRiesgo planNuevo = planDeRiesgoDo.obtenerPlanDeRiesgo(planviejo.getRutaArchivo());
		planviejo.setRutaArchivo(nombreArchivo);
		dataService.update(planviejo);
		
		
		
	}
}
