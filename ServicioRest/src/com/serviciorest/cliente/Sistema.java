

package com.serviciorest.cliente;

import com.core.service.negocio.UsuarioEB;

import java.util.Scanner;

public class Sistema {

	static UsuarioEB servicio = new UsuarioEB();
	public static void main(String[] args) {
		

		System.out.println("Ingrese un numero: ");
		Scanner scanner = new Scanner(System.in);
		//String numero = scanner.nextLine();
		scanner.close();
		
		//Persona pers = new Persona();
	//	PersonaCliente cliente= new PersonaCliente();
		//pers =  cliente.get(numero);
		//System.out.print(pers.getNombre());
	}

}
