package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import javax.ejb.Local;
import com.core.data.entites.Economica;
import com.core.data.persistencia.JPAService;

@Local
public interface EconomicaDAO extends JPAService  {
	
	
	public Economica buscarDonacionEconomica(Long id);
	
	public Collection<Economica> listarDonacionesEconomica(Long idOng);
		
	public Long agregarDonacionEconomicaOng(Economica economica) throws Exception;
	
	

}
