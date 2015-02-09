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
		String url=request.getParameter("text");
		String patronUrl=request.getParameter("Patrón Url");
		String patronNoticia=request.getParameter("Patrón Noticia");
		String patronTitular=request.getParameter("PatrónTitular");
		String patronSubtitular=request.getParameter("PatrónSubtitular");
		String comboTematica=request.getParameter("comboTematica");
		
		//insertar en la BBDD los datos
		
		//instanciar el driver
		
			
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				//Componer la url
				String sURL="jdbc:oracle:thin:@192.168.150.199:1521:orcl";
				
					Connection conexion = DriverManager.getConnection(sURL, "curso11","curso");            
							
					Statement sentencia=conexion.createStatement();
				
				
				String sSQL="INSERT INTO  MEDIOS VALUES('url', 'patronUrl',' patronNoticia','patronTitular','patronSubtitular','comboTematica')";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		request.getRequestDispatcher("FormularioAddNews.jsp").forward(request,response);
	}

}
