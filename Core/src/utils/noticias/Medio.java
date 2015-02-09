package utils.noticias;

public class Medio {

	private String home;
	private String patronTitular;
	private String patronLink;
	private String patronSubtitular;
	
	public Medio() {
		this.home = "";
		this.patronTitular = "";
		this.patronLink = "";
		this.patronSubtitular = "";
	}
	
	public Medio(String home, String patronTitular, String patronLink, String patronSubtitular) {
		this.home = home;
		this.patronTitular = patronTitular;
		this.patronLink = patronLink;
		this.patronSubtitular = patronSubtitular;
	}

	public String getHome() {
		return home;
	}
	
	public void setHome(String home) {
		this.home = home;
	}
	
	public String getPatronTitular() {
		return patronTitular;
	}
	
	public void setPatronTitular(String patronTitular) {
		this.patronTitular = patronTitular;
	}
	
	public String getPatronLink() {
		return patronLink;
	}
	
	public void setPatronLink(String patronLink) {
		this.patronLink = patronLink;
	}
	
	public String getPatronSubtitular() {
		return patronSubtitular;
	}
	
	public void setPatronSubtitular(String patronSubtitular) {
		this.patronSubtitular = patronSubtitular;
	}
}
