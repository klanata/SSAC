package com.core.data.persistencia.interfaces.remotas;

import javax.ejb.Remote;

import com.core.data.entites.Usuario;
import com.core.data.persistencia.JPAService;

@Remote
public interface UsuarioDAORemoto extends JPAService {
	
	
	public Usuario BuscarById(Long id)throws Exception;

}
