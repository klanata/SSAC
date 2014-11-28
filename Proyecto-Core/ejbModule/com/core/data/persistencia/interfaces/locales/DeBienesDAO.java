package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import javax.ejb.Local;

import com.core.data.entites.DeBienes;
import com.core.data.persistencia.JPAService;

@Local
public interface DeBienesDAO extends JPAService  {
	
	
	
	public DeBienes buscarDonacionBinesID(Long id);
	
	public Collection<DeBienes> listarDonacionesDeBienes(Long idOng);
		
	public Long agregarDonacionDeBienesOng(DeBienes deBienes) throws Exception;
	

}
