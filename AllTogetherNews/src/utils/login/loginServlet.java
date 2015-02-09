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
		// TODO Auto-generated method stub
		String nomb=request.getParameter("text");
		String pass=request.getParameter("password");
		String usu=null;
		String passw=null;
		String id=null;
		//Consultar en la bd nombre y pass
		
		//instanciar el driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//Componer la url
			String sURL="jdbc:oracle:thin:@192.168.150.199:1521:orcl";
			
			
			Connection conexion = DriverManager.getConnection(sURL, "curso11","curso");            
			
			Statement sentencia=conexion.createStatement();
			//resultSet
			String sSQL="SELECT NOMBRE, PASSWORD, ID_ROLE FROM USUARIOS";
			 ResultSet oRS=sentencia.executeQuery(sSQL);
			while(oRS.next()){
				
				 usu=oRS.getString("Nombre");
				 passw=oRS.getString("Password");
				 id=oRS.getString("Id_ROLE");
				
			}
			
			if(nomb.equals(usu)&&(pass.equals(passw)&&(id.equals("1")))){
				HttpSession sesion=request.getSession();
				sesion.setAttribute("usuario", nomb);
				
				request.getRequestDispatcher("FormularioAddNews.jsp").forward(request,response);
			}
			
			
			else
			{
				request.getRequestDispatcher("Login.jsp").forward(request,response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		

}
