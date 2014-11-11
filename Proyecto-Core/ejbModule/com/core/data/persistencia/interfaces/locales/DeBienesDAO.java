package com.core.data.persistencia.interfaces.locales;

import javax.ejb.Local;

import com.core.data.entites.DeBienes;
import com.core.data.entites.Ong;
import com.core.data.persistencia.JPAService;

@Local
public interface DeBienesDAO extends JPAService  {
	
	public void crearBienes(DeBienes deBienes);
	public void agregarDonacionDeBienesOng(Ong ong,DeBienes deBienes);
	

}
