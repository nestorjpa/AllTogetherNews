package utils.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladorPrincipal.Controlador;

/**
 * Servlet implementation class InsertarTemaServlet
 */
public class InsertarTemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarTemaServlet() {
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
		
		String tema=request.getParameter("tematica");
		System.out.println("recojo esta tematica "+tema);
		
		//Me creo mi controlador para poder gestionar esta parte
		Controlador control=Controlador.getControlador();
						
		//Me creo una conexion de BD mediante el datasource y lo llamo desde el controlador
		control.crearConexionBD();
		
		//Inserto el tema en la BD
		try {
			control.insertarTematica(tema);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Vuelvo al menu
		System.out.println("he insertado "+tema);
		request.getRequestDispatcher("MenuServlet").forward(request,response);
	}

}
