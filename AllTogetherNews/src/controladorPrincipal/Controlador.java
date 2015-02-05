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

		public void insertarMedio(Medio me) throws SQLException {
			System.out.println("Estoy en el controlador");
			miControladorDAO.insertarMedio(me);
			
		}

		public void insertarTematica(String tema) throws SQLException {
			
			miControladorDAO.insertarTematica(tema);
			
		}

		public List<String> consultarTematica() throws SQLException {
			
			
			return miControladorDAO.consultarTematica();
			
		}

		public  List<Noticia> consultarNoticias() throws SQLException {
			
			return miControladorDAO.consultarNoticias();
		}

		
}