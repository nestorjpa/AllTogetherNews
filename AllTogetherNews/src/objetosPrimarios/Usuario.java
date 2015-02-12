package objetosPrimarios;

import java.util.Observable;




public class Usuario  extends Observable {
	
	public static int numUsuarios=0;
	private String nombre;
	
	public Usuario(){
		
       
		numUsuarios++;
		this.notifyObservers();
		 this.setChanged();
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
