package com.core.service.negocio.remote;
/*Autor : Stephy
 * */
import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.EstadoRescatista;


@Remote
public interface RescatistaEBR {
	
	public Long crearRescatista(String nombre, String nick, String apellido, String email,String password,Date fechaNac,String sexo, Integer celular) throws Exception;
	
	public void asignarRescatistaCatastrofe(Catastrofe catastrofe);
	
	public Boolean buscarUsuario(String nick, String password);
	
	public Collection<EstadoRescatista> listarPendientesRescatistaPorCatastrofe(String nick, Long idCatastrofe);

}
