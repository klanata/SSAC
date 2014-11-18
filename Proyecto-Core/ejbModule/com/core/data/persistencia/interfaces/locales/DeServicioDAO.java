package com.core.data.persistencia.interfaces.locales;

import com.core.data.entites.DeServicios;
import com.core.data.entites.Ong;
import com.core.data.persistencia.JPAService;

public interface DeServicioDAO  extends JPAService  {
	
	public void crearServicio(DeServicios deServicio) throws Exception;
	public void agregarDonacionDeServicioOng(Ong ong, DeServicios deServicio);

}
