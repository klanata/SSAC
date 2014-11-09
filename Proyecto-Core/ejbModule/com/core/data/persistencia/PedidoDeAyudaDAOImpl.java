package com.core.data.persistencia;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.core.data.entites.PedidoDeAyuda;
import com.core.data.persistencia.interfaces.locales.PedidoDeAyudaDAO;

@Stateless

@Local(PedidoDeAyudaDAO.class)
public class PedidoDeAyudaDAOImpl extends AbstractService implements PedidoDeAyudaDAO {
	
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
	public void crearPedidoDeAyuda(PedidoDeAyuda pedAyuda) throws Exception{
		try{
			dataService.create(pedAyuda);
		} catch (Exception excep){
				throw excep;
			
		}
    }
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PedidoDeAyuda BuscarPedidoPorId (int idPedido){
		PedidoDeAyuda pedidoAyuda = dataService.find(PedidoDeAyuda.class, idPedido);
		return pedidoAyuda;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<PedidoDeAyuda> findAllPedidoAyuda(){
	Collection<PedidoDeAyuda> listPed = null;
		try{			
			listPed = dataService.findAll(PedidoDeAyuda.class);
		}catch (Exception e){}
		return listPed;
	}
}