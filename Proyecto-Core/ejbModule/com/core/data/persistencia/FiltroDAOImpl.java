
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

import com.core.data.persistencia.interfaces.locales.FiltroDAO;
import com.core.data.entites.Filtro;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FiltroDAOImpl extends AbstractService implements FiltroDAO{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager em;
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(Filtro filtro)throws Exception {	
		Long id;
		String descripcion = filtro.getDescripcion();
		try {
			if (this.existeFiltro(descripcion)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(filtro);					
				TypedQuery<Long> consulta = this.em.createNamedQuery("Filtro.BuscarFiltroId.Descripcion",Long.class);				
				consulta.setParameter("descripcion", descripcion);				
				List<Long> filtroId = consulta.getResultList();
				if (filtroId.isEmpty()) {
					id = (long) 0;	
			  	} else {
			  		id = filtroId.get(0);
			  	}					
			} 					
			return id;		    
		} 
		catch (Exception excep){			
			throw excep;
		}	
	}	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeFiltro(String descripcion) {	
			boolean existe;
			Query consulta = this.em.createNamedQuery("Filtro.BuscarFiltroId.Descripcion");
			consulta.setParameter("descripcion", descripcion);														
		  	if (consulta.getResultList().isEmpty()){
		  		existe = false;
		  	} else {
		  		existe = true;
		  	}
		  	return existe;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Filtro buscarFiltroPorDescripcion(String descripcion) throws Exception {
	  	
		Filtro filtro;
		try {
			Query consulta = this.em.createNamedQuery("Filtro.BuscarFiltroId.Descripcion",Filtro.class);			
		  	consulta.setParameter("descripcion", descripcion);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		filtro = new Filtro();
		  		filtro.setId(new Long(0));		  		
		  	} else {		  		
		  		filtro = (Filtro) consulta.getResultList().get(0);
		  	}		  	
		  	return filtro;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Filtro buscarFiltroPorId(Long id) throws Exception {
	  	
		Filtro filtro;
		try {
			Query consulta = this.em.createNamedQuery("Filtro.BuscarFiltro.Id",Filtro.class);			
		  	consulta.setParameter("id", id);		  			  	
		  	if (consulta.getResultList().isEmpty()){
		  		filtro = new Filtro();
		  		filtro.setId(new Long(0));		  		
		  	} else {		  		
		  		filtro = (Filtro) consulta.getResultList().get(0);
		  	}		  	
		  	return filtro;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Filtro> listarFiltros() throws Exception {
	  	
		try {
			TypedQuery<Filtro> consulta = this.em.createNamedQuery("Filtro.BuscarTodos",Filtro.class);
			boolean bajaLogica = false;
			consulta.setParameter("bajaLogica", bajaLogica);
			List<Filtro> filtros = consulta.getResultList();			
		  	return filtros;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Filtro> listarFiltrosYoutube() throws Exception {
	  	
		try {
			TypedQuery<Filtro> consulta = this.em.createNamedQuery("Filtro.BuscarYoutube",Filtro.class);
			boolean bajaLogica = false;
			String fuente = "Youtube";
			consulta.setParameter("bajaLogica", bajaLogica);
			consulta.setParameter("fuente", fuente);			
			List<Filtro> filtros = consulta.getResultList();			
		  	return filtros;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}


	@Override
	protected EntityManager getEntityManager() {	
		return null;
	}


	
	
	

}
