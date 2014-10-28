package test.serviciorest.cliente;

import static org.junit.Assert.*;

import org.junit.Test;

import com.serviciorest.cliente.PersonaCliente;
import com.serviciorest.modelo.Persona;

public class PersonaClienteTest {

	@Test
	public void testGet() {
		PersonaCliente cliente= new PersonaCliente();
		
		Persona persona = cliente.get("1");
		
		assertNotNull(persona);
	}

}
