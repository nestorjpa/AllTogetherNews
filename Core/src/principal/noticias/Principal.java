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

		//Esta llamada es para cuando se quiera ejecutar secuancial
		//Core.coreDoAll();
		
		//A partir de aquí cuando se quiera en paralelo
		
		ArrayList<Medio> arrayMedios = Core.getTodosLosMediosDeLaBBDD();
		
		ExecutorService executor = Executors.newFixedThreadPool(NUM_HILOS);		
		Iterator<Medio> itrMedio = arrayMedios.iterator();
		Medio medio=null;
		
		//Se tiene un pool de hilos y se añaden los nuevos medios a tratar
		//Es el executorService quien se encarga de la gestión
		//A medida que van finalizando los procesos va llamando a nuevos
		while (itrMedio.hasNext()){
			medio = itrMedio.next();
			executor.execute(new RecorrerCoreRunnable(medio));
		}

		executor.shutdown();		
	}

}
