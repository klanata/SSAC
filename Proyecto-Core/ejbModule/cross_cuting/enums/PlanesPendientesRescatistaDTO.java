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
	private Long idPedidoDeAyuda;
	private String urlArchivo;
	private Boolean estadoTarea;
	private String nombreTarea;
	private double coordenadaX;
	private double coordenaday;
	private String descripcion;
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public double getCoordenaday() {
		return coordenaday;
	}
	public void setCoordenaday(double coordenaday) {
		this.coordenaday = coordenaday;
	}
	public Long getIdEstadoRescatista() {
		return idEstadoRescatista;
	}
	public void setIdEstadoRescatista(Long idEstadoRescatista) {
		this.idEstadoRescatista = idEstadoRescatista;
	}
	
	public Long getIdPedidoDeAyuda() {
		return idPedidoDeAyuda;
	}
	public void setIdPedidoDeAyuda(Long idPedidoDeAyuda) {
		this.idPedidoDeAyuda = idPedidoDeAyuda;
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
