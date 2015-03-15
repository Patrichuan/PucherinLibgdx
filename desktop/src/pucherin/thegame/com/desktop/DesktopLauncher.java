package pucherin.thegame.com.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pucherin.thegame.com.PucherinGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Pucherin: The Game";
		//config.useGL20 = true;
		config.height = 1080;
		config.width = 1920;
		
		new LwjglApplication(new PucherinGame(), config);
	}
}
