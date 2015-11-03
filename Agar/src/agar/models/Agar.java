package agar.models;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Agar {

	private Point position;
	private Color couleur;
	private int hauteur;
	private int largeur;
	
	public Agar(Point position,Color couleur,int largeur,int hauteur) {
		super();
		this.position = position;
		this.couleur = couleur;
		this.hauteur = hauteur;
		this.largeur = largeur;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	
	/***************************************************************
	 * Cette fonction permet d'affiche l'Agar sur l'interface du jeu
	 ***************************************************************/
	public void dessiner(Graphics g){
		g.setColor(this.couleur);
		g.fillOval(this.position.x, this.position.y, this.largeur, this.hauteur);	
	}
	
	
	
}
