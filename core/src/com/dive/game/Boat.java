package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public class Boat extends GameObject{
	
	private Random rand;
	private int sizeBoat, ycord;

	// kreiere Müll und ihr wird das Bild, Größe des Bildes (width,height) und
	// Koordinaten übergeben
	public Boat (int xcord) {
		
		ycord = 920;
		// initialize random variable, speed in relation to background
		rand = new Random();
		acc = new float[] { 0, 0 };
		sprite = new Sprite(Assets.getInstance().boat);

		// set texture, size
		reset();

		// set position to the right of the screen at arbitrary height
		sprite.setPosition(xcord, ycord);
		
		// rectangle behind sprite to detect collisions
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(),
				sprite.getHeight());
		
		//assign other attributes
		active = false;
		type = ObjectType.SHARK;
	}

//	public void moveObject(float deltaTime,
//			float gameSpeed) {
//		
//		sprite.translate(-1920 * deltaTime * (gameSpeed + acc[0]), 0);
//		
//		// bewegt Feld hinter dem Hai für Kollisionserkennung
//		shape.setPosition(sprite.getX(), sprite.getY());
//	}

	//method to assign texture and size of sprite
	public void reset(){
		sizeBoat = 60 + rand.nextInt(40);
		sprite.setSize(sizeBoat+120, sizeBoat + 60);
	}
}