package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.core.data.entites.Administrador;
import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Economica;
import com.core.data.persistencia.JPAService;

@Local
public interface  AdministradorDAO extends JPAService {
	
	public Long crearAdministrador(Administrador admin) throws Exception;
	
	public boolean buscarAdministradorNickPass(String nick, String password);
	
	public boolean existeAdministrador(String nick);
	
	public Administrador obtenerAdministrador(String nick);
	
	public Collection<Administrador> listarAdministradores();

	
	public Collection<DeBienes> listaDeBienes(Date fechaInicio ,Date fechaFinal);
	public Collection<DeServicios> listaDeServicios(Date fechaInicio ,Date fechaFinal);
	public Collection<Economica> listaEconomica(Date fechaInicio ,Date fechaFinal);
	
}
