package com.core.service.negocio;
import java.util.ArrayList;
import java.util.Collection;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.PedidoDeAyuda;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.PedidoDeAyudaDAO;
import com.core.service.negocio.remote.PedidoDeAyudaEBR;

@Path("/pedidoAyuda") 
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
	@Override
	public List<PedidoDeAyuda> listarTodosLosPedidos(){
		//Obtener la lista de todos los pedidos de ayuda.
		List<PedidoDeAyuda> listaPedidos = new ArrayList<PedidoDeAyuda>();
		listaPedidos = pedidoayudaDAO.findAllPedidoAyuda();
		return listaPedidos;

 }

}
