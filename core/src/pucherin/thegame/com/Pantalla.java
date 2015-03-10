package pucherin.thegame.com;

import com.badlogic.gdx.Screen;

public abstract class Pantalla implements Screen {
	
	protected PucherinGame game;
	
	// De esta forma tendremos todas las pantallas conectadas al juego al que pertenecen (PucherinGame)
	// y podremos tirar del SpriteBatch sin tener que declararlo continuamente
	public Pantalla (PucherinGame game) {
		this.game = game;
	}
	
}
