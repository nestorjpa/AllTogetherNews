package Concurrencia.noticias;

import core.noticias.Core;
import utils.noticias.Medio;

public class RecorrerCoreRunnable implements Runnable {

	private Medio medioATratar;
	
	public RecorrerCoreRunnable (Medio medioATratar) {
		this.medioATratar= medioATratar;
		//
	}

	public Medio getMedioATratar() {
		return medioATratar;
	}

	public void setMedioATratar(Medio medioATratar) {
		this.medioATratar = medioATratar;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Core.coreDoOne(this.getMedioATratar());
	}

}
