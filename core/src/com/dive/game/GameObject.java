package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {
	
	private Sprite sprite;			//Hintergrund des Objekts, "die Textur"
	private float[] acc;			//Ein zweidimensionaler Vektor, welcher die Beschleunigung des Objektes angibt
	private Rectangle shape;		//die Kollisionsform des Objekts
	private ObjectType type;		//Typ des Objects (Shark, Plant, etc.)
	private float width, height;	//Außmaße des  Objekts in % des Bildschirms
	private GameScreen screen;		//Spielbereich festlegen
	
	
	public void draw(Batch batch){	//zeichnet das Objekt auf den gegebenen batch
		sprite.draw(batch);
	}
	
	public Rectangle getShape(){
		return shape;
	}
	
	public ObjectType getType(){
		return type;
	}
	
	public void resize(GameScreen screen){
		
		
	}
	
	
	
	
	
	

}
