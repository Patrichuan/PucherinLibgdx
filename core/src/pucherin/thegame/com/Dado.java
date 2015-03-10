package pucherin.thegame.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Dado extends Sprite {

	private static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/dados/dado.atlas"));
	private int Valor;

	public Dado() {
		super(new Sprite(atlas.createSprite("dado1")));
		Valor = 1;
	}

	// Devuelve un numero al azar
	public int NumAlAzar(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	// Tira un dado y devuelve el valor sacado despues de haber cambiado la
	// textura de este
	public void UpdateTexture(int Num) {
		switch (Num) {
		case 1:
			setTexture(atlas.findRegion("dado1").getTexture());
			break;
		case 2:
			setTexture(atlas.findRegion("dado2").getTexture());
			break;
		case 3:
			setTexture(atlas.findRegion("dado3").getTexture());
			break;
		case 4:
			setTexture(atlas.findRegion("dado4").getTexture());
			break;
		case 5:
			setTexture(atlas.findRegion("dado5").getTexture());
			break;
		case 6:
			setTexture(atlas.findRegion("dado6").getTexture());
			break;
		}
	}

	public int RollDice() {
		Valor = NumAlAzar(1, 6);
		UpdateTexture(Valor);
		return Valor;
	}

	public int getValor() {
		return Valor;
	}
	
	public void setValor(int Valor) {
		this.Valor = Valor;
	}
}