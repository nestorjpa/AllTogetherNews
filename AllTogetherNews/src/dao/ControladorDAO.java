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
	public static ControladorDAO getControladorDAO() {

		if (miControladorDAO==null) {

			return new ControladorDAO();
		}
		return miControladorDAO;
	}




	@Override
	public void insertarMedio(Medio m) throws SQLException  {

		System.out.println("Estoy en el controlador DAO");
		Connection conexion=miDS.getConnection();
		oStmt = conexion.createStatement();
	
		System.out.println("Voy a insertar "+m.getHome());
		System.out.println("Voy a insertar "+m.getPatronTitular());
		System.out.println("Voy a insertar "+m.getPatronSubtitular());
		System.out.println("Voy a insertar "+m.getTematica());
		System.out.println("Voy a insertar "+m.getPatronLink());
		String query="INSERT INTO MEDIOS(ID_MEDIO, HOME, ID_TEMATICA, PATRON_TITULAR, PATRON_SUBTITULAR, PATRON_LINK) VALUES (medios_seq.nextval,'"+m.getHome()+"',1,'"+m.getPatronTitular()+"','"+m.getPatronSubtitular()+"','"+m.getPatronLink()+"')";
		oStmt.executeUpdate(query);
		oStmt.close();
		conexion.close();
	}

	@Override
	public boolean validarAdministrador(Administrador ad) throws SQLException {

		Connection conexion=miDS.getConnection();
		
		oStmt = conexion.createStatement();
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
		conexion.close();
		if(ad.getPass().equals(pass))
		{
			return true;
		}
		else
		{
			return false;
		}



	}
	@Override
	public void insertarTematica(String tema) throws SQLException {
		
		Connection conexion=miDS.getConnection();
		oStmt = conexion.createStatement();
		String query="INSERT INTO TEMATICAS(ID_TEMATICA,NOMBRE) VALUES (tematicas_seq.nextval,'"+tema+"')";
		oStmt.executeUpdate(query);
		oStmt.close();
		conexion.close();
		
		
		
	}
	@Override
	public List<String> consultarTematica() throws SQLException {
		
		List<String> listaTematica=new ArrayList<String>();
		String tema;
		Connection conexion=miDS.getConnection();
		oStmt = conexion.createStatement();
		String nombre;
		String query="SELECT NOMBRE FROM TEMATICAS"; 
		System.out.println(query);
		ResultSet oRs = oStmt.executeQuery(query);
		while (oRs.next())
		{

			tema= oRs.getString("NOMBRE"); // El argumento es el nombre de la columna
			listaTematica.add(tema);
		}
		oStmt.close();
		conexion.close();
		
		
		
		return listaTematica;
	}
	@Override
	public List<Noticia> consultarNoticias() throws SQLException {
		//Noticia n=new Noticia();
		Medio me=new Medio();
		List<Noticia> listaNoticias=new ArrayList<Noticia>();
		Connection conexion=miDS.getConnection();
		oStmt = conexion.createStatement();
		String query="SELECT N.TITULAR,N.SUBTITULAR,N.LINK,N.FECHA_INSERCION,M.HOME FROM NOTICIAS N INNER JOIN MEDIOS M ON N.ID_MEDIO=M.ID_MEDIO"; 
		ResultSet oRs = oStmt.executeQuery(query);
		while (oRs.next())
		{
			Noticia n=new Noticia();
			n.setTitular(oRs.getString("TITULAR"));
			n.setSubti(oRs.getString("SUBTITULAR"));
			n.setLink(oRs.getString("LINK"));
			n.setFecha_inserccion(oRs.getString("FECHA_INSERCION"));
			me.setHome(oRs.getString("HOME"));
			n.setM(me);
			listaNoticias.add(n);
		}
		oStmt.close();
		conexion.close();
		return listaNoticias;
	}

	




}
