package utils.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import objetosPrimarios.Usuario;
import controladorPrincipal.Controlador;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet implements Observer {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
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
		int numUsus;
		
		List<String> lTemas=new ArrayList<String>();
		//Me creo mi controlador para poder gestionar esta parte
		Controlador control=Controlador.getControlador();
				
		//Me creo una conexion de BD mediante el datasource y lo llamo desde el controlador
		control.crearConexionBD();
		//Consulto las tematicas que existen y las guardo en un array list de String para pasarselo al JSP
		try {
			
			lTemas=control.consultarTematica();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("lista", lTemas);
		request.getRequestDispatcher("MenuAdmin.jsp").forward(request,response);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

}
