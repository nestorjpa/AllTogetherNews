package objetosPrimarios;

public class Administrador {
	

	private String nombreAdmin;
	private String pass;

	
	public Administrador(){};
	
	
	
	
	public Administrador(String nomb, String pass) {
		this.nombreAdmin=nomb;
		this.pass=pass;
	}

	public String getNombreAdmin() {
		return nombreAdmin;
	}
	public void setNombreAdmin(String nombreAdmin) {
		this.nombreAdmin = nombreAdmin;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}
