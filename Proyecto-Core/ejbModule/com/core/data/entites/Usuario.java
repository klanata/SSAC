package com.core.data.entites;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Usuario
 *
 */

@Entity
@SequenceGenerator(name = "usuario_sequence",
sequenceName = "usuario_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	
@NamedQuery(name="Usuario.BuscarPersona", 
query = "SELECT u "+
		"FROM Usuario u " +
		"WHERE u.nick = :nick"),

@NamedQuery(name="Usuario.BuscarPersona.Nick.Pass", 
query = "SELECT u "+
		"FROM Usuario u " +
		"WHERE u.nick = :nick AND u.pass= :password"),
		
@NamedQuery(name="Usuario.BuscarPersona.ID", 
query = "SELECT u "+
		"FROM Usuario u " +
		"WHERE u.id = :id")
})

@Table (name = "usuario")
@XmlRootElement
public class Usuario extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="usuario_sequence")
	@Column(name= "id", nullable= false)
	private Long id;
		
	@Column(name= "nick",  nullable= false)
	private String nick = "";
	
	@Column(name= "pass", nullable= false)
	private String pass = "";
	
	@Column(name= "mail")
	private String email="";
	
	@Column(name= "nombre",  nullable= false)
	private String nombre= "";
	
	@Column(name= "fecha", nullable= false)
	private Date fechaNac;
	
	private boolean bajaLogica;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Catastrofe> catastrofesRegistradas = new HashSet<Catastrofe>(0);
	

	private String imagen;
	
	
	
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Set<Catastrofe> getCatastrofesRegistradas() {
		return catastrofesRegistradas;
	}

	public void setCatastrofesRegistradas(Set<Catastrofe> catastrofesRegistradas) {
		this.catastrofesRegistradas = catastrofesRegistradas;
	}

	public boolean isBajaLogica() {
		return bajaLogica;
	}

	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	
	// GETTERS
	public Long getId(){
		return id;
	}
	
	public String getNick(){
		return nick;
	}
	
	public String getPassword(){
		return this.pass; 
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Date getFechaNac() {
		return fechaNac;
	}
	
	
	// SETTERS
	public void setId(Long id){
		this.id = id;
	}
	
	public void setNick(String login){
		this.nick = login;
	}
	
	public void setPassword(String password){
		this.pass = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	
	
	
}
