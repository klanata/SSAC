package com.serviciorest.cliente;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.serviciorest.modelo.Persona;
import com.sun.jersey.api.client.ClientRequest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;

//cliente web rest del web service ServicioPersonas
public class PersonaCliente {

	private Client cliente;
	
	public PersonaCliente()
	{	
	}
	
	public Persona get(String id)
	{
		Persona customer=null;
		try{
			
			String uri ="http://localhost:8080/PruebaRest/catastrofe/personas/2";
			URL url = new URL(uri);
			
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			JAXBContext jc = JAXBContext.newInstance(Persona.class);
			InputStream xml = connection.getInputStream();
			customer = (Persona) jc.createUnmarshaller().unmarshal(xml);

			connection.disconnect();
			
		}
		catch (Exception e){
			System.out.print(e.getMessage());
		}
		
		return customer;
		
		
	}
}
