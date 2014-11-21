package com.core.data.persistencia.interfaces.locales;
import javax.ejb.Local;
import com.core.data.entites.Ong;
import com.core.data.persistencia.JPAService;

@Local
public interface OngDAO extends JPAService{
	
	public Long insert(Ong ong)  throws Exception;
	
	public boolean existeOng(String nombre);
	
	

}
