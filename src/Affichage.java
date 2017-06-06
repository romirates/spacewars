/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @brief Classe permettant de réaliser un affichage graphique de la galaxie
 * 
 * La grille galactique est représentée graphiquement dans un panneau, les 
 * cases étant numérotées à partir du coin supérieur gauche (0,0) jusqu'au
 * coin inférieur droit (Largeur,Hauteur) 
 */
public final class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	// Largeur et hauteur du panel graphique, calculés une seule fois à la construction
	private int largeur, hauteur;
	private ArrayList<Vaisseaux> vaisseaux;
	private ArrayList<Planetes> planetes;
	/**
	 * @brief Constructeur initialisant le panneau d'affichage
	 */
	// TODO : à compléter pour faire le lien entre la galaxie et le panneau d'affichage ...
	public Affichage() {
		// calcul des dimensions du panneau en fonction des paramètres de la galaxie
		largeur = Constantes.Largeur*Constantes.GfxCase;
		hauteur = Constantes.Hauteur*Constantes.GfxCase;
		setPreferredSize(new Dimension(largeur,hauteur));
		this.vaisseaux = new ArrayList<Vaisseaux>();
		this.planetes = new ArrayList<Planetes>();
	}

	/**
	 * @brief Efface le contenu du panneau en redéssinant le fond et la grille
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 */
	public void efface(Graphics g) {
		// espace galactique : fond noir
		g.setColor(Color.BLACK);
		g.fillRect(0,0,largeur,hauteur);
		// grille galactique : lignes grises
		g.setColor(Color.GRAY);
		for (int x=Constantes.GfxCase ; x<largeur; x+=Constantes.GfxCase) {
			// affichage des lignes verticales
			g.drawLine(x, 0, x, hauteur);
		}
		for (int y=Constantes.GfxCase ; y<hauteur; y+=Constantes.GfxCase) {
			// affichage des lignes horizontales
			g.drawLine(0, y, largeur, y);
		}
	}

	/**
	 * @brief Affiche une planète selon ses caractéristiques
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 * @param x Abcisse de la case contenant la planète 
	 * @param y Ordonnée de la case contenant la planète
	 * @param t Taille de la planète
	 * @param c Couleur de la planète
	 */
	public void affichePlanete(Graphics g, Planetes planete) {
		// calcul des coordonnées graphiques du centre de la planète
		int xg = planete.getPos()[0]*Constantes.GfxCase + Constantes.GfxCase/2;
		int yg = planete.getPos()[1]*Constantes.GfxCase + Constantes.GfxCase/2;
		// calcul du rayon de la planète selon sa taille
		int tg = (int) (Constantes.GfxPlaneteBase + planete.getTaille()/Constantes.GfxPlaneteFacteur);
		// affichage
		g.setColor(planete.getFaction().getColor());
		g.fillOval(xg-tg/2,yg-tg/2,tg,tg);
	}
	
	/**
	 * @brief Affiche un vaisseau selon ses caractéristiques
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 * @param x Abcisse de la case contenant le vaisseau 
	 * @param y Ordonnée de la case contenant le vaisseau
	 * @param r Résistance du vaisseau
	 * @param s Chaîne symbolisant la propulsion et l'équipement du vaisseau
	 * @param c Couleur du vaisseau
	 */
	public void afficheVaisseau(Graphics g, Vaisseaux ship) {
		// calcul des coordonnées graphiques du centre du vaisseau
		int xg = ship.getPos()[0]*Constantes.GfxCase + Constantes.GfxCase/2;
		int yg = ship.getPos()[1]*Constantes.GfxCase + Constantes.GfxCase/2;
		// calcul du côté du vaisseau selon sa taille
		int tg = Constantes.GfxVaisseauBase + ship.getIntegrite()/Constantes.GfxVaisseauFacteur;
		// affichage du carré
		g.setColor(ship.getFaction().getColor());
		g.fillRect(xg-tg/2,yg-tg/2,tg,tg);
		// calcul des dimensions graphiques de la chaîne à afficher
		int lg = (int)Math.ceil(g.getFont().getStringBounds(ship.toString(),((Graphics2D)g).getFontRenderContext()).getWidth());
		int hg = (int)Math.ceil(g.getFont().getStringBounds(ship.toString(),((Graphics2D)g).getFontRenderContext()).getHeight());
		// affichage de la chaîne sur le carré
		g.setColor(Color.WHITE);
		g.drawString(ship.typeVaisseau(), xg-lg/2, yg+hg/2);
	}

	/**
	 * @brief Provoque le rafraichissement du panneau
	 */
	// TODO : modifier la signature au besoin 
	@SuppressWarnings("unchecked")
	public void rafraichir(ArrayList<Especes> especes) {
		this.vaisseaux.clear();
		this.planetes.clear();
		for(Especes esp : especes){
			this.vaisseaux.addAll(esp.getFlotte());
			this.planetes.addAll(esp.getEmpire());
		}
		repaint();
	}

	/**
	 * @brief Réaffiche le panneau à la demande
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 */
	@Override
	public void paintComponent(Graphics g) {
		// affichage par défaut
		super.paintComponent(g);
		
		// l'espace et la grille galactique
		efface(g);

		// affichage des planètes et vaisseaux
		for(int i=0; i<this.vaisseaux.size();i++){
			this.afficheVaisseau(g, this.vaisseaux.get(i));
		}
		for(int i=0; i<this.planetes.size();i++){
			this.affichePlanete(g, this.planetes.get(i));
		}
	}
	
}
