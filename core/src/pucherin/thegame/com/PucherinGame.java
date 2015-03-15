package pucherin.thegame.com;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PucherinGame extends Game {
	
	public SpriteBatch batch;
	
	public Pantalla Menu;	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();		
		Menu = new PantallaMenu (this);
		
		setScreen(Menu);
	}

}
