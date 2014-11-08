package com.web.beans;

import java.io.Serializable;
import java.math.BigInteger;
//import java.text.DateFormat;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.core.service.negocio.remote.UsuarioEBR;


public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
//ejb
	@EJB
	private UsuarioEBR servicioSeguridad;
	
	private String txtNick;
	private String txtPassword;
	private String txtEmail;
	private String txtNombre;
	private Date fechaNac;
	
	// GETTERS
	public String getTxtNick() {
		return txtNick;
	}
	
	public String getTxtPassword() {
		return txtPassword;
	}
	
	public String getTxtEmail() {
		return txtEmail;
	}
	
	public String getTxtNombre() {
		return txtNombre;
	}
	
	public Date getFechaNac() {
		return fechaNac;
	}
	
	// SETTERS
	public void setTxtNick(String txtNick) {
		this.txtNick = txtNick;
	}

	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	// Metodos de Negocio
	
	public String ingresarUsu_ON_CLICK(){
		
		String outcome = null;

		Date fechaNac = new Date();
		fechaNac.getTime();
		
		
		servicioSeguridad.ingesarUsuraio(this.txtNick, MD5(this.txtPassword), this.txtEmail, 
										this.txtNombre, fechaNac);
		
		outcome = "sucess";
		
		return outcome;
		
	}
	
	private String MD5(String input) {

		String md5 = null;

		if(null == input) return null;

		try {

			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return md5;
	}
	
	
}
