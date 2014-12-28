package com.web.filtro;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.annotation.PostConstruct; 

import clienteutility.ClienteUtility;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.ImagenCatastrofe;
import com.core.data.entites.Ong;
import com.core.data.entites.PlanDeRiesgo;
import com.core.data.entites.Filtro;
import com.core.service.negocio.remote.CatastrofeEBR;
import com.web.beans.InputBean;

import javax.servlet.http.Part;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


@ManagedBean(name="catastrofeUsuarioBean")
@RequestScoped
public class CatastrofeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String nombreEvento;
	private String descripcion;
	private String logo ;
	private double coordenadasX;
	private double coordenadasY;
	private Boolean activa;
	private Boolean prioridad;
	private String css;
	private Set<ImagenCatastrofe> imagenes = new HashSet<ImagenCatastrofe>();
	private Set<Filtro> filtros = new HashSet<Filtro>();
	private Set<Ong> ongs  =  new HashSet<Ong>();
	private PlanDeRiesgo planDeRiesgo;	
	
	
	
	//	------------------ Constructors  --------------------------------
	
	public CatastrofeBean() {	
	}	
	public CatastrofeBean(Long id, String nombreEvento, String descripcion, String logo, double coordenadasX,
			double coordenadasY, Boolean activa, Boolean prioridad, String css) {
		super();
		this.id = id;
		this.nombreEvento = nombreEvento;
		this.descripcion = descripcion;
		this.logo = logo;
		this.coordenadasX = coordenadasX;
		this.coordenadasY = coordenadasY;
		this.activa = activa;
		this.prioridad = prioridad;
		this.css = css;
	}
		
	
	//	------------------ Getter and setter methods ---------------------
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getCoordenadasX() {
		return coordenadasX;
	}
	public void setCoordenadasX(double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}
	public double getCoordenadasY() {
		return coordenadasY;
	}
	public void setCoordenadasY(double coordenadasY) {
		this.coordenadasY = coordenadasY;
	}
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	public Boolean getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Boolean prioridad) {
		this.prioridad = prioridad;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public Set<ImagenCatastrofe> getImagenes() {
		return imagenes;
	}
	public void setImagenes(Set<ImagenCatastrofe> imagenes) {
		this.imagenes = imagenes;
	}	
	public Set<Filtro> getFiltros() {
		return filtros;
	}
	public void setFiltros(Set<Filtro> filtros) {
		this.filtros = filtros;
	}
	public Set<Ong> getOngs() {
		return ongs;
	}
	public void setOngs(Set<Ong> ongs) {
		this.ongs = ongs;
	}
	public PlanDeRiesgo getPlanDeRiesgo() {
		return planDeRiesgo;
	}
	public void setPlanDeRiesgo(PlanDeRiesgo planDeRiesgo) {
		this.planDeRiesgo = planDeRiesgo;
	}	

	
	
			
}
