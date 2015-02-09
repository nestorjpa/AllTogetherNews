package utils.noticias;

public class Noticia {

	private String titular;
	private String link;
	private String subtitular;
	
	public Noticia() {
		this.titular = "";
		this.link = "";
		this.subtitular = "";
	}
	
	public Noticia(String titular, String link, String subtitular) {
		this.titular = titular;
		this.link = link;
		this.subtitular = subtitular;
	}

	public String getTitular() {
		return titular;
	}
	
	public void setTitular(String titular) {
		this.titular = titular;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getSubtitular() {
		return subtitular;
	}
	
	public void setSubtitular(String subtitular) {
		this.subtitular = subtitular;
	}

}
