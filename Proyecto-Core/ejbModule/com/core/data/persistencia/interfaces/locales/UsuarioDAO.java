package com.core.data.persistencia.interfaces.locales;
import java.util.Collection;

import javax.ejb.Local;

import com.core.data.entites.Usuario;
import com.core.data.persistencia.JPAService;



@Local
public interface UsuarioDAO extends JPAService{

	public Long insert(Usuario entity) throws Exception;
	
	public Usuario BuscarUsuarioById(Long id)throws Exception;
	public Usuario BuscarUsuarioNick(String nick);
	
	public Collection<Usuario> findAllUsuarios();
	
	public boolean existeUsuario(String login, String password);
	
	public boolean existeUsuarioNick(String nick);
	
	/*------------------------*/
	
	public boolean usuarioRegistrador(String nick, String pass, long idCatastrofe);
	
	
}
