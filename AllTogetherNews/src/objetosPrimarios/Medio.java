package objetosPrimarios;

public class Medio {
	
	
	private String home;
	private String tematica;
	private String patronRaiz;
	private String patronTitular;
	private String patronSubtitular;
	private String patronLink;
	private String patronExcepcion;
	
	
	
	public Medio(){}
	
	public Medio(String home,String id_tematica,String patronTitular,String patronSubtitular,String patronLink,String patronExcepcion,String patronRaiz)
	{
		
		
		this.home=home;
		this.tematica=id_tematica;
		this.patronTitular=patronTitular;
		this.patronSubtitular=patronSubtitular;
		this.patronLink=patronLink;
		this.setPatronExcepcion(patronExcepcion);
		this.setPatronRaiz(patronRaiz);
		
	}
	
	
	
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getPatronTitular() {
		return patronTitular;
	}
	public void setPatronTitular(String patronTitular) {
		this.patronTitular = patronTitular;
	}
	public String getPatronSubtitular() {
		return patronSubtitular;
	}
	public void setPatronSubtitular(String patronSubtitular) {
		this.patronSubtitular = patronSubtitular;
	}
	public String getPatronLink() {
		return patronLink;
	}
	public void setPatronLink(String patronLink) {
		this.patronLink = patronLink;
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
