package com.core.service.negocio.remote;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import com.core.data.entites.Administrador;

@Remote
public interface AdministradorEBR {
	
	public boolean existeUsuario(String nick, String password);
	
	public Long crearAdministrador(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, Integer celular) throws Exception ;
	
	public Collection<Administrador> listarTodosLosAdministradores();
	
	public void modificarAdministrador(String nick,String nombre, String apellido,
			String email, String password, Date fechaNac, String sexo, Integer celular);
	
	public void eliminarAdministrador(String nick);

}
