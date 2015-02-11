package principal.noticias;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Concurrencia.noticias.RecorrerCoreRunnable;
import utils.noticias.Medio;
import core.noticias.Core;

public class Principal {

	private static final int NUM_HILOS= 5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Core.cMedioAll();
		
		ArrayList<Medio> arrayMedios = Core.getTodosLosMediosDeLaBBDD();
		
		ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);		
		Iterator<Medio> itrMedio = arrayMedios.iterator();
		Medio medio;
		
		while (itrMedio.hasNext()){
			medio = itrMedio.next();
			executor.execute(new RecorrerCoreRunnable(medio));
		}

		executor.shutdown();
	}

}
