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

	
	public void coreDo(){

		ArrayList<Medio> listaMedios= BBDD.getMedios();
		Iterator<Medio> itrMedios= listaMedios.iterator();
		Medio currentMedio= null;
		ArrayList<Noticia> noticiasDelCurrentMedio;
		HashMap<String,ArrayList<Noticia>> todasLasNoticiasHashMap = new HashMap<String,ArrayList<Noticia>>();
		
		/*Este es el bloque al que aplicar multihilo*/
		while (itrMedios.hasNext()){
			currentMedio=itrMedios.next();
			noticiasDelCurrentMedio= getNoticiasMedio(currentMedio);
			//Se puede elegir mandar a la BBDD medio a medio, o bien crear un hashMap, hacerlos todos y mandarlas todas al final
			//Lo hacemos con un hashmap<String, Noticia>
			todasLasNoticiasHashMap.put(currentMedio.getHome(), noticiasDelCurrentMedio);
		}
		/*hasta aquí multihilo*/
		
		BBDD.insertaNoticias(todasLasNoticiasHashMap);
	}
	
	
	public ArrayList<Noticia> getNoticiasMedio(Medio medio){

		Document doc;
		ArrayList<Noticia> noticiasMedio= new ArrayList();
		ArrayList<String> arrayTitulares= new ArrayList();
		ArrayList<String> arrayLinks= new ArrayList();
		ArrayList<String> arraySubTitulares= new ArrayList();
		
		try {
			doc = Jsoup.connect(medio.getHome()).userAgent("Mozilla").get();
			
			Elements titulares = doc.select(medio.getPatronTitular());			
			for (Element titular : titulares) {
				//System.out.println("titular own: "+titular.ownText());
				arrayTitulares.add(titular.ownText());
			}
			
			Elements links = doc.select(medio.getPatronLink());			
			for (Element link : links) {
				//System.out.println("link: "+link.attr("href"));
				arrayLinks.add(link.attr("href"));
			}
			
			Elements subtitulares = doc.select(medio.getPatronSubtitular());
			
			for (Element subtitular : subtitulares) {
				//System.out.println("subtitular own: "+subtitular.ownText());
				arraySubTitulares.add(subtitular.ownText());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		noticiasMedio= crearNoticias(arrayTitulares, arrayLinks, arraySubTitulares);
		
		return noticiasMedio;	
	}

	
	public ArrayList<Noticia> crearNoticias (ArrayList<String> arrayTitulares, ArrayList<String> arrayLinks, ArrayList<String> arraySubTitulares){
		
		ArrayList<Noticia> arrayFinalNoticias= new ArrayList<Noticia>();
		Noticia noticia= new Noticia();
		int i=0;
		if ((arrayTitulares.size() == arrayLinks.size()) && (arrayTitulares.size() == arraySubTitulares.size())){
			
			while (i<arrayTitulares.size()){
				noticia = new Noticia (arrayTitulares.get(i), arrayLinks.get(i), arraySubTitulares.get(i));
				arrayFinalNoticias.add(noticia);
			}

		}else{
			System.out.println("Algo ha fallado con las inserciones, hay más titulares que subtitulares o links... No se hace nada");
		}
		
		return arrayFinalNoticias;
	}

	

	
	

	
}
