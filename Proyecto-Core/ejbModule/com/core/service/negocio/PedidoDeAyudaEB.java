package com.core.service.negocio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
	

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long crearPedido(Long idCatastrofe, String descripcion, BigDecimal coordenadasX, BigDecimal coordenadasY,
			Date fechaPublicacion) throws Exception{
		
		PedidoDeAyuda pedAyuda = new PedidoDeAyuda();
		Long ID;
		Catastrofe catastrofe = dataService.find(Catastrofe.class, idCatastrofe);
		pedAyuda.setCatastrofe(catastrofe);
		pedAyuda.setCoordenadasX(coordenadasX);
		pedAyuda.setCoordenadasY(coordenadasY);
		pedAyuda.setDescripcion(descripcion);
		pedAyuda.setFechaPublicacion(fechaPublicacion);
		
		ID = pedidoayudaDAO.crearPedidoDeAyuda(pedAyuda);
		return ID;
		}
	@Override
	public List<PedidoDeAyuda> listarTodosLosPedidos(){
		//Obtener la lista de todos los pedidos de ayuda.
		List<PedidoDeAyuda> listaPedidos = new ArrayList<PedidoDeAyuda>();
		listaPedidos = pedidoayudaDAO.findAllPedidoAyuda();
		return listaPedidos;

 }
	

}
