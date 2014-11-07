package com.core.data.persistencia.interfaces.locales;
import java.util.List;

import javax.ejb.Local;
import com.core.data.entites.Usuario;



@Local
public interface UsuarioDAO {

	public Usuario insert(Usuario entity);
	
	public void update(Usuario entity);
	
	public void delete(Usuario entity);
	
	public Usuario BuscarById(Long id)throws Exception;
	
	public List<Usuario> findAll();
	
	public Usuario buscarUsuario(String login, String password);
	
	
}
