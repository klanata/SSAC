package com.core.service.negocio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;


import java.util.Date;
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
	
	/*Esto esta mal esta capa se conecta con rest ellos nos mandaran los datos no obtejo
	 * public void crearPedido(PedidoDeAyuda pedAyuda){
		try {
			pedidoayudaDAO.crearPedidoDeAyuda(pedAyuda);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}*/
	
	//Stephy: Vale deberia ser asi falta setearle aca el id de la catastrofe
	public void crearPedido(Long catastrofeId, String descripcion, BigDecimal coordenadasX, BigDecimal coordenadasY,
			Date fechaPublicacion) throws Exception{
		
		PedidoDeAyuda pedAyuda = new PedidoDeAyuda();
		pedAyuda.setCatastrofeId(catastrofeId);
		pedAyuda.setCoordenadasX(coordenadasX);
		pedAyuda.setCoordenadasY(coordenadasY);
		pedAyuda.setDescripcion(descripcion);
		pedAyuda.setFechaPublicacion(fechaPublicacion);
		
		try {
			
		
			pedidoayudaDAO.crearPedidoDeAyuda(pedAyuda);
		} catch (Exception e) {
			throw e;
		}
			
			//e.printStackTrace();
		}
	@Override
	public List<PedidoDeAyuda> listarTodosLosPedidos(){
		//Obtener la lista de todos los pedidos de ayuda.
		List<PedidoDeAyuda> listaPedidos = new ArrayList<PedidoDeAyuda>();
		listaPedidos = pedidoayudaDAO.findAllPedidoAyuda();
		return listaPedidos;

 }
	

}
