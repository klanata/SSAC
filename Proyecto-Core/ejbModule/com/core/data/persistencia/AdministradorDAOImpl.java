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

import com.core.data.entites.Administrador;
import com.core.data.persistencia.interfaces.locales.AdministradorDAO;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdministradorDAOImpl extends AbstractService implements AdministradorDAO  {

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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long crearAdministrador(Administrador admin) throws Exception {
		Long id;
		String nick = admin.getNick();
		try {
			if (this.existeAdministrador(nick)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(admin);					

				Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador");
				consulta.setParameter("nick", nick);							
				
				if (consulta.getResultList().isEmpty()){
					id = (long) 0;	
			  	} else {
			  		
			  		Administrador o = (Administrador) consulta.getResultList().get(0);
			  		id= o.getId();
			  	}					
			} 					
			return id;		    
		} 
		catch (Exception excep){			
			throw excep;
		}	

	
	
	
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Boolean buscarAdministradorNickPass(String nick,
			String password) {
		
		//Administrador usuario = null;
		System.out.print("password"+ password);
		Boolean existe;
		Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador.Nick.Pass");
	  	consulta.setParameter("nick", nick);
	  	consulta.setParameter("password", password);
	  	if (consulta.getResultList().isEmpty()){
	  		existe = false;
	  	} else {
	  		existe = true;
	  	}
	  	return existe;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeAdministrador(String nick) {
				
				boolean existe;
				Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador");
				consulta.setParameter("nick", nick);							
				if (consulta.getResultList().isEmpty()){
			  		existe = false;
			  	} else {
			  		existe = true;
			  	}
			  	return existe;
		}

	

}
