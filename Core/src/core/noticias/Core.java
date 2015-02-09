package core.noticias;

import java.io.IOException;
import java.util.ArrayList;
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
		ArrayList<Noticia> noticiaDelCurrentMedio;
		
		
		while (itrMedios.hasNext()){
			currentMedio=itrMedios.next();
			noticiaDelCurrentMedio= getNoticiasMedio(currentMedio);
			//Se puede elegir mandar a la BBDD medio a medio, o bien crear un hashMap, hacerlos todos y mandarlas todas al final
		}
		
	}
	
	
	public ArrayList<Noticia> getNoticiasMedio(Medio medio){

		Document doc;
		ArrayList<Noticia> noticiasMedio= new ArrayList();
		
		try {
			doc = Jsoup.connect(medio.getHome()).userAgent("Mozilla").get();		
			Elements links = doc.select("h2.article-header > a");
			
			for (Element link : links) {
				System.out.println("titular own: "+link.ownText());
				System.out.println("link: "+link.attr("href"));
			}
			
			Elements links2 = doc.select("div.entry-content > p");
			
			for (Element link1 : links2) {
				System.out.println("subtitular own: "+link1.ownText());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	

	

	
	

	
}
