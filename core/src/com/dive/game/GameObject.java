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
	protected boolean active;
	
	
	public void draw(Batch batch){	//zeichnet das Objekt auf den gegebenen batch
		sprite.draw(batch);
	}
	
	public Rectangle getShape(){
		return shape;
	}
	
	public ObjectType getType(){
		return type;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setActive(boolean status){
		this.active = status;
	}
	
	public boolean getActive(){
		return active;
	}
	
	public void moveObject(){
		
	}
	
	public void resize(GameScreen screen){
		
		
	}
	
	
	
	
	
	

}
