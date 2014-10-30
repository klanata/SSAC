

package com.serviciorest.cliente;
import com.serviciorest.modelo.*;
import com.core.service.negocio.locales.ServiciosSeguridadImpl;
import java.util.Scanner;

public class Sistema {

	static ServiciosSeguridadImpl servicio = new ServiciosSeguridadImpl();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Ingrese un numero: ");
		Scanner scanner = new Scanner(System.in);
		String numero = scanner.nextLine();
		scanner.close();
		
		Persona pers = new Persona();
		PersonaCliente cliente= new PersonaCliente();
		pers =  cliente.get(numero);
		System.out.print(pers.getNombre());
	}

}
