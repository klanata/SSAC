package com.core.service.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.Administrador;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.AdministradorDAO;
import com.core.service.negocio.remote.AdministradorEBR;


@Path("/catastrofes")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//AdministradorEB!com.core.service.negocio.remote.AdministradorEBR")

public class AdministradorEB implements AdministradorEBR{


	@EJB
	private DataService dataService;
	
	
	
	@EJB
	private AdministradorDAO administradorDao;

	@Override
	public Boolean existeUsuario(String nick, String password) {
		
		Boolean existe = false;
		
		try{
			
			 existe= administradorDao.buscarAdministradorNickPass(nick, password);
			
			
		}catch(Exception e){}
		
		
		
		return existe;
	}
	
}
