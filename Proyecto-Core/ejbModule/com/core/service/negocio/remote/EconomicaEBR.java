package com.core.service.negocio.remote;
import java.util.Date;
import javax.ejb.Remote;


@Remote
public interface EconomicaEBR {
	public void crearDonacionEconomica(Long idOng, String usuario, Date fechaRealizada)throws Exception;
	
}