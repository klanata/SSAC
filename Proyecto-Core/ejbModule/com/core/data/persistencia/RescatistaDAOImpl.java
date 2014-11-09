package com.core.data.persistencia;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.Rescatista;
import com.core.data.persistencia.interfaces.locales.RescatistaDAO;


/*Autor: Stephy
 * */
@Stateless

@Local(RescatistaDAO.class)
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
	@Override
	public void crearRescatista(Rescatista rescatista) throws Exception 
	{
		try{
			
			dataService.create(rescatista);
			
		} catch (Exception excep){
			
				throw excep;
			
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void asignarCatastrofe(EstadoRescatista estadoRescatista,Long idRescatista) {
		try{
			
			//buscamos el rescatista
			Rescatista rescatista =dataService.find(Rescatista.class, idRescatista);
			//obtengo la lista de sus pendientes
			Collection<EstadoRescatista> listaEstadoRescatista = rescatista.getEstadoRescatista();
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
	public Collection<EstadoRescatista> listarPendientesRescatista(	Long idRescatista)
	{
		Collection<EstadoRescatista> listapendientes = null;
		try{
			
			//para crear Querys
			//coleccion de parametros clave/valor
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("idRescatista",idRescatista);
			listapendientes =dataService.findWithNamedQuery(EstadoRescatista.class,"EstadoRescatista.FindPendientes", parameters);
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
	
	
}