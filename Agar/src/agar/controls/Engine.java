package agar.controls;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import agar.states.FinJeuxState;
import agar.states.GameState;

public class Engine extends StateBasedGame {
	
	
	public Engine(String title){
		super(title);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		/******************************************
		 * ajout des differents states dans le jeux
		 ******************************************/
		this.addState(new GameState());
		this.addState(new FinJeuxState());
		
	}

}
