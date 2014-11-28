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
	////////////////////////////////////////////////////////////////////////////////
	public boolean existeUsuario(String login, String password) {
	
		boolean existe =false ;
		if(login.isEmpty() && password.isEmpty())
		{
			existe = false;
		}
		else
		{
			existe=  usuarioDAO.existeUsuario(login, password);
			
		}
		return existe;
	}
	////////////////////////////////////////////////////////////////////////////////
	public boolean ingesarUsuraio(String login, String password, String email,
			String nombre, Date fechaNac) {
		
		boolean ingreso = false;
		
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setFechaNac(fechaNac);
		u.setNick(login);
		u.setNombre(nombre);
		u.setPassword(password);
		
		try {
			Long id = usuarioDAO.insert(u);
			if(id != 0){ ingreso = true;}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return ingreso;
	}
	////////////////////////////////////////////////////////////////////////////////
	public Usuario buscarUsuario(String nick) throws Exception {
		
		Usuario u = null;
		if(nick.isEmpty())
		{
			u = null;
		}else{
			
			u = usuarioDAO.BuscarUsuarioNick(nick);
			
		}
		
		return u;
	}
	////////////////////////////////////////////////////////////////////////////////
	public void eliminarUsuario(String nick) {
		
		Usuario entidad = usuarioDAO.BuscarUsuarioNick(nick);
		if( entidad != null)
		{
			 
			dataService.delete(entidad);
			
		}
		
	}
	////////////////////////////////////////////////////////////////////////////////
	public void modificarUsuario(String nick,String password, String email, String nombre,
			Date fechaNac) {
		
		//obtengo el usuario
		if(!nick.isEmpty())
		{
			Usuario u = usuarioDAO.BuscarUsuarioNick(nick);
			if(!password.isEmpty()) { u.setPassword(password);}
			if(!email.isEmpty()){ u.setEmail(email);}
			if(!nombre.isEmpty()){u.setNombre(nombre);}
			if(fechaNac != null){ u.setFechaNac(fechaNac);}
			
			dataService.update(u);
			
		}	
		
	}
	
	
	

}