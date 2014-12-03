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
	
	public Long insert(Rescatista rescatista)throws Exception;
	
	public void asignarCatastrofe(EstadoRescatista estadoRescatista, Long idRescatista);
	
	public Collection<EstadoRescatista> listarPendientesRescatistaPorID(Long idRescatista);
	
	public void pendienteRealizado(EstadoRescatista estadorescatista);
	
	public Rescatista obtenerRescatistaConMenosPendientes();
	public Rescatista buscarUsuario(String login, String password);
	public Rescatista buscarUsuarioNick(String nick);
	
	public Collection<EstadoRescatista> listarPendientesRescatista(String nick);
	

}
