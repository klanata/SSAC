package com.core.service.negocio.remote;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.core.data.entites.PedidoDeAyuda;

@Remote
public interface PedidoDeAyudaEBR {
	
	
	public void crearPedido(Long catastrofeId, String descripcion, BigDecimal coordenadasX, BigDecimal coordenadasY,
			Date fechaPublicacion) throws Exception;
	public List<PedidoDeAyuda> listarTodosLosPedidos();
	
}
