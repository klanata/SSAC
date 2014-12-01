package com.web.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;


@ManagedBean(name = "inputBean")
@ViewScoped
public class InputBean implements Serializable{
	
	private static final long serialVersionUID = 9040359120893077422L;
	
	private Part part;
	private String statusMessage;
	
	
	public InputBean() {
		super();
       // this.part = part;
       // this.statusMessage = statusMessage;
    }	
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String uploadFile(Part part) throws IOException {
		
		//Extrae la parte de la cabezera del archivo desde el content-disposition
		String fileName = getFileName(part);
		System.out.println("***** fileName: " + fileName);
		
		String jboss = System.getenv("JBOSS_HOME");
		int x = new Double(Math.random() * 1000000).intValue();
		
		File outputFilePath = new File(x + fileName);		
		String fileString = outputFilePath.toString();	
		outputFilePath = new File(jboss + "\\Proyecto\\imagenes.war\\" + x + fileName);
		
		//Copia el archivo subido a el path destino
		InputStream inputStream = null;
		OutputStream outputStream = null;		
		try {
			inputStream = part.getInputStream();
			outputStream = new FileOutputStream(outputFilePath);
 
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
 
			statusMessage = "Archivo subido exitoso !!";
		} catch (IOException e) {
			e.printStackTrace();
			statusMessage = "Archivo subido no exitoso !!";
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return fileString;    // return el string con la ruta del archivo
		
	}
	
	// Extrae la parte de la cabezera del archivo desde el content-disposition
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("***** partHeader: " + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}

}
