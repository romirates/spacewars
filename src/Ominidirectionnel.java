/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.util.ArrayList;
import java.util.Iterator;

public class Ominidirectionnel extends Propulsion{
	/**
	 * Constructeur Ominidirectionnel :
	 * construit une porpulsion de type Ominidirectionnel : '*'
	 */
	public Ominidirectionnel() {
		super();
		this.type = '*';
	}
	/**
	 * Methode qui recherche les positions valide 
	 * dans un carré de de diamètre 2 fois la portée maximale
	 * @return listePos : ArrayList<int[]> contenant des couples (x,y) désignant les positions valides atteignable
	 */
	@Override
	protected ArrayList<int[]> deplacementsValide(Vaisseaux ship) {
		int distanceMax = this.maxPortee();
		int[] posV = ship.getPos().clone();
		ArrayList<int[]> listePos = new ArrayList<int[]>();
		for(int j=posV[1] - distanceMax; j< posV[1] + distanceMax; j++)
		{
			for(int i=posV[0] - distanceMax; i<posV[0] + distanceMax; i++){
				
				listePos.add(Galaxie.calculPos(new int[]{i,j}));
			}
		}
		Iterator<int[]> iter = listePos.iterator();
		while(iter.hasNext()){
			if(!Galaxie.getGal().estLibre(iter.next())){
				iter.remove();
			}
		}
		// TODO Auto-generated method stub
		return listePos;
	}

}
