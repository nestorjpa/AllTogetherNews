package objetosPrimarios;

public class Administrador {
	
	private int id_Admin;
	private String nombreAdmin;
	private String pass;
	
	
	public Administrador(){};
	
	public Administrador(int id,String nomb,String pass)
	{
		id_Admin=id;
		nombreAdmin=nomb;
		this.pass=pass;
	}
	
	
	public int getId_Admin() {
		return id_Admin;
	}
	public void setId_Admin(int id_Admin) {
		this.id_Admin = id_Admin;
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
