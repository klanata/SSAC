package com.core.data.persistencia;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.core.data.entites.Administrador;
import com.core.data.persistencia.interfaces.locales.AdministradorDAO;

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
	
	@Override
	public Integer crearAdministrador(Administrador admin) throws Exception {
		Integer id =0;
		try{
			
			dataService.create(admin);
			Administrador admin2 = buscarAdministradorNickPass(admin.getNick(), admin.getPassword());
			if  (admin2!=null)
			{
				
				id = admin2.getId().intValue();
				
			}
			
		} catch (Exception excep){
			
				throw excep;
			
		}
		return id;
	
	}

	@Override
	public Administrador buscarAdministradorNickPass(String nick,
			String password) {
		
		Administrador usuario = null;
		Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador.Nick.Pass");
	  	consulta.setParameter("nick", password);
	  	consulta.setParameter("password", password);
	  	
	  	if(consulta!= null){
	  		
	  		usuario = (Administrador) consulta.getResultList().get(0);
	  	}	
	  		
		return usuario;
	}
	
	

}