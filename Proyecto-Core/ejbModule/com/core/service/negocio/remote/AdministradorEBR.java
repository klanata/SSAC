package com.core.service.negocio.remote;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Remote;

import com.core.data.entites.Administrador;
import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
import com.core.data.entites.PedidoDeAyuda;

@Remote
public interface AdministradorEBR {
	
	public boolean existeUsuario(String nick, String password);
	
	public Long crearAdministrador(String nombre, String nick, String apellido,
			String email, String password, Date fechaNac, String sexo, String celular) throws Exception ;
	
	public Collection<Administrador> listarTodosLosAdministradores();
	
	public void modificarAdministrador(String nick,String nombre, String apellido,
			String email, String password, Date fechaNac, String sexo, String celular);
	
	public void eliminarAdministrador(String nick);
	
	

	public boolean existeAdministradorEB(String nick);
	
	public Administrador obtenerAdministradorEB(String nick);
	
	public Administrador obetenrAdministradorPorNick(Long id);
	
	/*********************/
	public Collection<DeBienes> listaDeBienesEnTiempo(Date fechaInicio, Date fechaFinal);
	public Collection<DeServicios> listaDeServiciosEnTiempo(Date fechaInicio, Date fechaFinal);
	public Collection<Economica> listaDeEconomicaEnTiempo(Date fechaInicio, Date fechaFinal);
	
	public Collection<PedidoDeAyuda> listaPedidosAyuda(Date fechaInicio, Date fechaFinal);
	
	
	
}
