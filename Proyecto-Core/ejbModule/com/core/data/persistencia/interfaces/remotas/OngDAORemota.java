package com.core.data.persistencia.interfaces.remotas;

import javax.ejb.Remote;

import com.core.data.entites.Ong;
import com.core.data.persistencia.JPAService;

@Remote
public interface  OngDAORemota extends JPAService{
	
	public Long insert(Ong ong)  throws Exception;
	
	public boolean existeOng(String nombre);
	
	

}
