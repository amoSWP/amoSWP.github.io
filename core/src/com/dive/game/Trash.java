package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Trash extends GameObject{
	
	// private Sprite sprite;
	private Rectangle shape;
	private float trashSpeed;
	
	
	//kreiere Müll und ihr wird das Bild, Größe des Bildes (width,height) und Koordinaten übergeben
	public Trash(int xcord, int ycord, int width, int height, float trashSpeed, Texture texture){
		
		sprite = new Sprite(texture);
		sprite.setPosition(xcord, ycord);
		sprite.setSize(width, height);
	
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
		
		this.trashSpeed = trashSpeed;
		active = false;
		type = ObjectType.TRASH;
	}
	
	public Rectangle getShape(){
		return shape;
	}
	
	
	
	public float getTrashSpeed(){
		return trashSpeed;
	}
	
	
	public void moveObject(){
		sprite.translateX(getTrashSpeed());
		shape.setPosition(sprite.getX(), sprite.getY());

	
	}

}

