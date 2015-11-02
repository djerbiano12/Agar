package agar.states;

import org.newdawn.slick.Color;
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
		g.setColor(Color.blue);
		g.drawString("Félicitation vous avez tout mangé !!!", 250, 250);
		g.setColor(Color.red);
		g.drawString("Score finale = "+GameState.score, 320, 280);
		
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
