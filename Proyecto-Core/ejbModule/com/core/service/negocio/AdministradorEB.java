package com.core.service.negocio;

import java.math.BigDecimal;
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

	@Override
	public Long crearAdministrador(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, Integer celular) throws Exception {
			
		Administrador r = new Administrador();
		r.setNombre(nombre);
		r.setApellido(apellido);
		r.setCelular(celular);
		r.setEmail(email);
		r.setFechaNac(fechaNac);
		r.setPassword(password);
		r.setSexo(sexo);
		Long id = administradorDao.crearAdministrador(r);
		return id;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	@Override
	public Boolean existeUsuario(String nick, String password) {
		
		Boolean existe = false;
		
		try{
			
			 existe= administradorDao.buscarAdministradorNickPass(nick, password);
			
			
		}catch(Exception e){}
		
		
		
		return existe;
	}
	
}
