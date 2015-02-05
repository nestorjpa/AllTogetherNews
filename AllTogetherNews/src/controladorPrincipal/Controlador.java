package controladorPrincipal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ControladorDAO;
import dao.IControladorDAO;
import objetosPrimarios.Administrador;
import objetosPrimarios.Medio;
import objetosPrimarios.Noticia;
public class Controlador {

	
	private List<Medio> listaMedios=new ArrayList<Medio>();
	private static Controlador miControladorPrincipal;
	private static IControladorDAO miControladorDAO;
	
	private Controlador(){}
	
	public  static Controlador getControlador() {
		 
		 if (miControladorPrincipal==null) {
		 
		return new Controlador();
		 }
		 return miControladorPrincipal;
		 }
	
	
	public void crearConexionBD(){
		
		miControladorDAO=ControladorDAO.getControladorDAO();
	}

	

		public boolean validarAdmin(Administrador ad) throws SQLException {
		return miControladorDAO.validarAdministrador(ad);
		
		}

		
}