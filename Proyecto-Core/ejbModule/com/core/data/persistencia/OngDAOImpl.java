package com.core.data.persistencia;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}
	
	@EJB
	DataService dataService;
	////////////////////////////////////////////////////////////////////
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
				
		boolean existe= false;
			try{
				
				Query consulta = this.em.createNamedQuery("Ong.BuscarOngNombre");
				consulta.setParameter("nombre", nombre);														
			  	if (consulta.getResultList().isEmpty()){
			  		existe = false;
			  	} else {
			  		existe = true;
			  	}
			} catch (Exception excep){			
				throw excep;
			}
			  	return existe;
		}
	////////////////////////////////////////////////////////////////
	public Ong buscarOngPorID(Long id) {
		
		Ong o = null;
		try{
			 o= dataService.find(Ong.class, id);
		} catch (Exception excep){			
			throw excep;
		}
		
		return o;
	}
	////////////////////////////////////////////////////////////////
	@Override
	public  List<Ong> listarONGS() {
	 
		 List<Ong> lista = null;
		try{
				//lista= dataService.findAll(Ong.class);
			
			TypedQuery<Ong> consulta = this.em.createNamedQuery("Ong.ListarONGBajaLogicaFalse",Ong.class);
											
			
				
			lista = consulta.getResultList(); 
			
		}catch (Exception excep){			
			
			System.out.print("muere en obtener la coleccion");
		}
		
		return lista;
	}
	////////////////////////////////////////////////////////////////
	public Ong buscarOngPorNick(String nombreOng) {
	
		Ong o = null;
		try{
			Query consulta = this.em.createNamedQuery("Ong.BuscarOngNombre");
			consulta.setParameter("nombre", nombreOng);							
			
			if (!consulta.getResultList().isEmpty()){
				 o = (Ong) consulta.getResultList().get(0);	
		  	}
		   	
		 }catch (Exception excep){			
				throw excep;
			}
		
		return o;
	}
	
	
	

	

}
