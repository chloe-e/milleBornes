package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import cartes.Carte;

public class MainJoueur {
	private List<Carte> main;
	private int nbOperations;
	
	public void prendre(Carte c) {
		main.add(c);
		nbOperations++;
	}
	
	public void jouer(Carte c) {
		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			if(carte == c) {
				iterator.remove();
			}
		}
		nbOperations++;
	}
	
	public String toString() {
		return main.toString();
	}

	
}