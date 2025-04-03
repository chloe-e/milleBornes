package utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import cartes.Carte;

public class GestionCartes <T> {
	
	private static Random rand = new Random();
	
	public static <T> T extraire(List<T> liste){
		int size = liste.size();
		int ind = rand.nextInt(size-1);

		T element = liste.get(ind);
		
		for (int i = ind; i < size-1; i++) {
			liste.set(i,liste.get(i+1));
		}
		liste.set(size-1,null);
		
		return element;
	}
	
//	public T extraireIt(T[] liste){
//		int ind = rand.nextInt(liste.length-1);
//		ListIterator<T> iterator = liste.listIterator();
//		for (int i = 0; i < ind; i++) {
//			iterator.next();
//		}
//		iterator.remove();
//	}

	public static <T> List<T> melanger(List<T> liste) {
		List<T> liste2 = liste;
		T elt;
		
		for(int i=liste.size()-1; i>0; i--) {
			elt = extraire(liste);
			liste = liste.subList(0,i);
			liste2.set(i,elt);
		}
		
		return liste2;
	}
	
	public static <T> boolean verifierMelange(List<T> listeInit, List<T> listeMelangee) {
		Set<T> uniqueCarte = new HashSet<>(listeInit);

		for(T e : uniqueCarte) {
			if (Collections.frequency(listeInit, e) != Collections.frequency(listeMelangee, e)) {
				return false;
			}
		}
		return true;
	}
	
	
	
	public static <T> List<T> rassembler(List<T> liste){
		NavigableMap<Integer,T> cartes = new TreeMap<>();
		for (T t : liste) {
			cartes.put(t.hashCode(), t);
		}
		return new ArrayList<>(cartes.values());
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		T prev = liste.get(0);
		T next = prev;
		Iterator<T> iterator = liste.iterator();
		iterator.next();
		for (Iterator<T> it = iterator; iterator.hasNext();) {
			next = it.next();
			if(next != prev) {
				for (Iterator<T> iterator2 = liste.iterator(); iterator2.hasNext();) {
					T t = iterator2.next();
					if(t == prev) {
						return false;
					}
				}
			}
			prev = it.next();
		}
		return true;
	}
	
	

}
