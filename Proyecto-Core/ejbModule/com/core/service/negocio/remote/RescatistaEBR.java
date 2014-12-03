package com.core.service.negocio.remote;
/*Autor : Stephy
 * */
import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.EstadoRescatista;
import com.core.data.entites.Rescatista;

import cross_cuting.enums.PlanesPendientesRescatistaDTO;


@Remote
public interface RescatistaEBR {
	
	public Long crearRescatista(String nombre, String nick, String apellido, String email,String password,Date fechaNac,String sexo, String celular) throws Exception;
	
	public void asignarRescatistaCatastrofe(Catastrofe catastrofe);
	
	public Boolean buscarUsuario(String nick, String password);
	
	public Collection<PlanesPendientesRescatistaDTO> listarPendientesRescatistaPorCatastrofe(String nick);
	
	public void RealizadoPendiente(EstadoRescatista estadorescatista);
	
	public void modificarRescatista (String nombre, String nick, String apellido, String email,String password,Date fechaNac,String sexo, String celular);
	
	public Boolean eliminarRescatista(String nick);
	
	public Rescatista obtenerRescatistaNik(String nick);
	
	public Collection<Rescatista> listarTodosLosRescatistasActivos();

}
