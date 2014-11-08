package com.core.service.negocio.remote;
import java.util.Collection;

import javax.ejb.Remote;
import com.core.data.entites.PedidoDeAyuda;;

public interface PedidoDeAyudaEBR {
	
	public void crearPedido(PedidoDeAyuda pedAyuda);
	public Collection<PedidoDeAyuda> listarTodosLosPedidos();
	
}
