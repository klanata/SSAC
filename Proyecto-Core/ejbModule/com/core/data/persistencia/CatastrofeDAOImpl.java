
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

import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.data.entites.Catastrofe;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CatastrofeDAOImpl extends AbstractService implements CatastrofeDAO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(Catastrofe catastrofe)throws Exception {	
		Long id;
		String nombreEvento = catastrofe.getNombreEvento();
		try {
			if (this.existeCatastrofe(nombreEvento)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(catastrofe);					
				TypedQuery<Long> consulta = this.em.createNamedQuery("Catastrofe.BuscarCatastrofeId.NombreEvento",Long.class);				
				consulta.setParameter("nombre", nombreEvento);				
				List<Long> catastrofesId = consulta.getResultList();
				if (catastrofesId.isEmpty()) {
					id = (long) 0;	
			  	} else {
			  		id = catastrofesId.get(0);
			  	}					
			} 					
			return id;		    
		} 
		catch (Exception excep){			
			throw excep;
		}	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeCatastrofe(String nombreEvento) {	
			boolean existe;
			Query consulta = this.em.createNamedQuery("Catastrofe.BuscarCatastrofeId.NombreEvento");
			consulta.setParameter("nombre", nombreEvento);														
		  	if (consulta.getResultList().isEmpty()){
		  		existe = false;
		  	} else {
		  		existe = true;
		  	}
		  	return existe;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Catastrofe buscarCatastrofePorNombre(String nombreEvento) throws Exception {
	  	
		Catastrofe catastrofe;
		try {
			Query consulta = this.em.createNamedQuery("Catastrofe.BuscarCatastrofeId.NombreEvento",Catastrofe.class);			
		  	consulta.setParameter("nombre", nombreEvento);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		catastrofe = new Catastrofe();
		  		catastrofe.setId(new Long(0));		  		
		  	} else {		  		
		  		catastrofe = (Catastrofe) consulta.getResultList().get(0);
		  	}		  	
		  	return catastrofe;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Catastrofe buscarCatastrofePorId(Long id) throws Exception {
	  	
		Catastrofe catastrofe;
		try {
			Query consulta = this.em.createNamedQuery("Catastrofe.BuscarCatastrofe.Id",Catastrofe.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		catastrofe = new Catastrofe();
		  		catastrofe.setId(new Long(0));		  		
		  	} else {		  		
		  		catastrofe = (Catastrofe) consulta.getResultList().get(0);
		  	}		  	
		  	return catastrofe;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Catastrofe> listarCatastrofes() throws Exception {
	  	
		try {
			TypedQuery<Catastrofe> consulta = this.em.createNamedQuery("Catastrofe.BuscarTodas",Catastrofe.class);
			boolean bajaLogica = false;
			consulta.setParameter("bajaLogica", bajaLogica);
			List<Catastrofe> catastrofes = consulta.getResultList();			
		  	return catastrofes;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}


	@Override
	protected EntityManager getEntityManager() {
		
		return null;
	}
	

}
