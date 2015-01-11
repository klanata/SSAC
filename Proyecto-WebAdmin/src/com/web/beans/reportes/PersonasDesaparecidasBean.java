package com.web.beans.reportes;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="personasdesaparecidasBean")
@RequestScoped
public class PersonasDesaparecidasBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombreCatastrofe;
	private Long id;
	private Long idCatastrofe;
	private String nombre;
	private String apellido ;
	private String cedula;
	private String numeroContacto ;	
	private Date fechNac;
	private String descripcion ;
	private Boolean hallada;
	private String imagen;
	
	
	
	public Long getIdCatastrofe() {
		return idCatastrofe;
	}
	public void setIdCatastrofe(Long idCatastrofe) {
		this.idCatastrofe = idCatastrofe;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	public String getNombreCatastrofe() {
		return nombreCatastrofe;
	}
	public void setNombreCatastrofe(String nombreCatastrofe) {
		this.nombreCatastrofe = nombreCatastrofe;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNumeroContacto() {
		return numeroContacto;
	}
	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}
	public Date getFechNac() {
		return fechNac;
	}
	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getHallada() {
		return hallada;
	}
	public void setHallada(Boolean hallada) {
		this.hallada = hallada;
	}
	
	
	
	
	public PersonasDesaparecidasBean(String nombreCatastrofe, Long id,
			Long idCatastrofe, String nombre, String apellido, String cedula,
			String numeroContacto, Date fechNac, String descripcion,
			Boolean hallada, String imagen) {
		super();
		this.nombreCatastrofe = nombreCatastrofe;
		this.id = id;
		this.idCatastrofe = idCatastrofe;
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula=cedula;
		this.numeroContacto = numeroContacto;
		this.fechNac = fechNac;
		this.descripcion = descripcion;
		this.hallada = hallada;
		this.imagen = imagen;
	}
	public PersonasDesaparecidasBean() {
		super();}
}
