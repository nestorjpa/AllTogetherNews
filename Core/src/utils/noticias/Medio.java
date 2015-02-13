package utils.noticias;

public class Medio {

	private String home;
	private String patronTitular;
	private String patronLink;
	private String patronSubtitular;
	private String patronRaiz;
	private String patronExcepcion;
	
	public Medio() {
		this.home = "";
		this.patronTitular = "";
		this.patronLink = "";
		this.patronSubtitular = "";
		this.patronRaiz="";
		this.patronExcepcion="";
	}
	
	public Medio(String home, String patronTitular, String patronLink, String patronSubtitular, String patronRaiz, String patronExcepcion) {
		this.home = home;
		this.patronTitular = patronTitular;
		this.patronLink = patronLink;
		this.patronSubtitular = patronSubtitular;
		this.patronRaiz = patronRaiz;
		this.patronExcepcion = patronExcepcion;
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
	
	public String getPatronRaiz() {
		return patronRaiz;
	}

	public void setPatronRaiz(String patronRaiz) {
		this.patronRaiz = patronRaiz;
	}

	public String getPatronExcepcion() {
		return patronExcepcion;
	}

	public void setPatronExcepcion(String patronExcepcion) {
		this.patronExcepcion = patronExcepcion;
	}

}
