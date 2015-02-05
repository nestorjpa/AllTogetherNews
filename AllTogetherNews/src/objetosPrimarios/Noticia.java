package objetosPrimarios;

import java.util.Date;

public class Noticia {
	
	private int id;
	private String titular;
	private String subti;
	private String link;
	private Date fecha_inserccion;
	private Medio m;
	
	
	public Noticia()
	{}
	
	public Noticia(int id,String titular,String subti,String link,Date fecha_inserccion,Medio m)
	{
		this.id=id;
		this.titular=titular;
		this.subti=subti;
		this.link=link;
		this.fecha_inserccion=fecha_inserccion;
		this.setM(m);
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getSubti() {
		return subti;
	}
	public void setSubti(String subti) {
		this.subti = subti;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getFecha_inserccion() {
		return fecha_inserccion;
	}
	public void setFecha_inserccion(Date fecha_inserccion) {
		this.fecha_inserccion = fecha_inserccion;
	}

	public Medio getM() {
		return m;
	}

	public void setM(Medio m) {
		this.m = m;
	}
	
	

}
