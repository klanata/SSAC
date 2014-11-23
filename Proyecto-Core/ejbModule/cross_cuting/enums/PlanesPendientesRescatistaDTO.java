package cross_cuting.enums;
/**
 * Autor:Stephy
 */
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class PlanesPendientesRescatistaDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long idEstadoRescatista;
	private Long idCatastrofe;
	private String urlArchivo;
	private Boolean estadoTarea;
	private String nombreTarea;
	
	public Long getIdEstadoRescatista() {
		return idEstadoRescatista;
	}
	public void setIdEstadoRescatista(Long idEstadoRescatista) {
		this.idEstadoRescatista = idEstadoRescatista;
	}
	public Long getIdCatastrofe() {
		return idCatastrofe;
	}
	public void setIdCatastrofe(Long idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}
	public String getUrlArchivo() {
		return urlArchivo;
	}
	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}
	public Boolean getEstadoTarea() {
		return estadoTarea;
	}
	public void setEstadoTarea(Boolean estadoTarea) {
		this.estadoTarea = estadoTarea;
	}
	public String getNombreTarea() {
		return nombreTarea;
	}
	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	

}
