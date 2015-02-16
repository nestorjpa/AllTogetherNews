package utils.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objetosPrimarios.Noticia;
import controladorPrincipal.Controlador;

/**
 * Servlet implementation class MostrarNoticiasServlet
 */
public class MostrarNoticiasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarNoticiasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Me creo una lista Noticias para recuperar lo que devuelve la BD
		List<Noticia> lNoticia=new ArrayList<Noticia>();
		List<String> lNavegacion=new ArrayList<String>();
		//Me creo mi controlador para poder gestionar esta parte
		Controlador control=Controlador.getControlador();
							
		//Me creo una conexion de BD mediante el datasource y lo llamo desde el controlador
		control.crearConexionBD();
				
		try {
			
			//Recojo lo que devuelve la BD
			lNoticia=control.consultarNoticias();
			lNavegacion=control.consultarTematica();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		
			//Le paso la lista al JSP para poder trabajar con ella alli
			request.setAttribute("lista", lNoticia);
			request.setAttribute("lNav", lNavegacion);
			request.getRequestDispatcher("WebCliente.jsp").forward(request,response);
		
		
		
	}

}

