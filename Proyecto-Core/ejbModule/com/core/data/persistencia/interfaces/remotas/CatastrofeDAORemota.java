package com.core.data.persistencia.interfaces.remotas;

import java.util.List;
import javax.ejb.Remote;
import com.core.data.entites.Catastrofe;


@Remote
public interface CatastrofeDAORemota {

	public Integer insert(Catastrofe catastrofe)  throws Exception;
	
	public boolean existeCatastrofe(String nombreEvento);
	
	public Catastrofe buscarCatastrofe(String nombreEvento) throws Exception;

	public List<Catastrofe> listarCatastrofes() throws Exception;

}
