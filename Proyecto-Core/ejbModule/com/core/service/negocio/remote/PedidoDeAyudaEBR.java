package com.core.service.negocio.remote;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import com.core.data.entites.PedidoDeAyuda;

@Remote
public interface PedidoDeAyudaEBR {
	
	
	public Long crearPedido(Long idCatastrofe, String descripcion, Double coordenadasX, Double coordenadasY,
			Date fechaPublicacion) throws Exception;
	public List<PedidoDeAyuda> listarTodosLosPedidos();
	
}
