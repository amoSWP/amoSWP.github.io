package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {
	
	protected Sprite sprite;			//Hintergrund des Objekts, "die Textur"
	protected float[] acc;				//Ein zweidimensionaler Vektor, welcher die Beschleunigung des Objektes angibt
	protected Rectangle shape;			//die Kollisionsform des Objekts
	protected ObjectType type;			//Typ des Objects (Shark, Plant, etc.)
	protected float x,y;				//relative Koordinaten des Objekts (% des Gamescreens)
	protected float width, height;		//relative Größe des Objekts (% des Gamescreens)
	protected boolean active;			//staus des Spielobjekts
	
	
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
	
	public boolean getActive(){
		return active;
	}
	
	public void setActive(boolean status){
		active = status;
	}
	
	public void resize(float scale){
		
		
	}
	
	public void moveObject(float width, float deltaTime, float gameSpeed){
		sprite.translateX(-width*deltaTime*gameSpeed);
		shape.setPosition(sprite.getX(), sprite.getY());
	}
	
	
	
	
	
	

}
