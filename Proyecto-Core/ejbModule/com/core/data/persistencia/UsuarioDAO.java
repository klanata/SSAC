package com.core.data.persistencia;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import com.core.data.entites.Usuario;



@Local
public interface UsuarioDAO extends JPAService {

	public Usuario insert(Usuario entity);
	
	public void update(Usuario entity);
	
	public void delete(Usuario entity);
	
	public Usuario BuscarById(Integer id);
	
	public List<Usuario> findAll();
	
	public Usuario buscarUsuario(String login, String password);
	
	
}
