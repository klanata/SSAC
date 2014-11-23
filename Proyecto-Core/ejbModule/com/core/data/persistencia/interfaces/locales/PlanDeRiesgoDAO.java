package com.core.data.persistencia.interfaces.locales;

import javax.ejb.Local;

import com.core.data.entites.PlanDeRiesgo;
import com.core.data.persistencia.JPAService;

@Local
public interface PlanDeRiesgoDAO extends JPAService {
	
	
	public PlanDeRiesgo obtenerPlanDeRiesgo (Integer id);
	

}
