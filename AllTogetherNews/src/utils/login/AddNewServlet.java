package utils.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//Me creo mi controlador para poder gestionar esta parte
		Controlador control=Controlador.getControlador();
				
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
