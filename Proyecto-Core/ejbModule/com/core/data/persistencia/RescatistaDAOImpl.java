package com.core.data.persistencia;

import java.util.*;

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

import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.Ong;
import com.core.data.entites.Rescatista;
import com.core.data.persistencia.interfaces.locales.RescatistaDAO;


/*Autor: Stephy
 * */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)

public class RescatistaDAOImpl extends AbstractService   implements RescatistaDAO{

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
	
	
	/////////////////////////////////////////////////////////////////////////////////////

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Long insert(Rescatista rescatista)throws Exception {	
		Long id;
		String nick = rescatista.getNick();
		try {
			if (this.existeRescatista(nick)){				
				id = (long) 0;				
			} 
			else 
			{				
				this.em.persist(rescatista);					

				Query consulta = this.em.createNamedQuery("Rescatista.BuscarRescatista");
				consulta.setParameter("nick", nick);							
				
				if (consulta.getResultList().isEmpty()){
					id = (long) 0;	
			  	} else {
			  		
			  		Rescatista o = (Rescatista) consulta.getResultList().get(0);
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
	public boolean existeRescatista(String nick) {
				
				boolean existe;
				Query consulta = this.em.createNamedQuery("Rescatista.BuscarRescatista");
				consulta.setParameter("nick", nick);							
				if (consulta.getResultList().isEmpty()){
			  		existe = false;
			  	} else {
			  		existe = true;
			  	}
			  	return existe;
		}

	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void asignarCatastrofe(EstadoRescatista estadoRescatista,Long idRescatista) {
		try{
			
			//buscamos el rescatista
			Rescatista rescatista =dataService.find(Rescatista.class, idRescatista);
			//obtengo la lista de sus pendientes
			Set<EstadoRescatista> listaEstadoRescatista = rescatista.getEstadoRescatista();
			listaEstadoRescatista.add(estadoRescatista);
			//actualizo la lista
			rescatista.setEstadoRescatista(listaEstadoRescatista);
			
			dataService.update(rescatista);
			
			
		} catch (Exception excep){
			throw excep;
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Collection<EstadoRescatista> listarPendientesRescatistaPorID(Long idRescatista)
	{
		Collection<EstadoRescatista> listapendientes = null;
		try{
			
			//para crear Querys
			//coleccion de parametros clave/valor
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("idRescatista",idRescatista);
			listapendientes = dataService.findWithNamedQuery(EstadoRescatista.class,"EstadoRescatista.FindPendientes", parameters);
			//limpiar parametros
			parameters.clear();
			
		}catch (Exception e){}
		
		
		return listapendientes;
	}
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void pendienteRealizado(EstadoRescatista estadorescatista) {
		try{
			
			EstadoRescatista estadoRescatistaActualizar = dataService.find(EstadoRescatista.class,estadorescatista.getId());
			//Como esta realizado ya no esta pendiente
			estadoRescatistaActualizar.setPendiente(false);
			
			dataService.update(estadoRescatistaActualizar);
			 
			
			
		} catch (Exception excep){
			throw excep;
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Rescatista obtenerRescatistaConMenosPendientes() {
		
		Rescatista rescatistaMenosPendientes = null;
		try{
			
			//obtengo todos los rescatistas en el sistema
			
			Collection<Rescatista> listarRescatistas = findAll(Rescatista.class);
			//recorremos la lista y vamos viendo quien tiene menos pendientes
			Integer pendiente = 0;
			
			while (listarRescatistas.isEmpty() != true)
			{
				Rescatista rescatista = listarRescatistas.iterator().next();
				//veo que cantidad de pendientes tiene
				Collection<EstadoRescatista> listaPendientes = rescatista.getEstadoRescatista();
				Integer i = listaPendientes.size();
				
				//rescatista.getEstadoRescatista().
				if ( (pendiente == 0) || (pendiente < i )  )
				{
					//actualizo el rescatista con menos pendientes
					rescatistaMenosPendientes = rescatista;
					
					pendiente = i;
				}
			}
			
		}catch (Exception excep){throw excep;}
		
		return rescatistaMenosPendientes;
	}
	///////////////////////////////////////////////////////////////////////////////////////
	public Rescatista buscarUsuario(String login, String password) {
		
		Rescatista usuario = null;
		Query consulta = this.em.createNamedQuery("Rescatista.BuscarRescatista.Nick.Pass");
	  	consulta.setParameter("nick", login);
	  	consulta.setParameter("password", password);
	   	usuario = (Rescatista) consulta.getResultList().get(0);
	  		
		return usuario;
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	public Collection<EstadoRescatista> listarPendientesRescatista(String nick)
	{
		Collection<EstadoRescatista> listapendientes =new  ArrayList<EstadoRescatista>(0);
		//Collection<EstadoRescatista> lista = null;
		try{
			
			
			Query consulta = this.em.createNamedQuery("Rescatista.BuscarRescatista");
			consulta.setParameter("nick", nick);							
			Rescatista r = (Rescatista) consulta.getResultList().get(0);
			Long idRescatista = r.getId();
			//esto te da la lista de pendientes del rescatista
			listapendientes = listarPendientesRescatistaPorID(idRescatista);
				
			
			
		}catch (Exception e){}
		
		
		return listapendientes;
	}
	@Override
	public Rescatista buscarUsuarioNick(String nick) {
		Rescatista usuario = null;
		Query consulta = this.em.createNamedQuery("Rescatista.BuscarRescatista");
	  	consulta.setParameter("nick", nick);
	  	usuario = (Rescatista) consulta.getResultList().get(0);
	  		
		return usuario;
	}
	@Override
	public Collection<Rescatista> listarRescatistas() {
		
		 Collection<Rescatista> lista = null;
		 TypedQuery<Rescatista> consulta = this.em.createNamedQuery("Rescatista.ListarRescatistaBajaLogicaFalse",Rescatista.class);
		 lista = consulta.getResultList(); 
		
		
		return lista;
	}
}
