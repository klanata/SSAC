import java.util.List;

import javax.ejb.Remote;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Servicio;


@Remote
public interface CatastrofeEBR {

	public Integer ingesarCatastrofe(String nombreEvento, String descripcion, String logo, BigDecimal coordenadasX,BigDecimal coordenadasY, Boolean activa, Boolean prioridad, Collection<Servicio> servicios, Collection<Ong> ongs,PlanDeRiesgo planDeRiesgo)throws Exception;
	//Retorna 0 si no puede ingresar la catastrofe, de lo contrario devuelve el id 
	
	public Catastrofe buscaCatastrofe(String nombreEvento) throws Exception;
	
	public List<Catastrofe> listaCatastrofes() throws Exception;
	
	
}
