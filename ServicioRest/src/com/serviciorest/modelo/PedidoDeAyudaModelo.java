package com.serviciorest.modelo;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PedidoDeAyudaModelo {
	
	private Integer id;
	private String descripcion;
	private Boolean procesando ;
	private Boolean valido ;
	private Date fechaPublicacion ;
	private long idCatastrofe;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long getIdCatastrofe() {
		return idCatastrofe;
	}
	public void setIdCatastrofe(long idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getProcesando() {
		return procesando;
	}
	public void setProcesando(Boolean procesando) {
		this.procesando = procesando;
	}
	public Boolean getValido() {
		return valido;
	}
	public void setValido(Boolean valido) {
		this.valido = valido;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}


}
