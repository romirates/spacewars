/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.util.ArrayList;
import java.util.Iterator;

public class Diagonal extends Propulsion{

	/**
	 * Constructeur Diagonal :
	 * construit une porpulsion de type diagonal : 'x'
	 */
	public Diagonal(){
		super();
		this.type = 'x';
	}
	/**
	 * Methode qui recherche les positions valide pour des déplacement en diagonale
	 * pour les cases comprises entre le vaisseau et sa portée max
	 * @return listePos : ArrayList<int[]> contenant des couples (x,y) désignant les positions valides atteignable
	 */
	@Override
	protected ArrayList<int[]> deplacementsValide(Vaisseaux ship) {
		ArrayList<int[]> listePos = new ArrayList<int[]>();
		for (int i=0; i<=this.maxPortee()*2; i++){
			listePos.add(Galaxie.calculPos(new int[]{ship.getPos()[0]+(-this.maxPortee()+i),ship.getPos()[1]+(this.maxPortee()-i)}));
			listePos.add(Galaxie.calculPos(new int[]{ship.getPos()[0]+(-this.maxPortee()+i),ship.getPos()[1]+(-this.maxPortee()+i)}));
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
