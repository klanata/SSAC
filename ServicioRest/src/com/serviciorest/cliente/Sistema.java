

package com.serviciorest.cliente;
import com.serviciorest.modelo.*;
import java.util.Scanner;

public class Sistema {

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
