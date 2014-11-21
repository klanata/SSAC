package com.core.service.negocio.remote;

import javax.ejb.Remote;

@Remote
public interface AdministradorEBR {
	
	public Boolean existeUsuario(String nick, String password);

}
