package dao;

import java.sql.SQLException;
import java.util.List;

import objetosPrimarios.Administrador;
import objetosPrimarios.Medio;
import objetosPrimarios.Noticia;

public interface IControladorDAO {
	
	
	
	void insertarMedio(Medio m) throws SQLException;
	
	boolean validarAdministrador(Administrador ad) throws SQLException;
	
	

}