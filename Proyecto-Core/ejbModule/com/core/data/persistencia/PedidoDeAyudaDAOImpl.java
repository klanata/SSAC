package com.core.data.persistencia;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	public Long crearPedidoDeAyuda(PedidoDeAyuda pedAyuda) throws Exception{
	
		try {
			dataService.create(pedAyuda);
			Long id = pedAyuda.getId();
			return id;
			} 	
		catch (Exception excep){			
			throw excep;
		}	
    }
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean existePedido(Long id) {	
			boolean existe;
			Query consulta = this.em.createNamedQuery("PedidoDeAyuda.BuscarPedidoid.id");
			consulta.setParameter("id", id);														
		  	if (consulta.getResultList().isEmpty()){
		  		existe = false;
		  	} else {
		  		existe = true;
		  	}
		  	return existe;
	}
	///////////////////////////////////
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public PedidoDeAyuda BuscarPedidoPorId (int idPedido){
		PedidoDeAyuda pedidoAyuda = dataService.find(PedidoDeAyuda.class, idPedido);
		return pedidoAyuda;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<PedidoDeAyuda> findAllPedidoAyuda(){
		try {
			TypedQuery<PedidoDeAyuda> consulta = this.em.createNamedQuery("PedidoDeAyuda.findAll",PedidoDeAyuda.class);								
			List<PedidoDeAyuda> pedido = consulta.getResultList();			
		  	return pedido;		
		} catch (Exception excep){			
			throw excep;
		}	  	
	}
}