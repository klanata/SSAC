package com.core.data.persistencia;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Usuario;
import com.core.data.persistencia.interfaces.locales.UsuarioDAO;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDAOImpl  extends AbstractService implements UsuarioDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@PersistenceContext
	protected EntityManager em;
	
	protected EntityManager getEntityManager() {
		
		return em;
	}
	
	//Esto para poder acceder a la funciones echas en las clases abtractas
	@EJB
	private DataService dataService;
	//////////////////////////////////////////////////////////////////////////////////////
	public Long insert(Usuario entity) throws Exception {
		Long id;
		String nick = entity.getNick();
		try {
			if (this.existeUsuarioNick(nick)){				
				id = (long) 0;	
				
			} 
			else 
			{	
				
				dataService.create(entity);
				
				id = entity.getId();
		  	}					
					    
		} 
		catch (Exception excep){			
			throw excep;
		}	
		return id;
	}
	/////////////////////////////////////////////////////////////////////////////
	public Usuario BuscarUsuarioById(Long id) throws Exception {
	
		Usuario u = null;
		try{
			 u= dataService.find(Usuario.class, id);
		}catch (Exception excep){			
			throw excep;
		}	
		return u;
	}
	/////////////////////////////////////////////////////////////////////////////
	public Collection<Usuario> findAllUsuarios() {
		
		Collection<Usuario> lista = null;
		try{
			lista = dataService.findAll(Usuario.class);
			
		}catch (Exception excep){			
			throw excep;
		}		
		return lista;
	}
	/////////////////////////////////////////////////////////////////////////////
	public boolean existeUsuario(String login, String password) {
			Boolean existe = false;
			try{
				Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.Nick.Pass");
			  	consulta.setParameter("nick", login);
			  	consulta.setParameter("password", password);
			  	if (!consulta.getResultList().isEmpty()){
			  		existe = true;
			  	} 
			}catch (Exception excep){			
				throw excep;
			} 	
		  	return existe;	
			
	}
	/////////////////////////////////////////////////////////////////////////////
	public boolean existeUsuarioNick(String nick) {
		Boolean existe = false;
		try{
			Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona");
		  	consulta.setParameter("nick", nick);
		  	if (!consulta.getResultList().isEmpty()){
		  		existe = true;
		  	} 
		}catch (Exception excep){			
			throw excep;
		} 	
	  	return existe;	
	}
	/////////////////////////////////////////////////////////////////////////////
	public Usuario BuscarUsuarioNick(String nick) {
		Usuario existe = null;
		try{
			Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona");
		  	consulta.setParameter("nick", nick);
		  	if (!consulta.getResultList().isEmpty()){
		  		existe = (Usuario) consulta.getResultList().get(0);
		  	} 
		}catch (Exception excep){			
			throw excep;
		} 	
	  	return existe;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean usuarioRegistrador(String nick, String pass, long idCatastrofe) {
		
		//me dice si esta registrado a la catastrofe lo dejo ingresar sino lo mando a que se registre
		boolean estaRegistrado = false;
		
		Usuario usuario = null;
		try{
			Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona.Nick.Pass");
		  	consulta.setParameter("nick", nick);
		  	consulta.setParameter("password", pass);
		  	
		  	if (!consulta.getResultList().isEmpty()){
		  		usuario = (Usuario) consulta.getResultList().get(0);
		  	} 
		  	//obtengo la coleccion de catastrofe
		  	Collection<Catastrofe>lista = usuario.getCatastrofesRegistradas();
		  	Catastrofe objCatastrofe = dataService.find(Catastrofe.class, idCatastrofe);
		  	//si esta registrado a esa catastrofe me dara true
		  	estaRegistrado = lista.contains(objCatastrofe);
		  	
		  	
		}catch (Exception excep){			
			throw excep;
		} 	
	  	return estaRegistrado;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	
}
