package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cartes.Carte;

public class MainJoueur {
	private List<Carte> main = new ArrayList<>();
	
	public List<Carte> getMain() {
		return main;
	}
	
	public void prendre(Carte c) {
		main.add(c);
	}
	
	public void jouer(Carte c) {
		for (Iterator<Carte> iterator = main.iterator(); iterator.hasNext();) {
			Carte carte = iterator.next();
			if(carte == c) {
				iterator.remove();
			}
		}
	}
	
	public String toString() {
		return main.toString();
	}

	
}