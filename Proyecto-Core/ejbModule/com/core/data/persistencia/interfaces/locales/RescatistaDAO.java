package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import javax.ejb.Local;

import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.Rescatista;
import com.core.data.persistencia.JPAService;

/*Autor: Stephy
 * */
@Local
public interface RescatistaDAO extends JPAService{
	
	//Lo crea el administrador con lista de pendientes vacia
	public void crearRescatista(Rescatista rescatista) throws Exception;
	
	public void asignarCatastrofe(EstadoRescatista estadoRescatista, Long idRescatista);
	
	public Collection<EstadoRescatista> listarPendientesRescatista(Long idRescatista);
	
	public void pendienteRealizado(EstadoRescatista estadorescatista);
	

}
