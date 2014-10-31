package com.core.data.persistencia.interfaces.locales;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.Usuario;
import com.core.data.persistencia.AbstractService;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.UsuarioDAO;




@Stateless


@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDAOImpl extends AbstractService implements UsuarioDAO{

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
	private DataService dataService;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario insert(Usuario entity) {
		
		try {
			if (entity != null)
			{			
				//dataService.create(entity);
				//em.create(entity);
				em.persist(entity);
			
			}
			
			return entity;
			
		} catch (Throwable e) {
			
		}
		
		return entity;
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Usuario entity) {
		try{
				
			
		}catch (Exception e){
			
		}
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Usuario entity) {
		// TODO Auto-generated method stub
		
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Usuario BuscarById(Integer id) {

		
		///Usuario usuario = n

	  	System.out.println("el usuario esta bavioooooooooooo    ");

	  	
		Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.ID");
//		String idString= id.toString();
	  	consulta.setParameter("id", id);
	  	Usuario usuario = (Usuario) consulta.getResultList().get(0);
	  	
	  	//Usuario usuario = find(Usuario.class, id);
	  	
		return usuario;
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario buscarUsuario(String login, String password) {
		
		Usuario usuario = null;
		Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.Nick.Pass");
	  	consulta.setParameter("nick", login);
	  	consulta.setParameter("pass", password);
	   	usuario = (Usuario) consulta.getResultList().get(0);
	  		
		return usuario;
	}


}
