package dao;

import java.sql.SQLException;
import java.util.List;

import objetosPrimarios.Administrador;
import objetosPrimarios.Medio;
import objetosPrimarios.Noticia;


public interface IControladorDAO {
	
	
	
	 void insertarMedio(Medio m) throws SQLException;
	
	 boolean validarAdministrador(Administrador ad) throws SQLException;

	void insertarTematica(String tema) throws SQLException;

	List<String> consultarTematica() throws SQLException;

	List<Noticia> consultarNoticias() throws SQLException;
	
	

}