package com.core.service.negocio;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.core.data.entites.DeBienes;
import com.core.data.entites.Ong;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.DeBienesDAO;
import com.core.service.negocio.remote.DeBienesEBR;


@Path("/debienes")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//DeBienesEB!com.core.service.negocio.remote.DeBienesEBR")
public class DeBienesEB implements DeBienesEBR{
	@EJB
	private DataService dataService;
	
	@EJB
	private DeBienesDAO debienesDAO;
	public void crearDonacionDeBienes(Long idOng, String usuario, Date fechaRealizada, String nombreItem,
			Integer cantidad) throws Exception {
		
		
		System.out.println("deBi " + idOng);
		System.out.println("deBi " + usuario);
		System.out.println("deBi " + fechaRealizada);
		System.out.println("deBi " + nombreItem);
		System.out.println("deBi " + cantidad);
		
		DeBienes db = new DeBienes();
		Ong ong = dataService.find(Ong.class,idOng);
		db.setOng(ong);
		db.setUsuario(usuario);
		db.setFechaRealizada(fechaRealizada);
		db.setNombreItem(nombreItem);
		db.setCantidad(cantidad);
		
		try {
			Long id = debienesDAO.agregarDonacionDeBienesOng(db);
			/**Collection<DeBienes> list = ong.getDonacionesDeBienes();
			list.add(db);
			dataService.update(ong);*/
			
		} catch (Exception e) {
			//System.out.println("deBienes " + db);
			throw e;
		}
		//	e.printStackTrace();
	}
}