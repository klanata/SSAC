package com.core.data.persistencia;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.core.data.entites.DeBienes;
import com.core.data.entites.DeServicios;
import com.core.data.entites.Ong;
import com.core.data.persistencia.interfaces.locales.DeBienesDAO;
import com.core.data.persistencia.interfaces.locales.DeServicioDAO;

@Stateless
public class DeServicioDAOImpl extends AbstractService implements DeServicioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/////////////////////////////////////////////////////////////////////////////
	@Override
	protected EntityManager getEntityManager() {
		
		return null;
	}
	@EJB
	DataService dataService;
	/////////////////////////////////////////////////////////////////////////////
	@Override
	public void crearBienes(DeServicios deServicio) throws Exception {
		try{
			dataService.create(deServicio);
		}
		catch (Exception e){
			throw e;
		} 
	}
	/////////////////////////////////////////////////////////////////////////////
	@Override
	public void agregarDonacionDeServicioOng(Ong ong, DeServicios deServicio) {
		try{
			
			//busco ong
			Ong ongGuardar= dataService.find(Ong.class, ong.getId());
			Collection<DeServicios> lista = ongGuardar.getDonacionesDeServicios();
			lista.add(deServicio);
			dataService.update(ongGuardar);
		}
		catch (Exception e){throw e;}
		
	}
	

}
