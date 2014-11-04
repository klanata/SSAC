package com.core.service.negocio;

import java.util.Date;
import javax.ejb.Remote;

import com.core.data.entites.Usuario;

@Remote
public interface ServiciosSeguridad {

	public Boolean existeUsuario(String login, String password);
	
	public Boolean ingesarUsuraio(String login, String password, String email, String nombre, 
			Date fechaNac);
	
		
	public Usuario buscarUsuario(String id) throws Exception;
	
}
