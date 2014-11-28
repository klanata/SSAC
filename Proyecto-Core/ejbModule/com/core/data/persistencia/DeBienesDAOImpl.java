package com.core.data.persistencia;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.core.data.entites.DeBienes;
import com.core.data.persistencia.interfaces.locales.DeBienesDAO;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DeBienesDAOImpl  extends AbstractService implements DeBienesDAO{

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

	////////////////////////////////////////////////////////////////////////////
	public DeBienes buscarDonacionBinesID(Long id) {
		
		DeBienes donacion= null;
		try {
			 donacion= dataService.find(DeBienes.class, id);
			
								
		} catch (Exception excep){			
			throw excep;
		}	
		return donacion;
	}
	////////////////////////////////////////////////////////////////////////////
	public Collection<DeBienes> listarDonacionesDeBienes(Long idOng) {
		
		Collection<DeBienes> lista = null;
		try {
			TypedQuery<DeBienes> consulta = this.em.createNamedQuery("DeBienes.BuscarDonacion.DeOng",DeBienes.class);								
			consulta.setParameter("idOng", idOng);
			lista = consulta.getResultList();			
		  	
								
		} catch (Exception excep){			
			throw excep;
		}	
		return lista;
	}
	////////////////////////////////////////////////////////////////////////////
	public Long agregarDonacionDeBienesOng(DeBienes deBienes) throws Exception {
		Long id = new Long(0);
		
		
		try {
				dataService.create(deBienes);
				id = deBienes.getId();
									
			} catch (Exception excep){			
				throw excep;
			}					
			return id;		    
		
	}
	
}
