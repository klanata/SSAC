package com.core.service.negocio.remote;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface AdministradorEBR {
	
	public Boolean existeUsuario(String nick, String password);
	public Long crearAdministrador(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, Integer celular) throws Exception ;
	

}
