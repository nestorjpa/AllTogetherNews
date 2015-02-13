package utils.noticias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BBDD {

	private static final String oracleDriver = "oracle.jdbc.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@192.168.150.199:1521:ORCL";
	private static final String user = "curso11";
	private static final String pass = "curso";
	
	private static Connection abrirConexion(){
	//Abrir conexión a la BBDD	
		Connection oCn=null;
		try {
			Class.forName(oracleDriver);
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
	//Cerrar conexión a la BBDD
		try {
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Medio> getMedios (){
	//Coge todos los medios que hay en la BBDD y los devuelve en un arrayList	
		Connection conexion;
		String sql;
		Statement stmt;
		ResultSet resultados;
		Medio medio= new Medio();
		ArrayList<Medio> listaMedios= new ArrayList<Medio>();
		
		conexion = abrirConexion();	
	    sql="select home, patron_titular, patron_link, patron_subtitular, patron_raiz, patron_excepcion from medios";
		try {
			stmt = conexion.createStatement();
			resultados = stmt.executeQuery(sql);

			String home="";
			String patronTitular="";
			String patronLink="";
			String patronSubtitular="";
			String patronRaiz="";
			String patronExcepcion="";
	   
			while (resultados.next()){
				home= resultados.getString("home");
				patronTitular= resultados.getString("patron_titular");
				patronLink= resultados.getString("patron_link");
				patronSubtitular= resultados.getString("patron_subtitular");
				patronRaiz= resultados.getString("patron_raiz");
				patronRaiz= resultados.getString("patron_excepcion");
				medio = new Medio (home, patronTitular, patronLink, patronSubtitular, patronRaiz, patronExcepcion);
				listaMedios.add(medio);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		cerrarConexion(conexion);
		
		return listaMedios;		
	}
	
	public static void insertaNoticias(HashMap<String,ArrayList<Noticia>> todasLasNoticiasHashMap){
	//La función que se encarga de gestionar la inserción en la BBDD, llama a otras para estructurar el código	
		Iterator<String> itrHashMap = todasLasNoticiasHashMap.keySet().iterator();
		String clave;
		ArrayList<Noticia> arrayNoticiasMedio;
		ArrayList<Noticia> arrayUltimasNoticias;
		while (itrHashMap.hasNext()){
			clave= itrHashMap.next();
			//La clave es home, la url del site
			arrayNoticiasMedio= todasLasNoticiasHashMap.get(clave);
			arrayUltimasNoticias= comprobarUltimasNoticias (arrayNoticiasMedio, clave);
			publicarNoticias(arrayUltimasNoticias, clave);
		}
		
	}
	
	public static ArrayList<Noticia> comprobarUltimasNoticias (ArrayList<Noticia> arrayNoticiasMedio, String home){
	//Mira en la BBDD las noticias y crea un arrayList con las que son diferentes
	//Una noticia es igual si tiene el mismo titular
		
		Connection conexion;
		String sql;
		Statement stmt;
		ResultSet resultados;
		HashSet<String> listaTitularesEnLaBBDD= new HashSet<String>();
		ArrayList<Noticia> arrayAux = new ArrayList<Noticia>();
		
		conexion = abrirConexion();	
	    sql="select n.titular from noticias n, medios m where (n.id_medio= m.id_medio) AND (m.home ='"+home+"')";
		try {
			stmt = conexion.createStatement();
			resultados = stmt.executeQuery(sql);

			String titular;
			while (resultados.next()){
				titular= resultados.getString("titular");
				listaTitularesEnLaBBDD.add(titular);
			}
		
		Iterator<Noticia> itrNoticiasMedio = arrayNoticiasMedio.iterator();
		Noticia noticiaAux;
				
		while (itrNoticiasMedio.hasNext()){
			noticiaAux= itrNoticiasMedio.next();
			if (!listaTitularesEnLaBBDD.contains(noticiaAux.getTitular())){
				arrayAux.add(noticiaAux);
			}			
		}		
		//En arrayAux están las noticias nuevas, las que hay que subir a la BBDD

		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		cerrarConexion(conexion);

		return arrayAux;		
	}
	
	
	public static void publicarNoticias (ArrayList<Noticia> noticiasAPublicar, String home){
	//Se escriben las nuevas noticias en la BBDD	
		Connection conexion;
		String sql;
		PreparedStatement pstmt;
		conexion = abrirConexion();
	    int idMedio= conocerIdMedio(home);

		conexion = abrirConexion();	

		sql="insert into noticias (id_noticia, titular, subtitular, link, fecha_insercion, id_medio) values (noticias_seq.nextval,?,?,?,to_date(?,'DD-MM-YYYY HH24:MI'),"+idMedio+")";

	    try {
			pstmt = conexion.prepareStatement(sql);
			
			for (int i=0; i<noticiasAPublicar.size();i++){
				//Recorremos todas las noticias y las insertamos
				pstmt.setString(1, noticiasAPublicar.get(i).getTitular());
				pstmt.setString(2, noticiasAPublicar.get(i).getSubtitular());
				pstmt.setString(3, noticiasAPublicar.get(i).getLink());
				pstmt.setString(4, momentoFechaActual ());
				pstmt.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
			
	    cerrarConexion(conexion);
	}
	
	public static int conocerIdMedio (String home){
	//Para conocer el idMedio de un home dado
		Connection conexion;
		String sql;
		Statement stmt;
		ResultSet resultados;
		int idMedio=1;
		
		conexion = abrirConexion();	
	    sql="select id_medio from medios where home ='"+home+"'";
		try {
			stmt = conexion.createStatement();
			resultados = stmt.executeQuery(sql);

			while (resultados.next()){
				idMedio = resultados.getInt("id_medio");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		cerrarConexion(conexion);

		return idMedio;
	}
	
	
	public static String momentoFechaActual (){
	//Te crea la fecha actual y te la devuelve en un String
	//('12-31-2007 12:15','MM-DD-YYYY HH:MI');
		Calendar cal= Calendar.getInstance();
		
		String dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String mes = String.valueOf((cal.get(Calendar.MONTH)+1));
		String ano = String.valueOf(cal.get(Calendar.YEAR));
		String hora = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String min = String.valueOf(cal.get(Calendar.MINUTE));
				
		String fecha= dia+"-"+mes+"-"+ano+" "+hora+":"+min;
		return fecha;
	}

}

























