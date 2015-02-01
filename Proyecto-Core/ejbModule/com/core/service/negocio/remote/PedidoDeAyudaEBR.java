package com.core.service.negocio.remote;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import com.core.data.entites.PedidoDeAyuda;

@Remote
public interface PedidoDeAyudaEBR {
	
	
	public Long crearPedido(Long idCatastrofe, String descripcion, Double coordenadasX, Double coordenadasY,
			Date fechaPublicacion) throws Exception;
	public List<PedidoDeAyuda> listarTodosLosPedidos();
	
	public PedidoDeAyuda buscarPedido(long id);
	
}
