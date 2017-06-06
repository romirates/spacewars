/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import spaceWar.Constantes;

public class Constantes {

	// Caracteristiques des especes 
	public static final int Nbespeces = 4; // taille minimale d'une planète
	
	// Caract�ristiques de la galaxie
	public static final int Largeur = 20; // largeur de la grille galactique (en nombre de cases)
	public static final int Hauteur = 15; // hauteur de la grille galactique (en nombre de cases)
	public static final int FDemiLargeur = (int)Math.floor(Constantes.Largeur/2); 
	public static final int CDemiLargeur = (int)Math.ceil(Constantes.Largeur/2);
	public static final int FDemiHauteur = (int)Math.floor(Constantes.Hauteur/2);
	public static final int CDemiHauteur = (int)Math.ceil(Constantes.Hauteur/2);
		
	// Caract�ristiques des plan�tes
	public static final int Planetes = 10; // taille minimale d'une planète
	public static final int PlaneteTailleMin = 10; // taille minimale d'une plan�te
	public static final int PlaneteTailleMax = 100; // taille maximale d'une plan�te
	public static final int PlaneteMinDegats = 5; //degats mini inflig� aux planetes
	public static final int PlaneteMaxDegats = 10; //degats max infling� aux planetes
		
	// Caract�ristiques des vaisseaux
	public static final int VaisseauResistanceMin = 1; // r�sistance minimale d'un vaisseau
	public static final int VaisseauResistanceMax = 10; // r�sistance maximale d'un vaisseau
	public static final int VaisseauMinDegats = 1; //degats mini inflig� aux vaisseaux
	public static final int VaisseauMaxDegats = 3; //degats max inflig� aux vaisseaux
	public static final int VaisseauInit = 2; //nombre de vaisseaux présent autour des planetes de bases des espèces au début de la simulation (max = 8)

	// Caract�ristiques des esp�ces
	public static final int EspecesNataliteMin = 5;
	public static final int EspecesNataliteMax = 10;
	public static final int EspecesProductiviteMin = 1;
	public static final int EspecesProductiviteMax = 5;
	
	// Caract�ristiques des propulsions
	public static final int PropulsionCarburantMin = 1;
	public static final int PropulsionCarburantMax = 20;
	public static final int PropulsionPorteeMin = 1; // port�e minimal d'une propulsion
	public static final int PropulsionPorteeMax = 5; // port�e maximal d'une propulsion
	
	// Param�tres de la simulation
	public static final int TourMax = 100; // nombre de tours maximum
	public static final int TourMs = 500; // dur�e d'un tour en millisecondes
		
	// Paramétres d'affichage
	public static final int GfxCase = 40; // taille des cases en pixel
	public static final int GfxPlaneteBase = 20; // taille de base des plan�tes en pixels
	public static final int GfxPlaneteFacteur = 5; // proportion des plan�tes relative � leur taille
	public static final int GfxVaisseauBase = 5; // taille de base des vaisseaux en pixels
	public static final int GfxVaisseauFacteur = 1; // proportion des vaisseaux relative � leur r�sistance
	
}


