package com.core.data.persistencia;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import com.core.data.entites.DeServicios;
import com.core.data.persistencia.interfaces.locales.DeServicioDAO;

@Stateless
public class DeServicioDAOImpl extends AbstractService implements DeServicioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
	@EJB
	private DataService dataService;
	/////////////////////////////////////////////////////////////////////////////

	public DeServicios buscarDonacionDeServicioID(Long id) {
		DeServicios donacion= null;
		try {
			 donacion= dataService.find(DeServicios.class, id);
			
								
		} catch (Exception excep){			
			throw excep;
		}	
		return donacion;
	}

	/////////////////////////////////////////////////////////////////////////////
	public Collection<DeServicios> listarDonacionesDeServicio(Long idOng) {
	
		Collection<DeServicios> lista = null;
		try {
			TypedQuery<DeServicios> consulta = this.em.createNamedQuery("DeServicios.BuscarDonacion.DeOng",DeServicios.class);								
			consulta.setParameter("idOng", idOng);
			lista = consulta.getResultList();			
		  	
								
		} catch (Exception excep){			
			throw excep;
		}	
		return lista;
	}

	/////////////////////////////////////////////////////////////////////////////
	public Long agregarDonacionDeServicioOng(DeServicios deServicios)
			throws Exception {
		
		Long id = new Long(0);
		
		
		try {
				dataService.create(deServicios);
				id = deServicios.getId();
									
			} catch (Exception excep){			
				throw excep;
			}					
			return id;
	}

	
	

}
