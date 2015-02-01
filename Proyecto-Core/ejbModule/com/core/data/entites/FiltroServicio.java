package com.core.data.entites;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: FiltroServicio
 *
 */
@Entity

@SequenceGenerator(name = "filtroServicio_sequence",
sequenceName = "filtroServicio_id_seq",
initialValue=1,
allocationSize=1)
@NamedQueries({
	
@NamedQuery(name="FiltroServicio.BuscarTodos", 
			query = "SELECT fs "+
					"FROM FiltroServicio fs "+
					"WHERE fs.bajaLogica = :bajaLogica"),
					
@NamedQuery(name="FiltroServicio.BuscarFiltroServicio.Id", 
			query = "SELECT fs "+
					"FROM FiltroServicio fs " +
					"WHERE fs.id = :id"),
					
@NamedQuery(name="FiltrosServicio.BuscarPorIdServicio", 
			query = "SELECT fs "+
			"FROM FiltroServicio fs "+
			"WHERE fs.bajaLogica = :bajaLogica AND " + 
			"fs.idServicio = :idServicio"),

})

@Table (name = "FiltroServicio")
@XmlRootElement
public class FiltroServicio extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE , generator = "filtroServicio_sequence")
	@Column(name= "id", nullable= false)
	private long id;
	
	@Column(nullable=false)
	private long idFiltro;
	
	@Column(nullable=false)
	private long idServicio;
	
	@ManyToOne
	private Catastrofe catastrofe;
	
	private boolean bajaLogica;
		
	
	//	------------------ Constructors  --------------------------------
	
	public FiltroServicio() {
		super();
		this.idFiltro = 0;
		this.idServicio = 0;	
		this.bajaLogica = false;
	}		
	
	
	/**
	 * @param idFiltro
	 * @param idServicio	
	 */
	public FiltroServicio(long idFiltro, long idServicio, boolean bajaLogica) {
		super();
		this.idFiltro = idFiltro;
		this.idServicio = idServicio;
		this.bajaLogica = bajaLogica;
	}
	
	
	//	------------------ Getter and setter methods ---------------------
			
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdFiltro() {
		return idFiltro;
	}
	public void setIdFiltro(long idFiltro) {
		this.idFiltro = idFiltro;
	}
	public long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}	
	public boolean isBajaLogica() {
		return bajaLogica;
	}
	public void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
	}
	public Catastrofe getCatastrofe() {
		return catastrofe;
	}
	public void setCatastrofe(Catastrofe catastrofe) {
		this.catastrofe = catastrofe;
	}
	
	
   
}
