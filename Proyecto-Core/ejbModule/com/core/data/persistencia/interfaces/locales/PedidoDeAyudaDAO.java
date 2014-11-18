package com.core.data.persistencia.interfaces.locales;
import java.util.List;
import javax.ejb.Local;
import com.core.data.entites.PedidoDeAyuda;
import com.core.data.persistencia.JPAService;

@Local
public interface PedidoDeAyudaDAO extends JPAService {

	public void crearPedidoDeAyuda(PedidoDeAyuda ent) throws Exception ;
		
	public PedidoDeAyuda BuscarPedidoPorId (int id);
	 
	public List<PedidoDeAyuda> findAllPedidoAyuda();
}
