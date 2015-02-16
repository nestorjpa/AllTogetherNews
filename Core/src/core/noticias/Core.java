package core.noticias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.noticias.BBDD;
import utils.noticias.Medio;
import utils.noticias.Noticia;

public class Core {

	
	public static void coreDoAll(){
	//Esta función se utilizaría para lanzar todo el proceso de manera secuencial y no en paralelo
	//En el funcionamiento típico no se utiliza
		ArrayList<Medio> listaMedios= BBDD.getMedios();
		Iterator<Medio> itrMedios= listaMedios.iterator();
		Medio currentMedio= null;
		ArrayList<Noticia> noticiasDelCurrentMedio;
		HashMap<String,ArrayList<Noticia>> todasLasNoticiasHashMap = new HashMap<String,ArrayList<Noticia>>();
		
		//Este es el bloque al que aplicar multihilo
		while (itrMedios.hasNext()){
			currentMedio=itrMedios.next();
			noticiasDelCurrentMedio= getNoticiasMedio(currentMedio);
			//Se puede elegir mandar a la BBDD medio a medio, o bien crear un hashMap, hacerlos todos y mandarlas todas al final
			//Lo hacemos con un hashmap<String, Noticia>
			todasLasNoticiasHashMap.put(currentMedio.getHome(), noticiasDelCurrentMedio);
		}
		//hasta aquí multihilo
		
		BBDD.insertaNoticias(todasLasNoticiasHashMap);
	}

	
	public static void coreDoOne(Medio medio){
	//Esta función es la llamada en cada proceso paralelo y la que inicia la carga
		//Para reutilizar, utilizamos los mismo que tenemos, solamente que el hashMap tendrá simplemente una sola fila
		HashMap<String,ArrayList<Noticia>> todasLasNoticiasHashMap = new HashMap<String,ArrayList<Noticia>>();
		ArrayList<Noticia> noticiasDelCurrentMedio = getNoticiasMedio(medio);
		if (!noticiasDelCurrentMedio.isEmpty()){
			todasLasNoticiasHashMap.put(medio.getHome(), noticiasDelCurrentMedio);
			BBDD.insertaNoticias(todasLasNoticiasHashMap);
		}else {
			System.out.println("No hay noticias de "+medio.getHome()+" que cumplan con los patrones");
		}

	}
	
	
	public static ArrayList<Noticia> getNoticiasMedio(Medio medio){
	//Se utiliza para trocear los campos que queremos de un medio en concreto
		Document doc;
		ArrayList<Noticia> noticiasMedio= new ArrayList<Noticia>();
		ArrayList<String> arrayTitulares= new ArrayList<String>();
		ArrayList<String> arrayLinks= new ArrayList<String>();
		ArrayList<String> arraySubTitulares= new ArrayList<String>();
		
		try {
			doc = Jsoup.connect(medio.getHome()).userAgent("Mozilla").get();
			
			/*Elements titulares = doc.select(medio.getPatronTitular());			
			for (Element titular : titulares) {
				//System.out.println("titular own: "+titular.ownText());
				arrayTitulares.add(titular.ownText());
			}
			
			String linkAux;
			Elements links = doc.select(medio.getPatronLink());			
			for (Element link : links) {
				//System.out.println("link: "+link.attr("href"));
				linkAux = mascaraLink(link.attr("href"), medio.getHome());
				arrayLinks.add(linkAux);
			}
			
			Elements subtitulares = doc.select(medio.getPatronSubtitular());
			
			for (Element subtitular : subtitulares) {
				//System.out.println("subtitular own: "+subtitular.ownText());
				arraySubTitulares.add(subtitular.select("p").first().text());
			}
			*/
			
			Elements nodos = doc.select(medio.getPatronRaiz());
			String titular;
			String subtitular;
			String enlace;
			
			for (Element nodo : nodos) {
			
			titular=" ";
			subtitular=" ";
			enlace =" ";
				
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
				String aux=" ";
				for(Element soloUnSubtitular : todosLosSubtitulares)
				{
					aux=aux+soloUnSubtitular.text();
					
				}
				subtitular=aux;

				//Ahora se crea la noticia y se añade al array de Noticias
				
				if (!titular.equals(" ") && !enlace.equals(" ")){
					if (!subtitular.equals(" ")){
						if (subtitular.length()>1000){
							subtitular=subtitular.substring(0, 999);
						}
					}
				noticiasMedio.add(new Noticia(titular, enlace, subtitular));
				}
				
			}//fin del recorrido
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//noticiasMedio= crearNoticias(arrayTitulares, arrayLinks, arraySubTitulares);
		
		return noticiasMedio;	
	}

	
	public static ArrayList<Noticia> crearNoticias (ArrayList<String> arrayTitulares, ArrayList<String> arrayLinks, ArrayList<String> arraySubTitulares){
	//DEPRECATED
	//Se utiliza para generar el array de todas las noticias de un medio, juntando los titulares, subtitlares y enlaces
	//Habría que pensar otra forma de hacerlo	
		ArrayList<Noticia> arrayFinalNoticias= new ArrayList<Noticia>();
		Noticia noticia= new Noticia();
		int i=0;
		if ((arrayTitulares.size() == arrayLinks.size()) && (arrayTitulares.size() == arraySubTitulares.size())){
			
			while (i<arrayTitulares.size()){
				noticia = new Noticia (arrayTitulares.get(i), arrayLinks.get(i), arraySubTitulares.get(i));
				arrayFinalNoticias.add(noticia);
				i++;
			}

		}else{
			System.out.println("Algo ha fallado con las inserciones, hay más titulares que subtitulares o links... No se hace nada");
		}
		
		return arrayFinalNoticias;
	}

	public static String mascaraLink (String link, String home){
	//Se utiliza para generar el enlace de manera correcta
		if (link.startsWith("/")){
			return home+link;
		}else{
			return link;
		}
		
	}

	public static ArrayList<Medio> getTodosLosMediosDeLaBBDD(){
		return BBDD.getMedios();
	}
	

}
