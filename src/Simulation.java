/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.awt.Color;
import java.util.Collections;

/**
 * @brief Classe gérant la simulation de conquête galactique
 */
public class Simulation {

	/**
	 * Methode qui regarde les scores des especes et qui les comparent
	 * affiche les espèces classé par score du plus gros au plus petit
	 */

	public static void classement(){
		Collections.sort(Galaxie.getGal().getEsp());
		for(int i =Galaxie.getGal().getEsp().size()-1; i>=0;i--){
			System.out.println("- especes id = "+Galaxie.getGal().getEsp().get(i).toString()+"\nScore = "+Galaxie.getGal().getEsp().get(i).score());
		}
			
	}
	/**
	 * Methode victoire qui indique si la partie a été remporter par une espèce
	 * 
	 * @return Vrai ssi la partie est terminée
	 */
	public static Boolean victoire() {
		return (Galaxie.getGal().getEsp().size()==1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// création du panneau d'affichage
		Affichage panneau = new Affichage();

		// création de la fenêtre principale contenant le panneau
		Fenetre fenetre = new Fenetre(panneau);
		Galaxie.getGal().genese(3, 10);
		// boucle de simulation
		int tour = 0;
		Galaxie.getGal().afficheInfo();
		while (!victoire() && tour<Constantes.TourMax) {
			// décompte des tours
			tour += 1;

			// Exécution des étapes du tour courant
			Galaxie.getGal().jouer();
			// Affichage d'un bref rapport textuel
			System.out.println("Tour " + tour + " :");
			for(Especes esp : Galaxie.getGal().getEsp()){
				if(esp.getColor() != Color.WHITE){//on affiche pas les données de l'espèce de base
					System.out.println("Espece id : "+esp.toString());
					System.out.println("Taille de son empire : "+esp.getEmpire().size()+" planetes.");
					System.out.println("Taille de sa flotte : "+esp.getFlotte().size()+" vaisseaux.");
					System.out.println("Score actuel : "+esp.score()+" points.");
					System.out.println();
				}
			}
			panneau.rafraichir(Galaxie.getGal().getEsp());
			
			// temporisation avant prochain tour
			try {
				Thread.sleep(Constantes.TourMs);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		classement();
		// fermeture de la fenêtre
		fenetre.dispose();
	}

}
