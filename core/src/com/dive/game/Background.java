package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Background {

	private Sprite sprite1,sprite2;
	private float speed, oldwidth;
	
	public Background(Texture sprite, float speed){
		// set sprites
		sprite1 = new Sprite(sprite);
		sprite2 = new Sprite(sprite);
		// set size of sprites
		sprite1.setSize(1920, 1080);
		sprite2.setSize(1920, 1080);
		// set position of screens
		sprite1.setX(0);
		sprite2.setX(1920);
		// set speed
		this.speed = speed;
        
	}
	
	public void move (float deltatime){
		// move sprites
		sprite1.translateX(-speed*1920*deltatime);
		sprite2.translateX(-speed*1920*deltatime);
		// if sprites out of bounds set their position to the right of the other sprite
		if (sprite1.getX() + sprite1.getWidth() < 0) {
			sprite1.setX(sprite2.getX() + sprite2.getWidth());
		}
		if (sprite2.getX() + sprite2.getWidth() < 0) {
			sprite2.setX(sprite1.getX() + sprite1.getWidth());
		}
		
	}
	
	public void setSpeed(float gamespeed){
		speed = gamespeed;
	}

	public void draw(Batch batch){
		this.sprite1.draw(batch);
		this.sprite2.draw(batch);
	}
	
}


