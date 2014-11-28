package com.core.service.negocio;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import com.core.data.entites.Ong;
import com.core.data.entites.Economica;
import com.core.data.persistencia.DataService;
import com.core.data.persistencia.interfaces.locales.EconomicaDAO;
import com.core.service.negocio.remote.EconomicaEBR;


@Path("/economica")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//EconomicaEB!com.core.service.negocio.remote.EconomicaEBR")
public class EconomicaEB implements EconomicaEBR{
	@EJB
	private DataService dataService;
	
	@EJB
	private EconomicaDAO economicaDAO;
	
	public void crearDonacionEconomica(Long idOng, String usuario, Date fechaRealizada, 
			BigDecimal monto)throws Exception{
		
		Economica eco = new Economica();
		Ong ong = dataService.find(Ong.class, idOng);
		eco.setOng(ong);
		
		eco.setUsuario(usuario);
		eco.setFechaRealizada(fechaRealizada);
		eco.setMonto(monto);
		try {
			 Long id = economicaDAO.agregarDonacionEconomicaOng(eco);
			/*Collection<Economica> list = ong.getDonacionesEconomicas();
			list.add(eco);
			dataService.update(ong);*/
		} catch (Exception e) {
			throw e;
		}
		
	}
}