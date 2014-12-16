package com.core.service.negocio.remote;

import java.util.Date;

import javax.ejb.Remote;

import com.core.data.entites.Usuario;




@Remote
public interface UsuarioEBR {

	public boolean existeUsuario(String nick, String pass);
	public boolean estaRegistradoCatastrofe(String nick, long idCatastrofe);
	//agrega a la coleccion de catastrofes que tiene
	public void registrarACatastrofe(String nick, String pass, long idCatastrofe);
	public void registroUsuarioPlataforma(String nick, String pass, String mail, String nombre,Date fecha,long idCatastrofe, String imagen);
	
	public Usuario obtenerUsuarioPorNick(String nick);
	public Usuario buscarUsuarioPorID(Long id);
	
	
	
	
	
}
