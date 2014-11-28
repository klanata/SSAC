package com.core.service.negocio.remote;

import java.util.Date;
import javax.ejb.Remote;

import com.core.data.entites.Usuario;

@Remote
public interface UsuarioEBR {

	public boolean existeUsuario(String login, String password);
	public boolean ingesarUsuraio(String login, String password, String email, String nombre,Date fechaNac);
	public Usuario buscarUsuario(String nick) throws Exception;
	public void eliminarUsuario(String nick);
	public void modificarUsuario(String nick,String password, String email, String nombre,Date fechaNac);
	
}
