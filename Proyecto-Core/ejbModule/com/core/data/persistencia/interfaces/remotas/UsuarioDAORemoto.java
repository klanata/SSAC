package com.core.data.persistencia.interfaces.remotas;

import javax.ejb.Remote;

import com.core.data.entites.Usuario;

@Remote
public interface UsuarioDAORemoto {
	
	
	public Usuario BuscarById(Long id)throws Exception;

}
