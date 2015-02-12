package objetosPrimarios;

public class Usuario {
	
	public static int numUsuarios=0;
	private String nombre;
	
	public Usuario(){
		numUsuarios++;
	}
	
	public Usuario(String nombre)
	{
		this.setNombre(nombre);
		numUsuarios++;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
