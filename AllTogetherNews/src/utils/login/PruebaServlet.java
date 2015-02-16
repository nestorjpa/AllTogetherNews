package utils.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objetosPrimarios.Medio;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objetosPrimarios.Noticia;

/**
 * Servlet implementation class PruebaServlet
 */
public class PruebaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaServlet() {
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
	
		String patronUrl=request.getParameter("PatronUrl");
		String patronRaiz=request.getParameter("raiz");
		String patronTitular=request.getParameter("PatronTitular");
		String patronLink=request.getParameter("PatronLink");
		String patronSubtitular=request.getParameter("PatronSubtitular");
		String patronExcepcion=request.getParameter("PatronExcep");
		String comboTematica=request.getParameter("Tematica");
		Medio medio=new Medio(patronUrl,comboTematica,patronTitular,patronSubtitular,patronLink,patronExcepcion,patronRaiz);
		try {
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
						enlace= mascaraLink(soloUnTitular.attr("href"),medio.getHome());
					}
				}else{
					//No son iguales
					
				}

				Elements todosLosSubtitulares =nodo.getElementsByTag(medio.getPatronSubtitular()).not(medio.getPatronExcepcion());
				String aux="";
				for(Element soloUnSubtitular : todosLosSubtitulares)
				{
					aux=aux+soloUnSubtitular.text();
					
				}
				subtitular=aux;

				//Ahora se crea la noticia y se añade al array de Noticias
				List<String> lNoticia=new ArrayList<String>();
				
				lNoticia.add(titular);
				lNoticia.add(enlace);
				lNoticia.add(subtitular);
				
				request.setAttribute("listaPrueba", lNoticia);
				request.getRequestDispatcher("MenuAdmin.jsp").forward(request,response);
				
				System.out.println("titular : "+titular);
				System.out.println("enlace :"+enlace);
				System.out.println("subtitular :"+subtitular);
				
				
				
				
			}//fin del recorrido
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
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
