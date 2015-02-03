package com.core.service.negocio;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Path;

import com.core.data.entites.Catastrofe;
import com.core.data.entites.Filtro;
import com.core.data.entites.Servicio;
import com.core.data.entites.FiltroServicio;
import com.core.data.persistencia.interfaces.locales.CatastrofeDAO;
import com.core.data.persistencia.interfaces.locales.FiltroServicioDAO;
import com.core.data.persistencia.interfaces.locales.ServicioDAO;
import com.core.service.negocio.remote.FiltroServicioEBR;
import com.core.data.persistencia.DataService;


@Path("/filtroServicios")
@Stateless(mappedName="ejb:Proyecto-EAR/Proyecto-Core//FiltroServicioEB!com.core.service.negocio.remote.FiltroServicioEBR")
public class FiltroServicioEB implements FiltroServicioEBR{
	
	@EJB
	private FiltroServicioDAO filtroServicioDAO;
	
	@EJB
	private DataService dataService;	
	
	@EJB
	private ServicioDAO servicioDAO;
	
	@EJB
	private CatastrofeDAO catastrofeDAO;

	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long ingesarFiltroServicio(Filtro filtro,Servicio servicio)throws Exception {
				
		FiltroServicio fs = new FiltroServicio();
		Long id;	
		
		Long idFiltro = filtro.getId();
		Long idServicio = servicio.getId();
		
		fs.setIdFiltro(idFiltro);
		fs.setIdServicio(idServicio);		
				
		id = filtroServicioDAO.insert(fs);
		return id;											
	}
		
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public FiltroServicio buscaFiltroServicioPorId(Long id) throws Exception {
		FiltroServicio fs = new FiltroServicio();
		fs = filtroServicioDAO.buscarFiltroServicioPorId(id);
		return fs;
	}
	
	public List<FiltroServicio> listaAllFiltroServicios() throws Exception{
		List<FiltroServicio> listFiltroServicios = new ArrayList<FiltroServicio>();	
		List<FiltroServicio> listAllFiltroServicios = filtroServicioDAO.listarFiltroServicios();
		
		FiltroServicio filtroServicio;
		Catastrofe catastrofe;
		boolean bajaLogicia;
				
		for (int i=0; i<=listAllFiltroServicios.size()-1; i++){
			filtroServicio = listAllFiltroServicios.get(i);
			catastrofe = filtroServicio.getCatastrofe();
			if (catastrofe != null){
				bajaLogicia = catastrofe.getBajaLogica();
				if (bajaLogicia == false){
					listFiltroServicios.add(filtroServicio);					
				}				
			}	
			else{
				listFiltroServicios.add(filtroServicio);	
			}
				
		}
		
		return listFiltroServicios;
		
	}
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<FiltroServicio> listaFiltroServiciosCatastrofesNoDadasDeBaja() throws Exception {
		List<FiltroServicio> listFiltroServicios = new ArrayList<FiltroServicio>();		
		List<FiltroServicio> listAllFiltroServicios = filtroServicioDAO.listarFiltroServicios();
		
		FiltroServicio filtroServicio;
		Catastrofe catastrofe;
		boolean bajaLogicia;
				
		for (int i=0; i<=listAllFiltroServicios.size()-1; i++){
			filtroServicio = listAllFiltroServicios.get(i);
			catastrofe = filtroServicio.getCatastrofe();
			if (catastrofe != null){
				bajaLogicia = catastrofe.getBajaLogica();
				if (bajaLogicia == false){
					listFiltroServicios.add(filtroServicio);					
				}				
			}									
		}
		
		return listFiltroServicios;
	}	
	
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<FiltroServicio> listaFiltroServiciosSinCatastrofe() throws Exception {
		List<FiltroServicio> listFiltroServicios = new ArrayList<FiltroServicio>();		
		List<FiltroServicio> listAllFiltroServicios = filtroServicioDAO.listarFiltroServicios();
		
		FiltroServicio filtroServicio;
		Catastrofe catastrofe;
		
		for (int i=0; i<=listAllFiltroServicios.size()-1; i++){
			filtroServicio = listAllFiltroServicios.get(i);
			catastrofe = filtroServicio.getCatastrofe();
			if (catastrofe == null){
				listFiltroServicios.add(filtroServicio);
			}									
		}
		
		return listFiltroServicios;
	}	
		
	public List<FiltroServicio> listaFiltroServicioAsignadosCatastrofe(long idCatastrofe) throws Exception {
		List<FiltroServicio> listFiltroServicios = new ArrayList<FiltroServicio>();
		List<FiltroServicio> listAllFiltroServicios = filtroServicioDAO.listarFiltroServicios();
		FiltroServicio filtroServicio;
		Catastrofe catastrofe;
		long idCatFiltroServicio;	
		boolean bajaLogica;
		
		for (int i=0; i<=listAllFiltroServicios.size()-1; i++){
			filtroServicio = listAllFiltroServicios.get(i);
			bajaLogica = filtroServicio.isBajaLogica();
			catastrofe = filtroServicio.getCatastrofe();	
			if ((catastrofe != null) && (bajaLogica == false)) {
				idCatFiltroServicio = catastrofe.getId();				
				if (idCatFiltroServicio == idCatastrofe){					
					listFiltroServicios.add(filtroServicio);
				}					
			}										
		}						
		
		return listFiltroServicios;
	}
	
	public void EliminarFiltroServicio(Long idFiltroServicio) throws Exception{
		FiltroServicio filtroServicio = filtroServicioDAO.buscarFiltroServicioPorId(idFiltroServicio);
		filtroServicio.setBajaLogica(true);
		dataService.update(filtroServicio);			
	}
	
}
