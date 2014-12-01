package com.core.service.negocio;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Administrador;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.AdministradorDAO;
import com.core.service.negocio.remote.AdministradorEBR;


@Path("/administrador")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR")

public class AdministradorEB implements AdministradorEBR{


	@EJB
	private DataService dataService;
	
	
	
	@EJB
	private AdministradorDAO administradorDao;
	///////////////////////////////////////////////////////////////////////////////////
	@Override
	public Long crearAdministrador(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, String celular) throws Exception {
			
		Administrador r = new Administrador();
		r.setNombre(nombre);
		r.setApellido(apellido);
		r.setCelular(celular);
		r.setEmail(email);
		r.setFechaNac(fechaNac);
		r.setPassword(password);
		r.setSexo(sexo);
		r.setNick(nick);
		r.setBajaLogica(false);
		Long id = administradorDao.crearAdministrador(r);
		return id;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	public boolean existeUsuario(String nick, String password) {
		
		boolean existe = false;
	    existe= administradorDao.buscarAdministradorNickPass(nick, password);
		return existe;
	}

	///////////////////////////////////////////////////////////////////////////////////
	public Collection<Administrador> listarTodosLosAdministradores() {
		
		Collection<Administrador> lista = administradorDao.listarAdministradores();
		
		return lista;
	}

	///////////////////////////////////////////////////////////////////////////////////
	public void modificarAdministrador(String nick,String nombre, String apellido,
			String email, String password, Date fechaNac, String sexo,
			String celular) {
				//obtengo el usuario
				if(!nick.isEmpty())
				{
					Administrador u = administradorDao.obtenerAdministrador(nick);
					if(!password.isEmpty()) { u.setPassword(password);}
					if(!email.isEmpty()){ u.setEmail(email);}
					if(!nombre.isEmpty()){u.setNombre(nombre);}
					if(fechaNac != null){ u.setFechaNac(fechaNac);}
					if(!apellido.isEmpty()){u.setApellido(apellido);}
					if(!sexo.isEmpty()){u.setSexo(sexo);}
					u.setCelular(celular);
					
					dataService.update(u);
					
				}	
		
	}

	///////////////////////////////////////////////////////////////////////////////////
	public void eliminarAdministrador(String nick) {
	
		 if(!nick.isEmpty())
		 {
			 //busco si exite el administrador a borrar
			 Administrador a = administradorDao.obtenerAdministrador(nick);
			 dataService.delete(a);
			 
		 }
		
	}

	///////////////////////////////////////////////////////////////////////////////////
	public boolean existeAdministradorEB(String nick) {
	
		boolean existe= administradorDao.existeAdministrador(nick);
		
		return existe;
	}

	///////////////////////////////////////////////////////////////////////////////////
	public Administrador obtenerAdministradorEB(String nick) {
		
		Administrador admin = administradorDao.obtenerAdministrador(nick);
		
		return admin;
	}
	
}
