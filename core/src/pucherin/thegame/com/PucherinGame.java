package pucherin.thegame.com;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PucherinGame extends Game {
	
	public SpriteBatch batch;
	
	public Pantalla Tablero;
	public Pantalla Menu;
	public Pantalla Records;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		Tablero = new PantallaTablero (this);
		Menu = new PantallaMenu (this);
		
		setScreen(Menu);
	}

}
