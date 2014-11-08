package com.core.service.negocio;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Usuario;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.UsuarioDAO;
import com.core.service.negocio.remote.UsuarioEBR;

@Path("/personas") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//UsuarioEB!com.core.service.negocio.remote.UsuarioEBR")
public class UsuarioEB implements UsuarioEBR{

	@EJB
	private UsuarioDAO usuarioDAO;
	@EJB
	private DataService dataService;
	
	
	public Boolean existeUsuario(String login, String password) {

		try {
			
			Usuario usuario = this.usuarioDAO.buscarUsuario(login, password);
			return (usuario != null);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public Boolean ingesarUsuraio(String login, String password, String email, String nombre, 
									Date fechaNac) {
		
			Usuario u = new Usuario();
			
			u.setNick(login);
			u.setPassword(password);
			u.setEmail(email);
			u.setFechaNac(fechaNac);
			u.setNombre(nombre);
			
		
			usuarioDAO.insert(u);
			
		
		return true;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	public Usuario buscarUsuario(String id) throws Exception 
	{
		Long id2 = Long.parseLong(id);
		Usuario u = new Usuario();
		u = this.usuarioDAO.BuscarById(id2);
		return u;
		
	}

}