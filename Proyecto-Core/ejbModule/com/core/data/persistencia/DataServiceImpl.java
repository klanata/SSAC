package com.core.data.persistencia;

/*
 * Autor: Stephy*/
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.core.data.persistencia.DataService;
import com.core.data.persistencia.AbstractService;


@Stateless
public class DataServiceImpl extends AbstractService implements DataService {

	private static final long serialVersionUID = 1420980238572534022L;
	
	@PersistenceContext
	protected EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}