package com.core.data.persistencia.interfaces.locales;

import java.util.List;

import javax.ejb.Local;

import com.core.data.entites.Catastrofe;
import com.core.data.persistencia.JPAService;


@Local
public interface CatastrofeDAO extends JPAService{
	
	public Long insert(Catastrofe catastrofe)  throws Exception;
	
	public boolean existeCatastrofe(String nombreEvento);
	
	public Catastrofe buscarCatastrofePorNombre(String nombreEvento) throws Exception;
	
	public Catastrofe buscarCatastrofePorId(Long id) throws Exception;

	public List<Catastrofe> listarCatastrofes() throws Exception;
	
	

}
