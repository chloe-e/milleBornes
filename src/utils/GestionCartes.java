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
	
	public T extraireIt(List<T> liste){
		int ind = rand.nextInt(liste.size() - 1);
		ListIterator<T> iterator = liste.listIterator();
		
		for (int i = 0; i < ind; i++) {
			iterator.next();
		}
		
		T elt = iterator.next();
		iterator.remove();
		
		return elt;
	}

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
		List<T> listeRassemblee = new ArrayList<>();
		listeRassemblee.add(liste.get(0));
		T cur;
		boolean trouve = false;
		
		for (int i = 1; i < liste.size(); i++) {
			cur = liste.get(i);
			trouve = false;
			for(int j=0; j<listeRassemblee.size() && !trouve; j++) {
				if(cur.equals(listeRassemblee.get(j))) {
					listeRassemblee.add(j+1,cur);
					trouve = true;
				}
			}
			
			if(!trouve) {
				listeRassemblee.add(cur);
			}
		}
		return listeRassemblee;
	}
	
	public static <T> boolean verifierRassemblement(List<T> liste) {
		T prev = liste.get(0);
		T next;
		
		for (ListIterator<T> it = liste.listIterator(); it.hasNext();) {
			next = it.next();
			if(!next.equals(prev)) {
				for (ListIterator<T> iterator2 = liste.listIterator(it.nextIndex()); iterator2.hasNext();) {
					T t = iterator2.next();
					if(t.equals(prev)) {
						return false;
					}
				}
			}
			prev = it.next();
		}
		return true;
	}
	
	

}
