package com.core.data.persistencia.interfaces.locales;

import java.util.List;
import javax.ejb.Local;
import com.core.data.entites.Catastrofe;


@Local
public interface CatastrofeDAO {
	
	public Integer insert(Catastrofe catastrofe)  throws Exception;
	
	public boolean existeCatastrofe(String nombreEvento);
	
	public Catastrofe buscarCatastrofe(String nombreEvento) throws Exception;

	public List<Catastrofe> listarCatastrofes() throws Exception;

}
