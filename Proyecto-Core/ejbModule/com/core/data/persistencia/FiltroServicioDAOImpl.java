
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

import com.core.data.persistencia.interfaces.locales.FiltroServicioDAO;
import com.core.data.entites.FiltroServicio;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FiltroServicioDAOImpl extends AbstractService implements FiltroServicioDAO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
		
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(FiltroServicio filtroServicio)throws Exception {	
		Long id;		
		try {
			this.em.persist(filtroServicio);
			id = filtroServicio.getId();
			return id;							  
		} 
		catch (Exception excep){			
			throw excep;
		}	
	}
	
		
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public FiltroServicio buscarFiltroServicioPorId(Long id) throws Exception {
	  	
		FiltroServicio filtroServicio;
		try {
			Query consulta = this.em.createNamedQuery("FiltroServicio.BuscarFiltroServicio.Id",FiltroServicio.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		filtroServicio = new FiltroServicio();
		  		filtroServicio.setId(new Long(0));		  		
		  	} else {		  		
		  		filtroServicio = (FiltroServicio) consulta.getResultList().get(0);
		  	}		  	
		  	return filtroServicio;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<FiltroServicio> listarFiltroServicios() throws Exception {
	  	
		try {
			TypedQuery<FiltroServicio> consulta = this.em.createNamedQuery("FiltroServicio.BuscarTodos",FiltroServicio.class);
			boolean bajaLogica = false;
			consulta.setParameter("bajaLogica", bajaLogica);
			List<FiltroServicio> filtroServicios = consulta.getResultList();			
		  	return filtroServicios;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<FiltroServicio> listarFiltrosServiciosPorIdServicio(Long idServicio) throws Exception{
	  	
		try {
			TypedQuery<FiltroServicio> consulta = this.em.createNamedQuery("FiltrosServicio.BuscarPorIdServicio",FiltroServicio.class);
			boolean bajaLogica = false;
			//fuente = "Youtube";
			consulta.setParameter("bajaLogica", bajaLogica);
			consulta.setParameter("idServicio", idServicio);			
			List<FiltroServicio> filtroServicios = consulta.getResultList();			
		  	return filtroServicios;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}


	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
