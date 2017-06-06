/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.util.ArrayList;
import java.util.Iterator;

public class Linaire extends Propulsion{
	/**
	 * Constructeur Linaire :
	 * construit une propulsion de type linaire : '+'
	 */
	public Linaire() {
		super();
		this.type = '+';
	}
	/**
	 * Methode qui recherche les positions valide pour des déplacement en ligne droite
	 * pour les cases comprises entre le vaisseau et sa portée max
	 * @return listePos : ArrayList<int[]> contenant des couples (x,y) désignant les positions valides atteignable
	 */
	@Override
	protected ArrayList<int[]> deplacementsValide(Vaisseaux ship) {
		ArrayList<int[]> listePos;
		listePos = new ArrayList<int[]>();
		for(int i = 0; i<=this.maxPortee()*2;i++){
			listePos.add(Galaxie.calculPos(new int[]{ship.getPos()[0]+(i-this.maxPortee()),ship.getPos()[1]})); //calcule les positions de deplacement pour y fixé
			listePos.add(Galaxie.calculPos(new int[]{ship.getPos()[0],ship.getPos()[1]+(i-this.maxPortee())})); //calcule les positions de deplacement pour x fixé
		}
		Iterator<int[]> iter = listePos.iterator();
		while(iter.hasNext()){
			if(!Galaxie.getGal().estLibre(iter.next())){
				iter.remove();
			}
		}
		return listePos;
	}
}