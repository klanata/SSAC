package com.core.service.negocio.locales;

import java.util.Date;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.core.data.entites.Usuario;

import com.core.data.persistencia.UsuarioDAO;
import com.core.service.negocio.ServiciosSeguridad;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiciosSeguridadImpl implements ServiciosSeguridad{

	@EJB
	private UsuarioDAO usuarioDAO;
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
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
	
	@Override
	public int funciona(int numero){
		
		return numero+5;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Usuario buscarUsuario(String id)
	{
		System.out.println("Esto tiene el id");
		Integer _id= new Integer(id);
		System.out.println("Esto tiene el id"+ _id);
		Usuario u = new Usuario();
		u = usuarioDAO.BuscarById(_id);
		return u;
		
	}

}