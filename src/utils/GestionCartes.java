package utils;
import java.util.Iterator;
import java.util.Random;

public class GestionCartes <T> {
	
	public T extraire(T[] liste){
		Random rand = new Random();
		int ind = rand.nextInt(liste.length-1);
		T element = liste[ind];
		liste[ind] = null;
		return element;
	}
	
	public T extraireIt(T[] liste){
		Random rand = new Random();
		int ind = rand.nextInt(liste.length-1);
		Iterator<T> iterator = new Iterator();
		for (int i = 0; i < ind; i++) {
			iterator.next();
		}
		iterator.remove();
	}
	
	public T[] melanger(T[] liste) {
		
	}
}
