package com.core.service.negocio.remote;

import java.util.Date;
import java.math.BigDecimal;
import javax.ejb.Remote;


@Remote
public interface DeServiciosEBR {
	public void crearDonacionDeServicios(Long idOng, String usuario, Date fechaRealizada,
			String areaConocimient, BigDecimal cantidadHoras)throws Exception;
	
}