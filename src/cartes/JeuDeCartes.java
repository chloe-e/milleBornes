package cartes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JeuDeCartes {
	Configuration[] typesDeCartes = {new Configuration(10, new Borne(25)),
					new Configuration(10,new Borne(50)),
	                new Configuration(10,new Borne(75)),
	                new Configuration (12,new Borne(100)),
					new Configuration (4,new Borne(200)),
					new Configuration(6,new FinLimite()),
					new Configuration(6,new Parade(Type.ESSENCE)),
					new Configuration(14,new Parade(Type.FEU)),
					new Configuration(6,new Parade(Type.CREVAISON)),
					new Configuration(6,new Parade(Type.ACCIDENT)),
					new Configuration(4,new DebutLimite()),
					new Configuration(3,new Attaque(Type.ESSENCE)),
					new Configuration(3,new Attaque(Type.CREVAISON)),
					new Configuration(5,new Attaque(Type.FEU)),
					new Configuration(3,new Attaque(Type.ACCIDENT)),
					new Configuration(1,new Botte(Type.FEU)),
					new Configuration(1,new Botte(Type.ESSENCE)),
					new Configuration(1,new Botte(Type.CREVAISON)),
					new Configuration(1,new Botte(Type.ACCIDENT))};
	

	public String affichageJeuDeCartes() {
		StringBuilder sb = new StringBuilder(" JEU :\n\n");
		for (Configuration configuration : typesDeCartes) {
			sb.append(configuration.getNbExemplaires() + 
					" " + configuration.getCarte().toString() + "\n");
		}
		return sb.toString();
	}
	
	public Carte[] donnerCartes() {
		Carte[] cartes = new Carte[106];
		int ind = 0;
		for (Configuration configuration : typesDeCartes) {
			for (int i = ind; i < ind+configuration.nbExemplaires; i++) {
				cartes[i] = configuration.carte;
							}
			ind+=configuration.nbExemplaires;
		}
		
		return cartes;
	}
	
	public boolean checkCount() {
		
		List<Carte> carteDonnees = new ArrayList<>();
		carteDonnees.addAll(Arrays.asList(donnerCartes()));
		
		for (Configuration config : typesDeCartes) {
			if(Collections.frequency(carteDonnees, config.getCarte()) 
					!= config.getNbExemplaires()) {
				return false;
			}
		}
		return true;
	}
		
	private static class Configuration {
		
		private int nbExemplaires;
		private Carte carte;
		
		public Configuration(int nbExemplaires, Carte carte) {
			super();
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}
}
