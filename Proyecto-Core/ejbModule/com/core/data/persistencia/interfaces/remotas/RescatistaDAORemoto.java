package com.core.data.persistencia.interfaces.remotas;
import javax.ejb.Remote;

import com.core.data.entites.EstadoRescatista;

import com.core.data.persistencia.JPAService;

@Remote
public interface RescatistaDAORemoto extends JPAService {
	
	
	public void pendienteRealizado(EstadoRescatista estadorescatista);

}


