package com.serviciorest.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona {
	
	private String nombre;
	private String apellido;
	private int id;
	
	public Persona() {
		this.nombre = "John";
		this.apellido = "Doe";
	}
	
	public Persona(int id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
