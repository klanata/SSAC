package com.core.service.negocio;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
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
	
	@PersistenceContext
	protected EntityManager em;
	
	protected EntityManager getEntityManager() {
		
		return em;
	}
	////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean existeUsuario(String nick, String pass) {
		
		boolean existe =false ;
		
		
			existe=  usuarioDAO.existeUsuario(nick, pass);
			
		
		return existe;
	}
	@Override
	public boolean estaRegistradoCatastrofe(String nick, 
			long idCatastrofe) {
		
		//
		
		
		return false;
	}
	@Override
	public void registrarACatastrofe(String nick, String pass, long idCatastrofe) {
		
		
	}
	@Override
	public void registroUsuarioPlataforma(String nick, String pass,
			String mail, String nombre, Date fecha, long idCatastrofe) {
		
		//se ejecuta solo una vez
		Catastrofe c = dataService.find(Catastrofe.class, idCatastrofe);
		Usuario u = new Usuario();
		u.setEmail(mail);
		u.setFechaNac(fecha);
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPassword(pass);
		u.setBajaLogica(false);
		Set<Catastrofe> lista = u.getCatastrofesRegistradas();
		lista.add(c);
		u.setCatastrofesRegistradas(lista);
		
		try {
			Long id = usuarioDAO.insert(u);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public Usuario obtenerUsuario(String nick) {
		
		Usuario usuario = null;
		try{
			Query consulta = this.em.createNamedQuery("Usuario.BuscarPersona");
		  	consulta.setParameter("nick", nick);
		  	if (!consulta.getResultList().isEmpty()){
		  		usuario = (Usuario) consulta.getResultList().get(0);
		  	} 
		}catch (Exception excep){			
			throw excep;
		} 	
	  	return usuario;
		
	}
	@Override
	public Usuario buscarUsuario(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
	

}