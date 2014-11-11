package com.core.data.persistencia;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.core.data.entites.Ong;
import com.core.data.persistencia.interfaces.locales.OngDAO;

import cross_cuting.enums.TipoCatastrofe;

/*Autor: Stephy
 * */
@Stateless

public class OngDAOImpl extends AbstractService   implements OngDAO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	DataService dataService;

	@Override
	public void crearONG(Ong ong) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Ong> listarOng(TipoCatastrofe tipoCatastrofe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
