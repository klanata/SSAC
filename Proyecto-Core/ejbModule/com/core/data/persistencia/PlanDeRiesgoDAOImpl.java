package com.core.data.persistencia;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.persistencia.interfaces.locales.PlanDeRiesgoDAO;



/*Autor: Stephy
 * */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PlanDeRiesgoDAOImpl extends AbstractService   implements  PlanDeRiesgoDAO{
	
	
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
	protected EntityManager getEntityManager() {
	
		return em;
	}

	@EJB
	DataService dataService;

	/////////////////////////////////////////////////////////////////////
	public PlanDeRiesgo obtenerPlanDeRiesgoPorID(Long id) {
		
		PlanDeRiesgo p = null;
		
		try{
			
			p = dataService.find(PlanDeRiesgo.class, id);
		}catch (Exception excep){			
			throw excep;
		}
		return p;
	}


	/////////////////////////////////////////////////////////////////////
	public Long crearPlanDeRiesgo(PlanDeRiesgo planRiesgo) throws Exception {
		Long id = new Long(0);
		try{
			//cada catastrofe va a tener un unico plan de riesgo
			Catastrofe c = planRiesgo.getCatastrofe();
			//si la catastrofe no tiene asignado plan de riesgo lo puedo crear sino tiro error.
			if(c.getPlanDeRiesgo() == null)
			{
				dataService.create(planRiesgo);
				id =  planRiesgo.getId();
			}
			
		}catch (Exception excep){			
			throw excep;
		}
		return id;
	}


	@Override
	public PlanDeRiesgo obtenerPlanDeRiesgo(String rutaArchivo) {
		
		PlanDeRiesgo p = null;
		try{
			Query consulta = this.em.createNamedQuery("PlanDeRiesgo.BuscarPlanDeRiesgoArchivo");
			consulta.setParameter("rutaArchivo", rutaArchivo);							
			if (!consulta.getResultList().isEmpty()){
		  		p = (PlanDeRiesgo)consulta.getResultList().get(0);
			}
		}catch (Exception excep){			
			throw excep;
		} 
		return p;
	}


	


}
