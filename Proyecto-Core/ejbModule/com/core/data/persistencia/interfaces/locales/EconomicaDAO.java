package com.core.data.persistencia.interfaces.locales;

import javax.ejb.Local;

import com.core.data.entites.Economica;
import com.core.data.entites.Ong;
import com.core.data.persistencia.JPAService;

@Local
public interface EconomicaDAO extends JPAService  {
	
	public void crearDonacionEconomica(Economica economica) throws Exception;
	public void agregarDonacionEconomicaONG(Ong ong, Economica economica);
	

}
