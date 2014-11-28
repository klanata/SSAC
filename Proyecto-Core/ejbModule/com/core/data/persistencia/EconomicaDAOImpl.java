package com.core.data.persistencia;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.core.data.entites.Economica;
import com.core.data.persistencia.interfaces.locales.EconomicaDAO;
@Stateless
public class EconomicaDAOImpl  extends AbstractService implements EconomicaDAO{

	
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
	
	///////////////////////////////////////////////////////////////////////////
	public Economica buscarDonacionEconomica(Long id) {
		Economica donacion= null;
		try {
			 donacion= dataService.find(Economica.class, id);
			
								
		} catch (Exception excep){			
			throw excep;
		}	
		return donacion;
	}

	///////////////////////////////////////////////////////////////////////////
	public Collection<Economica> listarDonacionesEconomica(Long idOng) {
		Collection<Economica> lista = null;
		try {
											
			TypedQuery<Economica> consulta= this.em.createNamedQuery("Economica.BuscarDonacion.DeOng",Economica.class);
			consulta.setParameter("idOng", idOng);
			lista = consulta.getResultList();			
		  	
								
		} catch (Exception excep){			
			throw excep;
		}	
		return lista;
	}

	////////////////////////////////////////////////////////////////////////////
	public Long agregarDonacionEconomicaOng(Economica economica)
			throws Exception {
		Long id = new Long(0);
		
		
		try {
				dataService.create(economica);
				id = economica.getId();
									
			} catch (Exception excep){			
				throw excep;
			}					
			return id;
	}

		

}
