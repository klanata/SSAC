package com.core.data.persistencia.interfaces.locales;

import java.util.Collection;

import javax.ejb.Local;

import com.core.data.entites.Ong;
import com.core.data.persistencia.JPAService;

import cross_cuting.enums.TipoCatastrofe;
@Local
public interface OngDAO extends JPAService{
	
	public void crearONG(Ong ong);
	public Collection<Ong> listarOng(TipoCatastrofe tipoCatastrofe);
	

}
