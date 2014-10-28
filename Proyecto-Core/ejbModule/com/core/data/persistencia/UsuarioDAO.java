package com.core.data.persistencia;
import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.Usuario;



@Local
public interface UsuarioDAO {

	public Usuario insert(Usuario entity);
	
	public void update(Usuario entity);
	
	public void delete(Usuario entity);
	
	public Usuario findById(Integer id);
	
	public List<Usuario> findAll();
	
	public List<Usuario> buscarUsuario(String login, String password);
	
}
