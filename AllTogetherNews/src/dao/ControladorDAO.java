package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.IControladorDAO;
import objetosPrimarios.Administrador;
import objetosPrimarios.Medio;
import objetosPrimarios.Noticia;

public class ControladorDAO implements IControladorDAO {

	private static ControladorDAO miControladorDAO;
	private static DataSource miDS;
	private static Connection conexion;
	private static Statement oStmt;

	static {


		//pedimos el contexto de nuestro servidor
		try {
			Context ic = new InitialContext();
			//pedimos el objeto en la ruta java:comp/env/jdbc/NOMBRE_DS
			miDS = (DataSource) ic.lookup("java:comp/env/jdbc/MiDataSource");
			//cogemos la conexión
			Connection conexion=miDS.getConnection();
			
			//Ya podemos trabajar con normalidad
			oStmt = conexion.createStatement();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	private ControladorDAO()
	{

	}
	public  static ControladorDAO getControladorDAO() {

		if (miControladorDAO==null) {

			return new ControladorDAO();
		}
		return miControladorDAO;
	}




	@Override
	public void insertarMedio(Medio m) throws SQLException {

		String query="INSERT INTO MEDIOS(ID_MEDIO, HOME, ID_TEMATICA, PATRON_TITULAR, PATRON_SUBTITULAR, PATRON_LINK) VALUES ('"+m.getId_medio()+"','"+m.getTematica()+"','"+m.getPatronTitular()+"','"+m.getPatronSubtitular()+"','"+m.getPatronLink()+"')";
		oStmt.executeUpdate(query);
		oStmt.close();
		conexion.close();
	}

	@Override
	public boolean validarAdministrador(Administrador ad) throws SQLException {

		String pass="";
		System.out.println("el nombre es "+ad.getNombreAdmin());
		String query="SELECT NOMBRE,PASSWORD FROM USUARIOS WHERE NOMBRE = '"+ad.getNombreAdmin() +"'"; 
		System.out.println(query);
		ResultSet oRs = oStmt.executeQuery(query);

		while (oRs.next())
		{

			pass = oRs.getString("PASSWORD"); // El argumento es el nombre de la columna
			System.out.println(pass);
		}
		oStmt.close();
		//conexion.close();
		if(ad.getPass().equals(pass))
		{
			return true;
		}
		else
		{
			return false;
		}



	}






}
