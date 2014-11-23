package com.core.data.persistencia;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Rescatista;
import com.core.data.persistencia.interfaces.locales.PlanDeRiesgoDAO;



/*Autor: Stephy
 * */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PlanDeRiesgoDAOImpl extends AbstractService   implements  PlanDeRiesgoDAO{
	
	
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
	@Override
	public PlanDeRiesgo obtenerPlanDeRiesgo(Integer id) {
		
		PlanDeRiesgo p = null;
		Query consulta = this.em.createNamedQuery("PlanDeRiesgo.BuscarPlanDeRiesgo");
		consulta.setParameter("id", id);
	  	p = (PlanDeRiesgo) consulta.getResultList().get(0);
		
		return p;
	}
	@Override
	
	protected EntityManager getEntityManager() {
	
		return em;
	}
  	

}
