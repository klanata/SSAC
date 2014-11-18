package com.core.service.negocio.remote;
import java.util.Date;
import javax.ejb.Remote;



@Remote
public interface DeBienesEBR {
	public void crearDonacionDeBienes(Long idOng, String usuario, Date fechaRealizada, String nombreItem,
			Integer cantidad)throws Exception;
	
}