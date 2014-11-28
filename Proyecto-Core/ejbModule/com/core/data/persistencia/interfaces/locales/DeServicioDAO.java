package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import javax.ejb.Local;
import com.core.data.entites.DeServicios;
import com.core.data.persistencia.JPAService;

@Local
public interface DeServicioDAO  extends JPAService  {
	
	public DeServicios buscarDonacionDeServicioID(Long id);
	
	public Collection<DeServicios> listarDonacionesDeServicio(Long idOng);
		
	public Long agregarDonacionDeServicioOng(DeServicios deServicios) throws Exception;
	
}
