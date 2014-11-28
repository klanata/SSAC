package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import javax.ejb.Local;

import com.core.data.entites.Administrador;
import com.core.data.persistencia.JPAService;

@Local
public interface  AdministradorDAO extends JPAService {
	
	public Long crearAdministrador(Administrador admin) throws Exception;
	
	public boolean buscarAdministradorNickPass(String nick, String password);
	
	public boolean existeAdministrador(String nick);
	
	public Administrador obtenerAdministrador(String nick);
	
	public Collection<Administrador> listarAdministradores();

}
