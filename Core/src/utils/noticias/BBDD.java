package utils.noticias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BBDD {

	private static final String url = "jdbc:oracle:thin:@192.168.150.199:1521:ORCL";
	private static final String user = "curso11";
	private static final String pass = "curso";
	
	private static Connection abrirConexion(){
		
		Connection oCn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    try {
			oCn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return oCn;
	}
	
	private static void cerrarConexion(Connection c){
		try {
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Medio> getMedios (){
		
		Connection conexion;
		String sql;
		Statement stmt;
		ResultSet resultados;
		Medio medio= new Medio();
		ArrayList<Medio> listaMedios= new ArrayList();
		
		conexion = abrirConexion();	
	    sql="select home, patron_titular, patron_link, patron_subtitular from medios";
		try {
			stmt = conexion.createStatement();
			resultados = stmt.executeQuery(sql);

			String home,patronTitular,patronLink, patronSubtitular;
	   
			while (resultados.next()){
				home= resultados.getString("home");
				patronTitular= resultados.getString("patron_titular");
				patronLink= resultados.getString("patron_link");
				patronSubtitular= resultados.getString("patron_subtitular");
				medio = new Medio (home, patronTitular, patronLink, patronSubtitular);
				listaMedios.add(medio);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		cerrarConexion(conexion);
		
		return listaMedios;		
	}
	
	
}
