package com.core.data.persistencia;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.Ong;

import com.core.data.persistencia.interfaces.locales.OngDAO;

/*Autor: Stephy
 * */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OngDAOImpl extends AbstractService   implements OngDAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	@EJB
	DataService dataService;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(Ong ong)throws Exception {	
		Long id;
		String nombre = ong.getNombre();
		try {
			if (this.existeOng(nombre)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(ong);					

				Query consulta = this.em.createNamedQuery("Ong.BuscarOngNombre");
				consulta.setParameter("nombre", nombre);							
				
				if (consulta.getResultList().isEmpty()){
					id = (long) 0;	
			  	} else {
			  		
			  		Ong o = (Ong) consulta.getResultList().get(0);
			  		id= o.getId();
			  	}					
			} 					
			return id;		    
		} 
		catch (Exception excep){			
			throw excep;
		}	
	}
	////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeOng(String nombre) {
				
				boolean existe;
				Query consulta = this.em.createNamedQuery("Ong.BuscarOngNombre");
				consulta.setParameter("nombre", nombre);														
			  	if (consulta.getResultList().isEmpty()){
			  		existe = false;
			  	} else {
			  		existe = true;
			  	}
			  	return existe;
		}
	////////////////////////////////////////////////////////////////
	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
	

	

}
