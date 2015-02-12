package utils.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objetosPrimarios.Medio;
import controladorPrincipal.Controlador;

/**
 * Servlet implementation class AddNewServlet
 */
public class AddNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String patronUrl=request.getParameter("PatronUrl");
		String patronRaiz=request.getParameter("raiz");
		String patronTitular=request.getParameter("PatronTitular");
		String patronLink=request.getParameter("PatronLink");
		String patronSubtitular=request.getParameter("PatronSubtitular");
		String patronExcepcion=request.getParameter("PatronExcep");
		String comboTematica=request.getParameter("Tematica");
		String dato=request.getParameter("prueba");
		
		if (dato==null){dato="add";}
		List<String> lNoticia=new ArrayList<String>();
		List<String> lNuevo=new ArrayList<String>();
		if(dato.equals("PROBAR"))
		{
			Controlador control=Controlador.getControlador();
			control.crearConexionBD();
			//Consulto las tematicas que existen y las guardo en un array list de String para pasarselo al JSP
			try {
				
				lNuevo=control.consultarTematica();
			
			request.setAttribute("lista", lNuevo);
			Medio medio=new Medio(patronUrl,comboTematica,patronTitular,patronSubtitular,patronLink,patronExcepcion,patronRaiz);
			Document doc = Jsoup.connect(medio.getHome()).userAgent("Mozilla").get();
				Elements nodos = doc.select(medio.getPatronRaiz());
				String titular="";
				String subtitular="";
				String enlace ="";
				
				for (Element nodo : nodos) {
					
					if (medio.getPatronTitular().equals(medio.getPatronLink())){
						//patron titular y link son iguales
						Elements todosTitulares=nodo.select(medio.getPatronTitular());
						for(Element soloUnTitular :todosTitulares)
						{
							titular= soloUnTitular.ownText();
							lNoticia.add(titular);
							enlace= mascaraLink(soloUnTitular.attr("href"),medio.getHome());
							lNoticia.add(enlace);
						}
					}else{
						
						
						
					}
					Elements todosLosSubtitulares =nodo.getElementsByTag(medio.getPatronSubtitular()).not(medio.getPatronExcepcion());
					String aux="";
					for(Element soloUnSubtitular : todosLosSubtitulares)
					{
						aux=aux+soloUnSubtitular.text();
						
					}
					subtitular=aux;
					lNoticia.add(subtitular);
					
						
					}
				String prueba="";
				Iterator<String> ite = lNoticia.iterator();
				while(ite.hasNext()){
					prueba = ite.next();
				
				}
						
				request.setAttribute("listaPrueba", lNoticia);
				request.getRequestDispatcher("MenuAdmin.jsp").forward(request,response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			dato="";

		}
		else
		{
			
	
		//Me creo mi controlador para poder gestionar esta parte
		Controlador control=Controlador.getControlador();
		try {
			lNuevo=control.consultarTematica();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		request.setAttribute("lista", lNuevo);
		//Me creo una conexion de BD mediante el datasource y lo llamo desde el controlador
		control.crearConexionBD();
		
		//Me creo una instancia de medio para introducirlo en la BD
		Medio me=new Medio(patronUrl,comboTematica,patronTitular,patronSubtitular,patronLink,patronExcepcion,patronRaiz);
		
		//Inserto el medio en la BD
		
		try {
			control.insertarMedio(me);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Vuelvo al jsp para poder hacer otra inserccion o decir irme

		request.getRequestDispatcher("MenuAdmin.jsp").forward(request,response);
	}
	}
	
		public static String mascaraLink (String link, String home){
			//Se utiliza para generar el enlace de manera correcta
				if (link.startsWith("/")){
					return home+link;
				}else{
					return link;
				}
				
			}
	

	
	
}
