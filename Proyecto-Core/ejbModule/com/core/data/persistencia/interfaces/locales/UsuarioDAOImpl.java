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
import com.core.data.persistencia.UsuarioDAO;




@Stateless

@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDAOImpl implements UsuarioDAO{

	@PersistenceContext
	protected EntityManager em;

	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario insert(Usuario entity) {
		
		try {
			if (entity != null)
			{			
				
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario buscarUsuario(String login, String password) {
		
		Usuario usuario = null;
		Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.Nick.Pass");
	  	consulta.setParameter("nick", login);
	  	consulta.setParameter("pass", password);
	   	usuario = (Usuario) consulta.getResultList().get(0);
	  		
		return usuario;
	}

}
