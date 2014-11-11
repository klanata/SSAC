package com.core.data.persistencia;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.core.data.entites.Economica;
import com.core.data.entites.Ong;
import com.core.data.persistencia.interfaces.locales.EconomicaDAO;
@Stateless
public class EconomicaDAOImpl  extends AbstractService implements EconomicaDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	DataService dataService;
	@Override
	public void crearDonacionEconomica(Economica economica) throws Exception {
		try{
				dataService.create(economica);
		}catch(Exception e){throw e;}
		// 
		
	}

	@Override
	public void agregarDonacionEconomicaONG(Ong ong, Economica economica) {
		try{
			
			//busco ong
			Ong ongGuardar= dataService.find(Ong.class, ong.getId());
			Collection<Economica> lista = ongGuardar.getDonacionesEconomicas();
			lista.add(economica);
			dataService.update(ongGuardar);
		}
		catch (Exception e){throw e;} 	
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return null;
	}

}
