package agar.states;


//import net.ghostid.Resources;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import agar.controls.Window;
import agar.models.Agar;

public class GameState extends BasicGameState{

	private boolean _droite = false;
	private boolean _gauche = false;
	private boolean _haut = false;
	private boolean _bas = false;
	private int _posX = 400;
	private int _posY = 300;
	private Agar me;
	private List<Agar> boules;
	private int nbrAgars = 20;
	Random rand = new Random();
	public static int score;

	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		me = new Agar(new Point(400,300),Color.blue,30,30);
		GameState.score = 0;
		
		/******************************************************
		 * Dans cet ArrayList on met tous les boules à manger
		 ******************************************************/
		boules = new ArrayList<Agar>();
		
		for(int i=0; i<nbrAgars; i++){
			
			/********************************************************************************
			 * Generer aleatoirement les positions X et Y des boules a manger
			 * formule utilisee -> int valeur = valeurMin + r.nextInt(valeurMax - valeurMin)
			 ********************************************************************************/
			int posxAleatoire = rand.nextInt((Window.WIDTH - 5)  + 1);
			int posyAleatoire = rand.nextInt((Window.HEIGHT - 5) + 1);
			
			/*********************************************************************
			 * Generer aleatoirement les couleurs des boules a manger en generant
			 * le degré de Vert, Bleu et Rouge utilisé (RGB)
			 *********************************************************************/
			int vert  = (int)(Math.random()*255.99);
            int bleu  = (int)(Math.random()*255.99);
            int rouge = (int)(Math.random()*255.99);
			boules.add(new Agar(new Point(posxAleatoire,posyAleatoire),new Color(rouge,vert,bleu),20,20));
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		g.setColor(Color.red);
		g.drawString("Score = "+GameState.score, 0, 0);
		for(int i=0; i<boules.size();i++){
			boules.get(i).dessiner(g);
		}
		me.dessiner(g);
		detecterCollision();
		
	   //Si le bouton "Fleche droite" du clavier est appuyé
	   if(_droite){
			me.dessiner(g);
			if(_posX == 820) _posX = 0;
			_posX++;
			me.setPosition(new Point(_posX,_posY));
			
		}
	  //Si le bouton "Fleche gauche" du clavier est appuyé
		else if(_gauche){
			me.dessiner(g);
			if(_posX == -20) _posX = 800;
			_posX--;
			me.setPosition(new Point(_posX,_posY));
		}
	 //Si le bouton "Fleche bas" du clavier est appuyé
		else if(_bas){
			me.dessiner(g);
			if(_posY == 620) _posY = 0;
			_posY++;
			me.setPosition(new Point(_posX,_posY));
		}
	 //Si le bouton "Fleche haut" du clavier est appuyé
		else if(_haut){
			me.dessiner(g);
			if(_posY == -20) _posY = 600;
			_posY--;
			me.setPosition(new Point(_posX,_posY));
		}	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if(finJeux()) s.enterState(States.END);
		
		//Si le bouton "Fleche droite" du clavier est appuyé
		if(gc.getInput().isKeyPressed(Input.KEY_RIGHT)) {
			_droite = true;
			_gauche = false;
			_haut = false;
			_bas = false;
			}
		
		//Si le bouton "Fleche gauche" du clavier est appuyé
		if(gc.getInput().isKeyPressed(Input.KEY_LEFT)) {
			_gauche = true;
			_droite = false;
			_haut = false;
			_bas = false;
			}
		
		//Si le bouton "Fleche bas" du clavier est appuyé
		if(gc.getInput().isKeyPressed(Input.KEY_UP)) {
			_haut = true;
			_gauche = false;
			_droite = false;
			_bas = false;
			}
		
		//Si le bouton "Fleche haut" du clavier est appuyé
		if(gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
			_bas = true;
			_haut = false;
			_gauche = false;
			_droite = false;
			}
		}
		
	@Override
	public int getID() {
		return States.GAME;
	}
	
	/******************************************************************
	 * Cette fonction calcul la distance entre deux boules
	 * Formule utilisée -> AB = racine carree((Xb - Xa)² + (Yb - Ya)²)
	 ******************************************************************/
	public double distanceBoules(Agar a1, Agar a2){
		return Math.sqrt(Math.pow((double)((a2.getPosition().x - a2.getLargeur()) - a1.getPosition().x), 2.0)+Math.pow((double)((a2.getPosition().y - a2.getHauteur()) - a1.getPosition().y), 2.0));
	}
	
	/*****************************************************************************
	 * Cette fonction permet de voir si notre boule (me) a touché une autre boule
	 * Si oui: 
	 * 1) On change la position du boule touchée de façon à ne plus la voir
	 * 2) Augmenter la taille de notre boulr (me)
	 * 3) Augmenter le score de 10
	 ******************************************************************************/
	public void detecterCollision(){
		for(int i = 0; i < this.boules.size(); i ++){
			if((distanceBoules(me, boules.get(i)) <= me.getLargeur()/2)){
				this.boules.get(i).setPosition(new Point(1000,1000));
				me.setHauteur(me.getHauteur()+5);
				me.setLargeur(me.getLargeur()+5);
				GameState.score += 10;	
			}
		}
	}
	
	/*********************************************************************************************
	 * Cette fonction cherche s'il y a encore une boule à manger pour pouvoir finir le jeux apres
	 * elle retourne "true" si on a tout mangé (jeux fini)
	 * et "false" sinon
	 *********************************************************************************************/
	public boolean finJeux(){
		int nbrBoulesManges = 0;
		for(int i=0; i<this.boules.size();i++){
			if(this.boules.get(i).getPosition().x == 1000) nbrBoulesManges++;
		}
		if(nbrBoulesManges == nbrAgars) return true;
		else return false;	
	}
}
