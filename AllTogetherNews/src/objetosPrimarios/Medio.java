package objetosPrimarios;

public class Medio {
	
	
	private String home;
	private String tematica;
	private String patronTitular;
	private String patronSubtitular;
	private String patronLink;
	
	
	
	public Medio(){}
	
	public Medio(String home,String id_tematica,String patronTitular,String patronSubtitular,String patronLink)
	{
		
		
		this.home=home;
		this.tematica=id_tematica;
		this.patronTitular=patronTitular;
		this.patronSubtitular=patronSubtitular;
		this.patronLink=patronLink;
		
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
	
	
	
	
	
	
	
	

}
