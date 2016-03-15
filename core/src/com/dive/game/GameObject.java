package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {
	
	protected Sprite sprite;			//Hintergrund des Objekts, "die Textur"
	protected float[] acc;			//Ein zweidimensionaler Vektor, welcher die Beschleunigung des Objektes angibt
	protected Rectangle shape;		//die Kollisionsform des Objekts
	protected ObjectType type;		//Typ des Objects (Shark, Plant, etc.)
	protected float width, height;	//Außmaße des  Objekts in % des Bildschirms
	protected GameScreen screen;		//Spielbereich festlegen
	
	
	public void draw(Batch batch){	//zeichnet das Objekt auf den gegebenen batch
		sprite.draw(batch);
	}
	
	public Rectangle getShape(){
		return shape;
	}
	
	public ObjectType getType(){
		return type;
	}
	
	public void moveObject(float groundSpeed){
		sprite.translateX(groundSpeed*screen.width);
	}
	
	public void resize(GameScreen screen){
		
		
	}
	
	
	
	
	
	

}
