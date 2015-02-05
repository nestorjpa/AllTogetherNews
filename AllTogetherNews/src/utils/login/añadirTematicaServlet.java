package utils.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladorPrincipal.Controlador;

/**
 * Servlet implementation class añadirTematicaServlet
 */
public class añadirTematicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public añadirTematicaServlet() {
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
		
		//Me creo mi controlador para poder gestionar esta parte
		
		Controlador control=Controlador.getControlador();
		
		//Me creo una conexion de BD mediante el datasource y lo llamo desde el controlador
		
		control.crearConexionBD();
		
		//Consulto las tematicas que existen y las guardo en un array list de String
		List<String> lTemas=new ArrayList<String>();
		try {
			lTemas=control.consultarTematica();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("lista", lTemas);
		request.getRequestDispatcher("FormularioTematica.jsp").forward(request,response);
		
		
		
		
		
		
	}

}
