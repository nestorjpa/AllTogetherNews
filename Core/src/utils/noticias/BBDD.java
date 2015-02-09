package utils.noticias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	
	public static void insertaNoticias(HashMap<String,ArrayList<Noticia>> todasLasNoticiasHashMap){
		
		Iterator<String> itrHashMap = todasLasNoticiasHashMap.keySet().iterator();
		String clave;
		ArrayList<Noticia> arrayNoticiasMedio;
		ArrayList<Noticia> arrayUltimasNoticias;
		while (itrHashMap.hasNext()){
			clave= itrHashMap.next();
			//La clave es home, la url del site
			arrayNoticiasMedio= todasLasNoticiasHashMap.get(clave);
			arrayUltimasNoticias= comprobarUltimasNoticias (arrayNoticiasMedio, clave);	
		}
		
	}
	
	public static ArrayList<Noticia> comprobarUltimasNoticias (ArrayList<Noticia> arrayNoticiasMedio, String medio){
		//Mira en la BBDD las noticias y crea un arrayList con las que son diferentes
		//Una noticia es igual si tiene el mismo titular
		
		Connection conexion;
		String sql;
		Statement stmt;
		ResultSet resultados;
		Noticia noticia= new Noticia();
		ArrayList<String> listaTitularesEnLaBBDD= new ArrayList();
		
		conexion = abrirConexion();	
	    sql="select titular from noticias";
		try {
			stmt = conexion.createStatement();
			resultados = stmt.executeQuery(sql);

			String titular;
			while (resultados.next()){
				titular= resultados.getString("titular");
				listaTitularesEnLaBBDD.add(titular);
			}
		
		//Crear dos iteradores para recorrer la lista arrayNoticiasMedio y arrayTitularesEnLaBBDD
		//para comprobar si son iguales o distintos. Los distitos se agregan
		
		Iterator<Noticia> itrNoticiasMedio = arrayNoticiasMedio.iterator();
		Iterator<String> itrTitularesEnLaBBDD = listaTitularesEnLaBBDD.iterator();
		Noticia noticiaAux;
		String titularAux;
		ArrayList<Noticia> arrayAux= new ArrayList<Noticia>();
		
		while (itrNoticiasMedio.hasNext()){
			noticiaAux= itrNoticiasMedio.next();
			while (itrTitularesEnLaBBDD.hasNext()){
				titularAux= itrTitularesEnLaBBDD.next();
				if (noticiaAux.getTitular().equals(titularAux)){
					//No insertar
					break; //El while interno
				}else{
					//No es igual, mirar el siguiente
				}				
			}
			
		}
		
		
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		cerrarConexion(conexion);
		
		
		
	}
	
	
	
}

























