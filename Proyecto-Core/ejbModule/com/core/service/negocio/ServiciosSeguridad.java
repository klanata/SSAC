package com.core.service.negocio;

import java.util.Date;
import javax.ejb.Local;

@Local
public interface ServiciosSeguridad {

	public Boolean existeUsuario(String login, String password);
	
	public Boolean ingesarUsuraio(String login, String password, String email, String nombre, 
			Date fechaNac);
	
	public int funciona(int numero);
	
}
