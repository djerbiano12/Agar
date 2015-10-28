package agar.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class FinJeuxState extends BasicGameState {

	@Override
	public void init(GameContainer gc, StateBasedGame s)
			throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g)
			throws SlickException {
		g.drawString("Félicitation vous avez tous mangés !!!", 250, 300);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta)
			throws SlickException {	
	}

	@Override
	public int getID() {
		return States.END;
	}

}
