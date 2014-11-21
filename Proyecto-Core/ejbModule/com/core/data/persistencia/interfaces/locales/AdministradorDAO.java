package com.core.data.persistencia.interfaces.locales;

import javax.ejb.Local;

import com.core.data.entites.Administrador;
import com.core.data.persistencia.JPAService;

@Local
public interface  AdministradorDAO extends JPAService {
	
	public Integer crearAdministrador(Administrador admin) throws Exception;
	
	public Boolean buscarAdministradorNickPass(String nick, String password);

}
