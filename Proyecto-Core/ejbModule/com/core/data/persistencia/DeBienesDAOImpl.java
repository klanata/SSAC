package com.core.data.persistencia;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.core.data.entites.DeBienes;
import com.core.data.entites.Ong;
import com.core.data.persistencia.interfaces.locales.DeBienesDAO;

@Stateless
public class DeBienesDAOImpl  extends AbstractService implements DeBienesDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected EntityManager getEntityManager() {
		
		return null;
	}
	@EJB
	DataService dataService;
	////////////////////////////////////////////////////////////////////////
	@Override
	public void crearBienes(DeBienes deBienes) {
		try{
			dataService.create(deBienes);
		}
		catch (Exception e){try {
			throw e;
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}}
		
	}
	///////////////////////////////////////////////////////////////////////
	@Override
	public void agregarDonacionDeBienesOng(Ong ong,DeBienes deBienes) {
		try{
			
			//busco ong
			Ong ongGuardar= dataService.find(Ong.class, ong.getId());
			Collection<DeBienes> lista = ongGuardar.getDonacionesDeBienes();
			lista.add(deBienes);
			dataService.update(ongGuardar);
		}
		catch (Exception e){throw e;}
		
	}

}
