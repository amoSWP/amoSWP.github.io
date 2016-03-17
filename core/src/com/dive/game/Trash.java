package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Trash extends GameObject {

	private Sprite[] sprites;
	private Random rand;
	private int sizeTrash;

	// kreiere Müll und ihr wird das Bild, Größe des Bildes (width,height) und
	// Koordinaten übergeben
	public Trash(int xcord, int ycord) {
		// initialize random variable, speed in relation to background
		rand = new Random();
		acc = new float[] { 0, 0 };

		// array with sprites to choose from set of random textures
		sprites = new Sprite[] { new Sprite(Assets.getInstance().trash1),
				new Sprite(Assets.getInstance().trash2) , new Sprite(Assets.getInstance().trash3)};

		// set texture, size
		setRandomTexture();

		// set position to the right of the screen at arbitrary height
		sprite.setPosition(xcord, ycord);
		
		// rectangle behind sprite to detect collisions
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(),
				sprite.getHeight());
		
		//assign other attributes
		active = false;
		type = ObjectType.TRASH;
	}

	public void moveObject(float width, float height, float deltaTime,
			float gameSpeed) {
		// Bewegung des Hais, Hintergrund + eigene Geschwindigkeit
		float yTranslate = acc[1] * height * deltaTime;
		if (yTranslate + sprite.getY() < 20) {
			yTranslate = -sprite.getY() + 20;
		}
		sprite.translate(-width * deltaTime * (gameSpeed + acc[0]), yTranslate);

		// bewegt Feld hinter dem Hai für Kollisionserkennung
		shape.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight()
				* 0.18f);
	}

	//method to assign texture and size of sprite
	public void setRandomTexture() {
		int i = rand.nextInt(3);
		sprite = sprites[i];
		sizeTrash = 20 + rand.nextInt(30);
		if(i==2){
			sprite.setSize(sizeTrash + 80, sizeTrash+ 40);
		}
		sprite.setSize(sizeTrash, sizeTrash);
	}
}
