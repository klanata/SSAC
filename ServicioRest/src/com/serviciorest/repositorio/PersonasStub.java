package com.serviciorest.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import com.core.data.entites.Usuario;
import com.core.service.negocio.locales.*;
import com.serviciorest.modelo.*;

public class PersonasStub {
	
	List<Persona> personas = new ArrayList<Persona>();
	
	@EJB
	private ServiciosSeguridadImpl servicioSeguridad;
	
	public List<Persona> getPersonas(){
		
	Persona persona1 = new Persona(1,"Brian","May");
	Persona persona2 = new Persona(2,"Freddie","Mercury");
	Persona persona3 = new Persona(3,"Roger","Taylor");
	
	personas.add(persona1);
	personas.add(persona2);
	personas.add(persona3);
	
	return personas;
	
	}

	public Persona getPersona(String id) {
		
		
		ServiciosSeguridadImpl servicioSeguridad = new ServiciosSeguridadImpl();
		Usuario usuario = servicioSeguridad.buscarUsuario(id);
		
		return new Persona(1,"Brian","May");
		//return new Persona(usuario.getId(),usuario.getNick(),usuario.getNombre());
		
		
		
	}
	
	public String funciona(String num)
	{
		ServiciosSeguridadImpl servicio = new ServiciosSeguridadImpl();
		int SeisMasCinco = servicio.funciona(Integer.parseInt(num));
		return String.valueOf(SeisMasCinco);
	}
	

}
