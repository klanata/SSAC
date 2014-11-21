package com.core.service.negocio.remote;
/*Autor : Stephy
 * */
import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Rescatista;

@Remote
public interface RescatistaEBR {
	
	public void crearRescatista(Rescatista rescatista);
	
	public void asignarRescatistaCatastrofe(Catastrofe catastrofe);
	
	public Rescatista buscarUsuario(String login, String password);

}
