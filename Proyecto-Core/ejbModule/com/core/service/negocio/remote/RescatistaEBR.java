package com.core.service.negocio.remote;
/*Autor : Stephy
 * */
import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;


@Remote
public interface RescatistaEBR {
	
	public Long crearRescatista(String nombre, String nick, String apellido, String email,String password,Date fechaNac,String sexo, BigDecimal celular) throws Exception;
	
	public void asignarRescatistaCatastrofe(Catastrofe catastrofe);

}
