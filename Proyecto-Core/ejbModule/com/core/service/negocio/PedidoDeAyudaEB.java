package com.core.service.negocio;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.PedidoDeAyuda;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.PedidoDeAyudaDAO;
import com.core.service.negocio.remote.PedidoDeAyudaEBR;

@Path("/personas") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//PedidoDeAyudaEB!com.core.service.negocio.remote.PedidoDeAyudaEBR")

public class PedidoDeAyudaEB implements PedidoDeAyudaEBR{
	@EJB
	private DataService dataService;
	
	@EJB 
	private PedidoDeAyudaDAO pedidoayudaDAO;
	
	public void crearPedido(PedidoDeAyuda pedAyuda){
		try {
			pedidoayudaDAO.crearPedidoDeAyuda(pedAyuda);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public Collection<PedidoDeAyuda> listarTodosLosPedidos(){
		//Obtener la lista de todos los pedidos de ayuda.
		Collection<PedidoDeAyuda> pedido= pedidoayudaDAO.findAllPedidoAyuda();
		return pedido;
		
		
	}

}
