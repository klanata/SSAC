package com.core.data.persistencia;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.AbstractEntity;
import com.core.data.entites.Usuario;
import com.core.data.persistencia.interfaces.locales.UsuarioDAO;
import com.core.data.persistencia.interfaces.remotas.UsuarioDAORemoto;




@Stateless

@Local(UsuarioDAO.class)
@Remote(UsuarioDAORemoto.class)
public class UsuarioDAOImpl  extends AbstractService implements UsuarioDAO, UsuarioDAORemoto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@PersistenceContext
	protected EntityManager em;
	//Esto para poder acceder a la funciones echas en las clases abtractas
	@EJB
	private DataService dataService;
	
	@Override
	
	public Usuario insert(Usuario entity) {
		
		try {
								
				dataService.create(entity);
							
			
		} catch (Throwable e) {
			
		}
		
		return entity;
		
	}
	////////////////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Usuario entity) {
		try{
				
			
		}catch (Exception e){
			
		}
		
	}
	////////////////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Usuario entity) {
		
		
	}

	////////////////////////////////////////////////////////////////////////////
	@Override
	public Usuario BuscarById(Long id)throws Exception{
		/*Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.ID");
	  	consulta.setParameter("id", id);
	  	Usuario usuario = (Usuario) consulta.getResultList().get(0);*/
		//Esto funciona cada vez que quieran obetener un objeto lo pueden usar asi
		
		Usuario usuario = find(Usuario.class, id);
		return usuario;
		
	}
	////////////////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Usuario> findAll() {
		
		
		return null;
	}

	////////////////////////////////////////////////////////////////////////////
	public Usuario buscarUsuario(String login, String password) {
		
		Usuario usuario = null;
		Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.Nick.Pass");
	  	consulta.setParameter("nick", login);
	  	consulta.setParameter("pass", password);
	   	usuario = (Usuario) consulta.getResultList().get(0);
	  		
		return usuario;
	}
	////////////////////////////////////////////////////////////////////////////
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	@Override
	public <T extends AbstractEntity> List<T> createAll(List<T> col)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AbstractEntity> List<T> updateAll(List<T> entidad) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AbstractEntity, PK> void deleteAll(Class<T> clazz,
			List<PK> ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <T extends AbstractEntity> T getRandomElement(Class<T> tipoEntidad) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T extends AbstractEntity> T getRandomElementWithParameters(
			Class<T> tipoEntidad, Map<String, Object> parametros) {
		// TODO Auto-generated method stub
		return null;
	}

}
