package com.core.data.persistencia;


import java.util.Collection;
import java.util.Date;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.core.data.entites.Administrador;
import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
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
	///////////////////////////////////////////////////////////////////////////
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
	public boolean buscarAdministradorNickPass(String nick,
			String password) {
	
		Boolean existe = false;
		try{
			Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador.Nick.Pass");
		  	consulta.setParameter("nick", nick);
		  	consulta.setParameter("password", password);
		  	if (!consulta.getResultList().isEmpty()){
		  		existe = true;
		  	} 
		}catch (Exception excep){			
			throw excep;
		} 	
	  	return existe;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existeAdministrador(String nick) {
				
				boolean existe = false;
		try{			
				Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador");
				consulta.setParameter("nick", nick);							
				if (consulta.getResultList().isEmpty()){
			  		existe = false;
			  	} else {
			  		existe = true;
			  	}
		}catch (Exception excep){			
			throw excep;
		} 
		return existe;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Administrador obtenerAdministrador(String nick) {
		
		Administrador a = null;
		try{
			Query consulta = this.em.createNamedQuery("Administrador.BuscarAdministrador");
			consulta.setParameter("nick", nick);							
			if (!consulta.getResultList().isEmpty()){
		  		a = (Administrador)consulta.getResultList().get(0);
			}
		}catch (Exception excep){			
			throw excep;
		} 
		return a;
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Collection<Administrador> listarAdministradores() {
		
		Collection<Administrador> lista = null;
		try{
			lista = dataService.findAll(Administrador.class);
			
		}catch (Exception excep){			
			throw excep;
		} 
		
		return lista;
	}
	@Override
	public Collection<DeBienes> listaDeBienes(Date fechaInicio, Date fechaFinal) {
		
		
		Collection<DeBienes> lista = null;					
		
		TypedQuery<DeBienes> consulta = this.em.createNamedQuery("DeBienes.ReporteDonacion",DeBienes.class);
		//r(entry.getKey(), (Date) entry.getValue(), TemporalType.TIMESTAMP);
		consulta.setParameter("fechaInicio",(Date) fechaInicio,TemporalType.TIMESTAMP );
		System.out.print("paso de cargar fecha inicio");
		consulta.setParameter("fechaFinal", (Date)fechaInicio,TemporalType.TIMESTAMP );
		System.out.print("paso de cargar fecha inicio");
		try{
			lista = consulta.getResultList();
		}catch (Exception e)
		{
			System.out.print("se muere al cargar la lista con las fechas");
			
		}	
		
		return lista;
	}
	@Override
	public Collection<DeServicios> listaDeServicios(Date fechaInicio,
			Date fechaFinal) {
		
		Collection<DeServicios> lista = null;					
		//  query.setParameter(entry.getKey(), (Date) entry.getValue(), TemporalType.TIMESTAMP);
		TypedQuery<DeServicios> consulta = this.em.createNamedQuery("DeServicios.ReporteDeServicios",DeServicios.class);				
		consulta.setParameter("fechaInicio", fechaInicio,TemporalType.TIMESTAMP );
		consulta.setParameter("fechaFinal", fechaInicio,TemporalType.TIMESTAMP );
		lista = consulta.getResultList();
		
		return lista;
	}
	@Override
	public Collection<Economica> listaEconomica(Date fechaInicio,
			Date fechaFinal) {

		Collection<Economica> lista = null;					
		
		TypedQuery<Economica> consulta = this.em.createNamedQuery("Economica.ReporteEconomica",Economica.class);				
		consulta.setParameter("fechaInicio", fechaInicio );
		consulta.setParameter("fechaFinal", fechaInicio );
		lista = consulta.getResultList();
		
		return lista;
	}
	

}
