package pucherin.thegame.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Circulo extends Sprite {

    private TextureAtlas AtlasCirculo;
    private int NumDeCasillas;    
    private int NumDeFichas;    
    private int Multiplicador; // Usado para contar las vueltas que lleva el circulo 7
    private boolean Completado;
    private String[] RegionNames = {"0000","0001","0002","0003","0004", "0005","0006","0007","0008", "0009", "0010", "0011"};
	
    public Circulo (int NumDeCasillas) {    	 
    	this (new Builder(NumDeCasillas), NumDeCasillas);    	
    }		
    
    private Circulo (Builder builder, int NumDeCasillas) {
    	super (new Sprite(builder.Textura().createSprite("0000")));
    	AtlasCirculo = builder.Textura();
    	Completado = false;
    	this.NumDeCasillas = NumDeCasillas;
    	NumDeFichas = 0;
    	Multiplicador = 0;
    }    	
	 
	public void UpdateTexture() {	
		if (NumDeCasillas == 7) {
			if (Completado) { // Esto quiere decir que acabo de sacar un 12
				
				// Si no modofinish deberia ser 1 el NumDeFichas <-------------------------------
				NumDeFichas = 0;
				Multiplicador = 0;
			} else {
				if (NumDeFichas<4){
					NumDeFichas++;						
				} else {
					NumDeFichas = 1;
					Multiplicador++; // Veces que se ha completado el 7 con 4 fichas					
				}	
			}								
		
		System.out.println("Actualmente el puchero vale: Fichas->"+NumDeFichas+ "    +   Multiplicador->"+Multiplicador+"*4");			
			
		} else {
			if (Completado) {				
				NumDeFichas = 0; // Si se ha dado por completado es que me lleve lo del circulo en modo FINISH
			} else {
				if (NumDeFichas<NumDeCasillas) {
					NumDeFichas++;
				} else if (NumDeFichas>=NumDeCasillas) {	
					// Te llevas el circulo que esta lleno y pones 1 ficha en el
					NumDeFichas = 1;
					System.out.println("Circulo"+ NumDeCasillas +" completado !! (deberia de haber 1 ficha ahora)");
					setCompletado(true);
				}
			}
		}
		
		setTexture(AtlasCirculo.findRegion(RegionNames[NumDeFichas]).getTexture());		
	}		

	public int getNumDeCasillas() {
		return NumDeCasillas;
	}

	public void setNumDeCasillas(int NumDeCasillas) {
		this.NumDeCasillas = NumDeCasillas;
	}

	public int getNumDeFichas() {
		return NumDeFichas;
	}

	public void setNumDeFichas(int NumDeFichas) {
		this.NumDeFichas = NumDeFichas;
	}
	
    public boolean isCompletado() {
		return Completado;
	}

	public void setCompletado(boolean Completado) {
		this.Completado = Completado;
	}

	public int getMultiplicador() {
		return Multiplicador;
	}

	public void setMultiplicador(int Multiplicador) {
		this.Multiplicador = Multiplicador;
	}




	public static class Builder {
    	
    	private int NumDeCasillas;
    	private TextureAtlas Textura;        

        public Builder(int NumDeCasillas) {
            this.NumDeCasillas = NumDeCasillas;
        }  
        
        public TextureAtlas Textura () {
        	switch (NumDeCasillas) {
    		case 2:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo2/circulo2.atlas"));				
    			break;
    		case 3:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo3/circulo3.atlas"));			
    			break;
    		case 4:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo4/circulo4.atlas"));			
    			break;
    		case 5:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo5/circulo5.atlas"));			
    			break;
    		case 6:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo6/circulo6.atlas"));			
    			break;
    		case 7:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo7/circulo7.atlas"));			
    			break;
    		case 8:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo8/circulo8.atlas"));			
    			break;
    		case 9:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo9/circulo9.atlas"));			
    			break;
    		case 10:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo10/circulo10.atlas"));			
    			break;
    		case 11:
    			Textura = new TextureAtlas(Gdx.files.internal("data/circulos/circulo11/circulo11.atlas"));			
    			break;
    		}	    		
        	return Textura; 
        }            
    }
}