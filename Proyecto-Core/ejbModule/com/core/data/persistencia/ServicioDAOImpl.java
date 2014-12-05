
package com.core.data.persistencia;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import com.core.data.persistencia.interfaces.locales.ServicioDAO;
import com.core.data.entites.Servicio;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServicioDAOImpl extends AbstractService implements ServicioDAO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeServicio(String fuente) {	
			boolean existe;
			Query consulta = this.em.createNamedQuery("Servicio.BuscarServicioId.Fuente");
			consulta.setParameter("fuente", fuente);														
		  	if (consulta.getResultList().isEmpty()){
		  		existe = false;
		  	} else {
		  		existe = true;
		  	}
		  	return existe;
	}
		
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Servicio buscarServicioPorFuente(String fuente) throws Exception {
		
		Servicio servicio = new Servicio();
		try {
			System.out.println("probando agregar servicio de nombre: " + fuente );
			Query consulta = this.em.createNamedQuery("Servicio.BuscarServicio.Fuente",Servicio.class);						
		  	consulta.setParameter("fuente", fuente);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		servicio = new Servicio();		  		
		  		servicio.setId(new Long(0));		  		
		  	} else {		  		
		  		servicio = (Servicio) consulta.getResultList().get(0);
		  	}		  	
		  	return servicio;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Servicio buscarServicioPorId(Long id) throws Exception {
	  	
		Servicio servicio;
		try {
			Query consulta = this.em.createNamedQuery("Servicio.BuscarServicio.Id",Servicio.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		servicio = new Servicio();
		  		servicio.setId(new Long(0));		  		
		  	} else {		  		
		  		servicio = (Servicio) consulta.getResultList().get(0);
		  	}		  	
		  	return servicio;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Servicio> listarServicios() throws Exception {
	  	
		try {
			TypedQuery<Servicio> consulta = this.em.createNamedQuery("Servicio.BuscarTodos",Servicio.class);			
			List<Servicio> servicios = consulta.getResultList();			
		  	return servicios;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}	


	@Override
	protected EntityManager getEntityManager() {	
		return null;
	}


	
	
	

}
