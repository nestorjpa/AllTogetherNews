package utils.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objetosPrimarios.Administrador;
import controladorPrincipal.Controlador;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		boolean validacion;
		String nomb=request.getParameter("user");
		String pass=request.getParameter("pass");
		String usu=null;
		String passw=null;
		//Me creo mi controlador para poder gestionar esta parte
		Controlador control=Controlador.getControlador();
		
		//Me creo una conexion de BD mediante el datasource y lo llamo desde el controlador
		control.crearConexionBD();
		//Me creo una instancia de administrador con lo que he introducido
		Administrador ad=new Administrador(nomb,pass);
		//Llamo a validar administrador para que me compare lo que he introducido
		
		try {
			if(control.validarAdmin(ad))
			{
				HttpSession sesion=request.getSession();
				sesion.setAttribute("usuario", nomb);
				
				request.getRequestDispatcher("MenuAdmin.jsp").forward(request,response);
			}
			
			else
			{
				
			validacion=false;
			request.setAttribute("validacion", false);
			request.getRequestDispatcher("Login.jsp").forward(request,response);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
		

}
