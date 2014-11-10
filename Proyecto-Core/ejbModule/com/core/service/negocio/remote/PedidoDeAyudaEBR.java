package com.core.service.negocio.remote;
import java.util.Collection;


import java.util.List;

import javax.ejb.Remote;

import com.core.data.entites.PedidoDeAyuda;

@Remote
public interface PedidoDeAyudaEBR {
	
	public void crearPedido(PedidoDeAyuda pedAyuda);
	public List<PedidoDeAyuda> listarTodosLosPedidos();
	
}
