package dao;

import java.sql.SQLException;
import java.util.List;

import objetosPrimarios.Administrador;
import objetosPrimarios.Medio;
import objetosPrimarios.Noticia;

public interface IControladorDAO {
	
	
	void insertarNoticia(Noticia n) throws SQLException;
	void insertarMedio(Medio m) throws SQLException;
	List extraerPatronesMedio() throws SQLException;
	boolean validarAdministrador(Administrador ad) throws SQLException;
	
	

}