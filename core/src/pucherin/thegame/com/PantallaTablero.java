package pucherin.thegame.com;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class PantallaTablero extends Pantalla {

	//TODO 
	// - Cambiar de pantalla cuando se acaba la partida
	// - Sonido
	// - Animaciones
	// - Pantalla de Inicio
	// - Pantalla de Puntuaciones
	
	private Sprite Tablero;
	private Circulo Circulo2, Circulo3, Circulo4, Circulo5, Circulo11;
	private Circulo Circulo6, Circulo8, Circulo9, Circulo10;
	private Circulo Circulo7;	
	
	private Dado Dado1, Dado2; 
	
	private BitmapFont font;
	private LabelStyle PassiveTurnStyle;
	private LabelStyle CurrentTurnStyle;
	
	// Unificar en un array de lbls
	private Label NombrePlayer1, NombrePlayer2, NombrePlayer3, NombrePlayer4;
	
	// Unificar en un array las puntuaciones y las FichasRestantes_lbl
	private Label Puntuacion1_lbl, Puntuacion2_lbl, Puntuacion3_lbl, Puntuacion4_lbl;
	private Label Multiplicador_lbl;
	private Label FichasRestantes_lbl;
	
	private int[] Puntuacion = new int[5];
	int CantidadGanada = 0;
	
	private int TurnoDe = 1;
	private boolean ModoFinish = false;
	
	private OrthographicCamera cam;

	Vector3 touchPoint = new Vector3();

	float ScaleSizeCircle = 3.375f;
	float ScaleSizeDice = 0.139f;

	float SCREEN_WIDTH;
	float SCREEN_HEIGHT;

	float CIRCLE_WIDTH;
	float CIRCLE_HEIGHT;
	
	float DICE_WIDTH;
	float DICE_HEIGHT;
	
	final int TamañoDeMargenes = 50;
	final int SeparacionEntreCirculos = 40;

	public PantallaTablero(PucherinGame game) {
		super(game);
	}

	@Override
	public void show() {

		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		
		CIRCLE_WIDTH = SCREEN_HEIGHT / ScaleSizeCircle;
		CIRCLE_HEIGHT = SCREEN_HEIGHT / ScaleSizeCircle;		
		
		DICE_WIDTH = SCREEN_HEIGHT / ScaleSizeDice;
		DICE_HEIGHT = SCREEN_WIDTH / ScaleSizeDice;

		AsignarYPosicionarTablero ();		
		AsignarYPosicionarCirculos ();			
		AsignarYPosicionarMarcadores ();
		AsignarYPosicionarDados ();
		
		float aspectRatio = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;

		cam = new OrthographicCamera(1920 * aspectRatio, 1080);
		cam.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
	}

	@Override
	public void render(float delta) {
		renderizarJuego();
		procesarEntrada();
	}

	@Override
	public void resize(int width, int height) {
		// Indico las dimensiones de la camara y que el eje de coordenadas Y
		// empiece de abajo hacia arriba
		cam.setToOrtho(false, width, height);
		cam.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
	
	
	
	// Asigno Sprites y Posiciono en la pantalla los Sprites
	public void AsignarYPosicionarTablero () {
		Tablero = new Sprite(new Texture("data/tablero/tablero.png"));
		Tablero.setPosition(0, 0);
		Tablero.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	public void AsignarYPosicionarMarcadores () {
		font = new BitmapFont(Gdx.files.internal("data/font/ocrfont.fnt"), false);
		PassiveTurnStyle = new LabelStyle(font, Color.valueOf("FFFFFF"));
		CurrentTurnStyle = new LabelStyle(font, Color.valueOf("D7DF01"));
		
		// Crear otra font para otro LabelStyle con tamaño 500 para que no se pixele el numero de fichas restantes		
		Puntuacion[0] = 50; // Estoy deberia de ser 50
		FichasRestantes_lbl = new Label(""+Puntuacion[0], PassiveTurnStyle);
		FichasRestantes_lbl.setFontScale(5);
		FichasRestantes_lbl.setPosition(SCREEN_WIDTH / 2 - 120, SCREEN_HEIGHT - TamañoDeMargenes - 100);		
		
		Multiplicador_lbl = new Label("+0", PassiveTurnStyle);
		Multiplicador_lbl.setFontScale(3.5f);
		Multiplicador_lbl.setPosition(SCREEN_WIDTH / 2 - 95, SCREEN_HEIGHT / 2 + 60);	
		
		NombrePlayer1 = new Label("PLAYER 1", CurrentTurnStyle);
		NombrePlayer1.setPosition(TamañoDeMargenes - 8, SCREEN_HEIGHT - TamañoDeMargenes - 30);		
		Puntuacion1_lbl = new Label("00", CurrentTurnStyle);
		Puntuacion1_lbl.setPosition(TamañoDeMargenes - 8, SCREEN_HEIGHT - TamañoDeMargenes - 70);		
		Puntuacion[1] = 0;
		
		NombrePlayer2 = new Label("PLAYER 2", PassiveTurnStyle);
		NombrePlayer2.setPosition(SCREEN_WIDTH - TamañoDeMargenes - 175, SCREEN_HEIGHT - TamañoDeMargenes - 30);		
		Puntuacion2_lbl = new Label("00", PassiveTurnStyle);
		Puntuacion2_lbl.setPosition(SCREEN_WIDTH - TamañoDeMargenes - 35, SCREEN_HEIGHT - TamañoDeMargenes - 70);		
		Puntuacion[2] = 0;
		
		NombrePlayer3 = new Label("PLAYER 3", PassiveTurnStyle);
		NombrePlayer3.setPosition(TamañoDeMargenes - 8, 80);		
		Puntuacion3_lbl = new Label("00", PassiveTurnStyle);
		Puntuacion3_lbl.setPosition(TamañoDeMargenes - 8, 40);		
		Puntuacion[3] = 0;
		
		NombrePlayer4 = new Label("PLAYER 4", PassiveTurnStyle);
		NombrePlayer4.setPosition(SCREEN_WIDTH - TamañoDeMargenes - 175, 80);		
		Puntuacion4_lbl = new Label("00", PassiveTurnStyle);
		Puntuacion4_lbl.setPosition(SCREEN_WIDTH - TamañoDeMargenes - 35, 40);		
		Puntuacion[4] = 0;
	}	
	
	public void AsignarYPosicionarCirculos () {
		Circulo5 = new Circulo(5);
		Circulo5.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo5.setPosition(TamañoDeMargenes, 200);		

		Circulo3 = new Circulo(3);
		Circulo3.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo3.setPosition(SCREEN_WIDTH / 2 - (CIRCLE_WIDTH / 2),	TamañoDeMargenes);

		Circulo4 = new Circulo(4);
		Circulo4.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo4.setPosition(TamañoDeMargenes + CIRCLE_WIDTH + SeparacionEntreCirculos, 100);

		Circulo2 = new Circulo(2);
		Circulo2.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo2.setPosition(SCREEN_WIDTH - TamañoDeMargenes - (CIRCLE_WIDTH * 2) - SeparacionEntreCirculos, 100);

		Circulo6 = new Circulo(6);
		Circulo6.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo6.setPosition(100, SCREEN_HEIGHT - 500);		

		Circulo8 = new Circulo(8);
		Circulo8.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo8.setPosition(100 + CIRCLE_WIDTH + SeparacionEntreCirculos, SCREEN_HEIGHT - 370);

		Circulo9 = new Circulo(9);
		Circulo9.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo9.setPosition(SCREEN_WIDTH - TamañoDeMargenes * 2 - (CIRCLE_WIDTH * 2) - SeparacionEntreCirculos, SCREEN_HEIGHT - 370);

		Circulo7 = new Circulo(7);
		Circulo7.setSize(CIRCLE_WIDTH + 150, CIRCLE_HEIGHT + 150);
		Circulo7.setPosition(SCREEN_WIDTH / 2 - CIRCLE_WIDTH / 2 - 75, SCREEN_HEIGHT / 2 - CIRCLE_HEIGHT / 2);		
		
		Circulo10 = new Circulo(10);
		Circulo10.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo10.setPosition(SCREEN_WIDTH - TamañoDeMargenes * 2 - CIRCLE_WIDTH, SCREEN_HEIGHT - 500);
		
		Circulo11 = new Circulo(11);
		Circulo11.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
		Circulo11.setPosition(SCREEN_WIDTH - TamañoDeMargenes - CIRCLE_WIDTH, 200);		
	}

	public void AsignarYPosicionarDados () {
		Dado1 = new Dado();
		Dado1.setSize(150, 150);
		Dado1.setPosition(SCREEN_WIDTH - SCREEN_WIDTH/3, SCREEN_HEIGHT/2 - 40);		
		
		Dado2 = new Dado();
		Dado2.setSize(150, 150);
		Dado2.setPosition(SCREEN_WIDTH/3 - 150, SCREEN_HEIGHT/2 - 40);
	}
	
	
	
	// Dibujo los Sprites con el game.batch
	public void DibujarTablero () {
		Tablero.draw(game.batch);
	}
	
	public void DibujarMarcadores () {
		NombrePlayer1.draw(game.batch, 1);
		Puntuacion1_lbl.draw(game.batch, 1);
		
		NombrePlayer2.draw(game.batch, 1);
		Puntuacion2_lbl.draw(game.batch, 1);
		
		NombrePlayer3.draw(game.batch, 1);
		Puntuacion3_lbl.draw(game.batch, 1);
		
		NombrePlayer4.draw(game.batch, 1);
		Puntuacion4_lbl.draw(game.batch, 1);
		
		FichasRestantes_lbl.draw(game.batch, 1);
		
		Multiplicador_lbl.draw(game.batch, 1);
	}
	
	public void DibujarCirculos () {
		Circulo5.draw(game.batch);
		Circulo3.draw(game.batch);
		Circulo4.draw(game.batch);		
		Circulo2.draw(game.batch);	
		Circulo6.draw(game.batch);		
		Circulo8.draw(game.batch);
		Circulo9.draw(game.batch);
		Circulo7.draw(game.batch);
		Circulo10.draw(game.batch);
		Circulo11.draw(game.batch);
		
	}
	
	public void DibujarDados () {
		Dado1.draw(game.batch);
		Dado2.draw(game.batch);
	}
	
	
	
	// Actualizacion de Textures de los Sprites
	public void ActualizarDados () {			
		Dado1.setSize(150, 150);
		Dado1.setPosition(SCREEN_WIDTH - SCREEN_WIDTH/3, SCREEN_HEIGHT/2 - 40);		
		
		Dado2.setSize(150, 150);
		Dado2.setPosition(SCREEN_WIDTH/3 - 150, SCREEN_HEIGHT/2 - 40);	
	}
	
	public void ActualizarCirculo (int NumDeCirculo) {
		if (!ModoFinish) {
			switch (NumDeCirculo) {
			case 2:
				Circulo2.UpdateTexture();
				Circulo2.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo2.setPosition(SCREEN_WIDTH - TamañoDeMargenes - (CIRCLE_WIDTH * 2) - SeparacionEntreCirculos, 100);	
				break;
			case 3:
				Circulo3.UpdateTexture();
				Circulo3.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo3.setPosition(SCREEN_WIDTH / 2 - (CIRCLE_WIDTH / 2),	TamañoDeMargenes);
				break;
			case 4:
				Circulo4.UpdateTexture();
				Circulo4.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo4.setPosition(TamañoDeMargenes + CIRCLE_WIDTH + SeparacionEntreCirculos, 100);
				break;
			case 5:
				Circulo5.UpdateTexture();
				Circulo5.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo5.setPosition(TamañoDeMargenes, 200);	
				break;
			case 6:
				Circulo6.UpdateTexture();
				Circulo6.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo6.setPosition(100, SCREEN_HEIGHT - 500);		
				break;
			case 7:
				Circulo7.UpdateTexture();
				Circulo7.setSize(CIRCLE_WIDTH + 150, CIRCLE_HEIGHT + 150);
				Circulo7.setPosition(SCREEN_WIDTH / 2 - CIRCLE_WIDTH / 2 - 75, SCREEN_HEIGHT / 2 - CIRCLE_HEIGHT / 2);
				
				// ACTUALIZAR AQUI EL LABEL PARA SABER CUANTAS FICHAS TIENE EL PUCHERO ACTUALMENTE
				Multiplicador_lbl.setText("+" + Circulo7.getMultiplicador()*4);
				
				break;
			case 8:
				Circulo8.UpdateTexture();
				Circulo8.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo8.setPosition(100 + CIRCLE_WIDTH + SeparacionEntreCirculos, SCREEN_HEIGHT - 370);
				break;
			case 9:
				Circulo9.UpdateTexture();
				Circulo9.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo9.setPosition(SCREEN_WIDTH - TamañoDeMargenes * 2 - (CIRCLE_WIDTH * 2) - SeparacionEntreCirculos, SCREEN_HEIGHT - 370);
				break;
			case 10:
				Circulo10.UpdateTexture();
				Circulo10.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo10.setPosition(SCREEN_WIDTH - TamañoDeMargenes * 2 - CIRCLE_WIDTH, SCREEN_HEIGHT - 500);
				break;
			case 11:
				Circulo11.UpdateTexture();
				Circulo11.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo11.setPosition(SCREEN_WIDTH - TamañoDeMargenes - CIRCLE_WIDTH, 200);		
				break;		
			case 12:
				CantidadGanada = (Circulo7.getNumDeFichas()) + (Circulo7.getMultiplicador()*4);
				Multiplicador_lbl.setText("+0");
				System.out.println("Me acabo de llevar del puchero "+CantidadGanada);
				
				Circulo7.setCompletado(true);
				
				Circulo7.UpdateTexture();
				Circulo7.setSize(CIRCLE_WIDTH + 150, CIRCLE_HEIGHT + 150);
				Circulo7.setPosition(SCREEN_WIDTH / 2 - CIRCLE_WIDTH / 2 - 75, SCREEN_HEIGHT / 2 - CIRCLE_HEIGHT / 2);	
				
				break;
			}
		} else {
			// ESTOY EN MODO FINISH (tirada = circulo a 0, cantidadganada = numerodefichas)
			switch (NumDeCirculo) {
			case 2:
				CantidadGanada = (Circulo2.getNumDeFichas());
				System.out.println("Circulo2 FINALIZADO ! ");
				
				Circulo2.setCompletado(true);
				
				Circulo2.UpdateTexture();
				Circulo2.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo2.setPosition(SCREEN_WIDTH - TamañoDeMargenes - (CIRCLE_WIDTH * 2) - SeparacionEntreCirculos, 100);	
				break;				
				
			case 3:
				CantidadGanada = (Circulo3.getNumDeFichas());
				System.out.println("Circulo3 FINALIZADO ! ");
				
				Circulo3.setCompletado(true);
				Circulo3.UpdateTexture();
				Circulo3.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo3.setPosition(SCREEN_WIDTH / 2 - (CIRCLE_WIDTH / 2),	TamañoDeMargenes);
				break;
			case 4:
				CantidadGanada = (Circulo4.getNumDeFichas());
				System.out.println("Circulo4 FINALIZADO ! ");
				
				Circulo4.setCompletado(true);
				Circulo4.UpdateTexture();
				Circulo4.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo4.setPosition(TamañoDeMargenes + CIRCLE_WIDTH + SeparacionEntreCirculos, 100);
				break;
			case 5:
				CantidadGanada = (Circulo5.getNumDeFichas());
				System.out.println("Circulo5 FINALIZADO ! ");
				
				Circulo5.setCompletado(true);
				Circulo5.UpdateTexture();
				Circulo5.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo5.setPosition(TamañoDeMargenes, 200);	
				break;
			case 6:
				CantidadGanada = (Circulo6.getNumDeFichas());
				System.out.println("Circulo6 FINALIZADO ! ");
				
				Circulo6.setCompletado(true);
				Circulo6.UpdateTexture();
				Circulo6.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo6.setPosition(100, SCREEN_HEIGHT - 500);		
				break;
			case 7:
				CantidadGanada = (Circulo7.getNumDeFichas() + (Circulo7.getMultiplicador()*4));
				Multiplicador_lbl.setText("+0");
				System.out.println("Circulo7 FINALIZADO ! ");
				
				Circulo7.setCompletado(true);
				Circulo7.UpdateTexture();
				Circulo7.setSize(CIRCLE_WIDTH + 150, CIRCLE_HEIGHT + 150);
				Circulo7.setPosition(SCREEN_WIDTH / 2 - CIRCLE_WIDTH / 2 - 75, SCREEN_HEIGHT / 2 - CIRCLE_HEIGHT / 2);
				
				// ACTUALIZAR AQUI EL LABEL PARA SABER CUANTAS FICHAS TIENE EL PUCHERO ACTUALMENTE
				
				break;
			case 8:
				CantidadGanada = (Circulo8.getNumDeFichas());
				System.out.println("Circulo8 FINALIZADO ! ");
				
				Circulo8.setCompletado(true);
				Circulo8.UpdateTexture();
				Circulo8.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo8.setPosition(100 + CIRCLE_WIDTH + SeparacionEntreCirculos, SCREEN_HEIGHT - 370);
				break;
			case 9:
				CantidadGanada = (Circulo9.getNumDeFichas());
				System.out.println("Circulo9 FINALIZADO ! ");
				
				Circulo9.setCompletado(true);
				Circulo9.UpdateTexture();
				Circulo9.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo9.setPosition(SCREEN_WIDTH - TamañoDeMargenes * 2 - (CIRCLE_WIDTH * 2) - SeparacionEntreCirculos, SCREEN_HEIGHT - 370);
				break;
			case 10:
				CantidadGanada = (Circulo10.getNumDeFichas());
				System.out.println("Circulo10 FINALIZADO ! ");
				
				Circulo10.setCompletado(true);
				Circulo10.UpdateTexture();
				Circulo10.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo10.setPosition(SCREEN_WIDTH - TamañoDeMargenes * 2 - CIRCLE_WIDTH, SCREEN_HEIGHT - 500);
				break;
			case 11:
				CantidadGanada = (Circulo11.getNumDeFichas());
				System.out.println("Circulo11 FINALIZADO ! ");
				
				Circulo11.setCompletado(true);
				Circulo11.UpdateTexture();
				Circulo11.setSize(CIRCLE_WIDTH, CIRCLE_HEIGHT);
				Circulo11.setPosition(SCREEN_WIDTH - TamañoDeMargenes - CIRCLE_WIDTH, 200);		
				break;		
			case 12:
				CantidadGanada = (Circulo7.getNumDeFichas() + (Circulo7.getMultiplicador()*4)) + (Circulo7.getMultiplicador()*4) + (Circulo2.getNumDeFichas()) +
						(Circulo3.getNumDeFichas()) + (Circulo4.getNumDeFichas()) + (Circulo5.getNumDeFichas()) + (Circulo6.getNumDeFichas()) +
						(Circulo7.getNumDeFichas()) + (Circulo8.getNumDeFichas()) + (Circulo9.getNumDeFichas()) + (Circulo10.getNumDeFichas()) +
						(Circulo11.getNumDeFichas()) - 1;
				Multiplicador_lbl.setText("+0");
				System.out.println("Me acabo de llevar TODO:  "+CantidadGanada);
				
				AsignarYPosicionarCirculos (); // Esto es una salvajada, buscar forma de no usarlo
				
				Circulo2.setCompletado(true);
				Circulo3.setCompletado(true);
				Circulo4.setCompletado(true);
				Circulo5.setCompletado(true);
				Circulo6.setCompletado(true);
				Circulo7.setCompletado(true);
				Circulo8.setCompletado(true);
				Circulo9.setCompletado(true);
				Circulo10.setCompletado(true);
				Circulo11.setCompletado(true);				
				
				// Y cambio de pantalla
				
				break;
			}
			
				Puntuacion[TurnoDe] += CantidadGanada;
			
				switch (TurnoDe) {
				case 1:
					Puntuacion1_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
					break;
				case 2:
					Puntuacion2_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
					break;
				case 3:
					Puntuacion3_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
					break;
				case 4:
					Puntuacion4_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
					break;
				}				
		}		
	}
		
	public void ActualizarMarcadores () {
		
		
		
		// Mejorar condicion para no activar modo finish cada turno al llegar a 0
		if (Puntuacion[0]==0) {
			// No hay mas fichas para poner y estamos en modo FINISH :D
			FichasRestantes_lbl.setText("FINISH");
			FichasRestantes_lbl.setFontScale(2.8f);
			FichasRestantes_lbl.setPosition(SCREEN_WIDTH / 2 - 196, SCREEN_HEIGHT - TamañoDeMargenes - 70);	
			ModoFinish = true;
			
		} else {
			// Si las hay asi que resto 1 ficha y el juego sigue en version NORMAL			
			Puntuacion[0] --;
			FichasRestantes_lbl.setText(EstilizarPuntuacion(Puntuacion[0]));
		}
		
		if (!ModoFinish) {
			if (Circulo2.isCompletado()) {
				CantidadGanada = Circulo2.getNumDeCasillas();
				Circulo2.setCompletado(false);
			}
			if (Circulo3.isCompletado()) {
				CantidadGanada = Circulo3.getNumDeCasillas();
				Circulo3.setCompletado(false);
			}
			if (Circulo4.isCompletado()) {
				CantidadGanada = Circulo4.getNumDeCasillas();
				Circulo4.setCompletado(false);
			}
			if (Circulo5.isCompletado()) {
				CantidadGanada = Circulo5.getNumDeCasillas();
				Circulo5.setCompletado(false);
			}
			if (Circulo6.isCompletado()) {
				CantidadGanada = Circulo6.getNumDeCasillas();
				Circulo6.setCompletado(false);
			}
			if (Circulo7.isCompletado()) {									
				Circulo7.setCompletado(false);
			}
			if (Circulo8.isCompletado()) {
				CantidadGanada = Circulo8.getNumDeCasillas();
				Circulo8.setCompletado(false);
			}
			if (Circulo9.isCompletado()) {
				CantidadGanada = Circulo9.getNumDeCasillas();
				Circulo9.setCompletado(false);
			}
			if (Circulo10.isCompletado()) {
				CantidadGanada = Circulo10.getNumDeCasillas();
				Circulo10.setCompletado(false);
			}
			if (Circulo11.isCompletado()) {
				CantidadGanada = Circulo11.getNumDeCasillas();			
				Circulo11.setCompletado(false);
			}
			
			Puntuacion[TurnoDe] += CantidadGanada;
			
			switch (TurnoDe) {
			case 1:
				Puntuacion1_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
				break;
			case 2:
				Puntuacion2_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
				break;
			case 3:
				Puntuacion3_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
				break;
			case 4:
				Puntuacion4_lbl.setText(EstilizarPuntuacion(Puntuacion[TurnoDe]));
				break;
			}	
			
		} else {
			// ESTOY EN MODO FINISH
			
			
			
		}
		
		CantidadGanada = 0;
	}
	
	
	
	public String EstilizarPuntuacion (int i) {
		String PuntuacionMaqueada;		
		if (i<10) {
			PuntuacionMaqueada = "0" + i;			
		} else {
			PuntuacionMaqueada = "" + i;
		}
		return PuntuacionMaqueada;
	}
	
	public void SiguienteTurno () {		
		if (TurnoDe >= 4) {
			TurnoDe = 1;
		} else {
			TurnoDe++;
		}
		
		ResetStyles ();
		
		switch (TurnoDe) {
		case 1:
			NombrePlayer1.setStyle(CurrentTurnStyle);		
			Puntuacion1_lbl.setStyle(CurrentTurnStyle);
			break;
		case 2:
			NombrePlayer2.setStyle(CurrentTurnStyle);		
			Puntuacion2_lbl.setStyle(CurrentTurnStyle);
			break;
		case 3:
			NombrePlayer3.setStyle(CurrentTurnStyle);		
			Puntuacion3_lbl.setStyle(CurrentTurnStyle);
			break;
		case 4:
			NombrePlayer4.setStyle(CurrentTurnStyle);		
			Puntuacion4_lbl.setStyle(CurrentTurnStyle);
			break;
		}
		
		System.out.println("- TURNO DEL JUGADOR " + TurnoDe);
	}
	
	public void ResetStyles () {
		NombrePlayer1.setStyle(PassiveTurnStyle);		
		Puntuacion1_lbl.setStyle(PassiveTurnStyle);
		NombrePlayer2.setStyle(PassiveTurnStyle);		
		Puntuacion2_lbl.setStyle(PassiveTurnStyle);
		NombrePlayer3.setStyle(PassiveTurnStyle);		
		Puntuacion3_lbl.setStyle(PassiveTurnStyle);
		NombrePlayer3.setStyle(PassiveTurnStyle);		
		Puntuacion3_lbl.setStyle(PassiveTurnStyle);
		NombrePlayer4.setStyle(PassiveTurnStyle);		
		Puntuacion4_lbl.setStyle(PassiveTurnStyle);
	}
	
	
	
	public void renderizarJuego() {		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// OJO QUE PARA QUE SE MUESTREN LOS CAMBIOS EN LA CAMARA HAY QUE HACER UN cam.update();
		cam.update();

		// Combined indica que tipo de matriz tenemos en la camara
		game.batch.setProjectionMatrix(cam.combined);

		// Empezamos a dibujar
		game.batch.begin();
		
		DibujarTablero();
		DibujarCirculos();
		DibujarMarcadores();
		DibujarDados();
		
		// Y terminamos de dibujar
		game.batch.end();
	}
	
	// Proceso el listener de la pantalla
	public void procesarEntrada() {
		if (Gdx.input.justTouched()) {
			//cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			//if (Dado1.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {				
			ActualizarCirculo (Dado1.RollDice()+Dado2.RollDice());
			ActualizarDados ();
			ActualizarMarcadores ();
			
			SiguienteTurno();
			//}
		}
	}
}