package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes;
	private int nbCartes;
	private int nbOperations = 0;
	

	public Sabot(Carte[] cartes) {
		super();
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	
	public void ajouterCarte(Carte carte){
		if (nbCartes == cartes.length) {
			throw new IllegalStateException();
		}
		else {
			cartes[nbCartes] = carte;
			nbCartes++;
			nbOperations++;
		}
	}
	
	public Carte piocher() {
		Iterateur iter = new Iterateur();
		Carte c = null;
		try {
			c = iter.next();
		} catch (NoSuchElementException e){
			System.out.println("Pioche vide.\n");
		}
		try {
			iter.remove();
		}catch (IllegalStateException e) {
			System.out.println("Pioche vide.\n");
		}
		return c;
	}
	
	@Override
	public Iterateur iterator() {
		return new Iterateur();
	}

	//------------------Classe itérateur-----------------
	
	public class Iterateur implements Iterator<Carte>{
		private int indIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOperationsRef = nbOperations;
		
		@Override
		public boolean hasNext() {
			return indIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verificationConcurrence();
			if(hasNext()) {
				Carte c = cartes[indIterateur];
				indIterateur++;
				nextEffectue = true;
				return c;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		
		@Override
		public void remove() {
			verificationConcurrence();
			if(!nextEffectue || estVide()) {

				throw new IllegalStateException();
			}
			for (int i = indIterateur-1 ; i < nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
			}
			
			nextEffectue = false;
			indIterateur--;
			nbCartes--;
		}
		
		public void verificationConcurrence() {
			if (nbOperations != nbOperationsRef) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
