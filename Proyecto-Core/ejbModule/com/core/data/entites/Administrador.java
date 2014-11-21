package com.core.data.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@NamedQueries({
	
@NamedQuery(name="Administrador.BuscarAdministrador", 
query = "SELECT u "+
		"FROM Administrador u " +
		"WHERE u.nick = :nick"),

@NamedQuery(name="Administrador.BuscarAdministrador.Nick.Pass", 
query = "SELECT u "+
		"FROM Administrador u " +
		"WHERE u.nick = :nick AND u.password= :password")
		

})
@XmlRootElement
public class Administrador  extends AbstractEntity implements Serializable{

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Administrador() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id", nullable= false)
	private Long id;
		
	@Column(nullable= false)
	private String nombre = "";
	
	@Column(nullable= false)
	private String apellido = "";
	
	@Column(nullable= false)
	private String nick = "";
	
	
	private String email = "";
	
	@Column(nullable= false)
	private String password = "";
	
	@Column(nullable= false)
	private Date fechaNac;
	
	@Column()
	private String sexo = "";
	
	
	private Integer celular;
	
	@OneToMany
	private Collection<Catastrofe> catastrofes = new ArrayList<Catastrofe>(0);
	
		
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public Collection<Catastrofe> getCatastrofes() {
		return catastrofes;
	}

	public void setCatastrofes(Collection<Catastrofe> catastrofes) {
		this.catastrofes = catastrofes;
	}

	
}
