package pucherin.thegame.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PantallaMenu extends Pantalla {

	float SCREEN_WIDTH;
	float SCREEN_HEIGHT;
	
	float BUTTON_WIDTH;
	float BUTTON_HEIGHT;
	
	float EspacioEntreBotones = 20;
	
	public BitmapFont font;
	
	Stage stage;
	
    TextButton ButtonJugar;
    TextButton ButtonPuntuaciones;
    TextButton ButtonInstrucciones;

    TextButtonStyle textButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;
	
	public PantallaMenu(PucherinGame game) {
		super(game);		
	}

	@Override
	public void show() {
		
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();	
		
		BUTTON_WIDTH = SCREEN_WIDTH/2 - 150;
		BUTTON_HEIGHT = SCREEN_HEIGHT/7  - 50;
		
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		
		AsignarYPosicionarBotones();			
	}

	@Override
	public void render(float delta) {
		renderizarJuego();
    }
		
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}		
	
	public void AsignarYPosicionarBotones () {
		font = new BitmapFont(Gdx.files.internal("data/font/ocrfont.fnt"), false); 
		font.scale(1.5f);
		          
        skin = new Skin();
        
        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/botones/boton.atlas"));
        skin.addRegions(buttonAtlas);
        
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("botonsinpulsar");
        textButtonStyle.down = skin.getDrawable("botonpulsado");        
        
        ButtonJugar = new TextButton("JUGAR", textButtonStyle);
        ButtonJugar.setPosition(SCREEN_WIDTH/2 - BUTTON_WIDTH/2, SCREEN_HEIGHT/2 - BUTTON_HEIGHT/2 + BUTTON_HEIGHT + EspacioEntreBotones);
        ButtonJugar.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        ButtonJugar.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	        	game.setScreen(new PantallaTablero(game));
	        	dispose();
	        }
	    });
        
        ButtonInstrucciones = new TextButton("INSTRUCCIONES", textButtonStyle);
        ButtonInstrucciones.setPosition(SCREEN_WIDTH/2 - BUTTON_WIDTH/2, SCREEN_HEIGHT/2 - BUTTON_HEIGHT/2);
        ButtonInstrucciones.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        
        ButtonPuntuaciones = new TextButton("PUNTUACIONES", textButtonStyle);
        ButtonPuntuaciones.setPosition(SCREEN_WIDTH/2 - BUTTON_WIDTH/2, SCREEN_HEIGHT/2 - BUTTON_HEIGHT/2 - EspacioEntreBotones - BUTTON_HEIGHT);
        ButtonPuntuaciones.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        
        stage.addActor(ButtonJugar);
        stage.addActor(ButtonInstrucciones);
        stage.addActor(ButtonPuntuaciones);
	}
	
	
	
	public void renderizarJuego() {	
		Gdx.gl.glClearColor(126/255f, 51/255f, 64/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();		
	}
}