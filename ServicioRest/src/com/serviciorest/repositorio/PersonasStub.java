package com.serviciorest.repositorio;

import java.util.ArrayList;
import java.util.List;
import com.core.service.negocio.locales.*;

import com.serviciorest.modelo.*;

public class PersonasStub {
	
	List<Persona> personas = new ArrayList<Persona>();
	
	public List<Persona> getPersonas(){
		
	Persona persona1 = new Persona(1,"Brian","May");
	Persona persona2 = new Persona(2,"Freddie","Mercury");
	Persona persona3 = new Persona(3,"Roger","Taylor");
	
	personas.add(persona1);
	personas.add(persona2);
	personas.add(persona3);
	
	return personas;
	
	}

	public Persona getPersona(String personaId) {
		
		return new Persona(1,"Brian","May");
	}
	
	public String funciona(String num)
	{
		ServiciosSeguridadImpl servicio = new ServiciosSeguridadImpl();
		int SeisMasCinco = servicio.funciona(Integer.parseInt(num));
		return String.valueOf(SeisMasCinco);
	}
	

}
