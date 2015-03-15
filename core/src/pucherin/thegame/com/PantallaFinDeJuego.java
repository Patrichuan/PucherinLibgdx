package pucherin.thegame.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PantallaFinDeJuego extends Pantalla {

	private int[] Puntuaciones = new int[5];

	float SCREEN_WIDTH;
	float SCREEN_HEIGHT;
	
	float BUTTON_WIDTH;
	float BUTTON_HEIGHT;
		
	public BitmapFont font;
	
	Stage stage;
	
    TextButton ButtonJugar;
    
    private Label Puntuacion1_lbl, Puntuacion2_lbl, Puntuacion3_lbl, Puntuacion4_lbl;
    private Label Marcadores_lbl;
    
    private LabelStyle PassiveTurnStyle;
	private LabelStyle CurrentTurnStyle;
    
    
    TextButtonStyle textButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;
	
    public PantallaFinDeJuego(PucherinGame game, int[] Puntuaciones) {		
		super(game);
		this.Puntuaciones = Puntuaciones;
	}

	@Override
	public void show() {
		
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();			
		
		BUTTON_WIDTH = SCREEN_WIDTH/2 - 150;
		BUTTON_HEIGHT = SCREEN_HEIGHT/7  - 50;
		
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		
		AsignarYPosicionarPuntuaciones();			
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
	
	public void AsignarYPosicionarPuntuaciones () {
		font = new BitmapFont(Gdx.files.internal("data/font/ocrfont.fnt"), false); 
		font.scale(1.5f);
		          
		PassiveTurnStyle = new LabelStyle(font, Color.valueOf("FFFFFF"));
		CurrentTurnStyle = new LabelStyle(font, Color.valueOf("D7DF01"));		
		
        skin = new Skin();
        
        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/botones/boton.atlas"));
        skin.addRegions(buttonAtlas);
        
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("botonsinpulsar");
        textButtonStyle.down = skin.getDrawable("botonpulsado");        
        
        ButtonJugar = new TextButton("JUGAR DE NUEVO", textButtonStyle);
        ButtonJugar.setPosition(SCREEN_WIDTH/2 - BUTTON_WIDTH/2, 50);
        ButtonJugar.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        ButtonJugar.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	        	game.setScreen(new PantallaTablero(game));
	        	dispose();
	        }
	    });
        
        Marcadores_lbl = new Label("PUNTUACIONES", CurrentTurnStyle);
        Marcadores_lbl.setFontScale(5);
        Marcadores_lbl.setPosition(SCREEN_WIDTH / 2 - 700, SCREEN_HEIGHT - 150);
        
        Puntuacion1_lbl = new Label("PLAYER 1: "+Puntuaciones[1], PassiveTurnStyle);
        Puntuacion1_lbl.setFontScale(3);
        Puntuacion1_lbl.setPosition(SCREEN_WIDTH / 2 - 400, SCREEN_HEIGHT - 350);
        
        Puntuacion2_lbl = new Label("PLAYER 2: "+Puntuaciones[2], PassiveTurnStyle);
        Puntuacion2_lbl.setFontScale(3);
        Puntuacion2_lbl.setPosition(SCREEN_WIDTH / 2 - 400, SCREEN_HEIGHT - 500);
        
        Puntuacion3_lbl = new Label("PLAYER 3: "+Puntuaciones[3], PassiveTurnStyle);
        Puntuacion3_lbl.setFontScale(3);
        Puntuacion3_lbl.setPosition(SCREEN_WIDTH / 2 - 400, SCREEN_HEIGHT - 650);
        
        Puntuacion4_lbl = new Label("PLAYER 4: "+Puntuaciones[4], PassiveTurnStyle);
        Puntuacion4_lbl.setFontScale(3);
        Puntuacion4_lbl.setPosition(SCREEN_WIDTH / 2 - 400, SCREEN_HEIGHT - 800);        
        
        stage.addActor(ButtonJugar);
        stage.addActor(Marcadores_lbl);
        stage.addActor(Puntuacion1_lbl);
        stage.addActor(Puntuacion2_lbl);
        stage.addActor(Puntuacion3_lbl);
        stage.addActor(Puntuacion4_lbl);
	}
	
	
	
	public void renderizarJuego() {	
		Gdx.gl.glClearColor(126/255f, 51/255f, 64/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();		
	}
}