package com.core.service.negocio;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import com.core.data.entites.Ong;
import com.core.data.entites.DeServicios;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.DeServicioDAO;
import com.core.service.negocio.remote.DeServiciosEBR;

@Path("/deservicios") 
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//DeServiciosEB!com.core.service.negocio.remote.DeServiciosEBR")

public class DeServiciosEB implements DeServiciosEBR{
	@EJB
	private DataService dataService;
	
	@EJB 
	private DeServicioDAO deserviciosDAO;
	
	public void crearDonacionDeServicios(Long idOng, String usuario, Date fechaRealizada,
			String areaConocimient, BigDecimal cantidadHoras)throws Exception{
		
		DeServicios ds = new DeServicios();
		Ong ong = dataService.find(Ong.class,idOng);
		ds.setOng(ong);
		ds.setUsuario(usuario);
		ds.setFechaRealizada(fechaRealizada);
		ds.setAreaConocimient(areaConocimient);
		ds.setCantidadHoras(cantidadHoras);
		try {
			deserviciosDAO.crearServicio(ds);
			Collection<DeServicios> list = ong.getDonacionesDeServicios();
			list.add(ds);
			dataService.update(ong);
		} catch (Exception e) {
			System.out.println("deServicio " + ds);
			throw e;
		}
		
	}
}