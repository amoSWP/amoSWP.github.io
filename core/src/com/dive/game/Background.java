package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Background {

	private Sprite sprite1,sprite2;
	private float speed, oldwidth;
	private GameScreen screen;
	
	public Background(Texture sprite, float speed, GameScreen screen){
		// set sprites
		sprite1 = new Sprite(sprite);
		sprite2 = new Sprite(sprite);
		// set size of sprites
		sprite1.setSize(screen.width, screen.height);
		sprite2.setSize(screen.width, screen.height);
		// set position of screens
		sprite1.setX(screen.x);
		sprite2.setX(screen.x + screen.width);
		// set speed
		this.speed = speed;
		this.screen = screen;
        
	}
	
	public void move (float deltatime){
		// move sprites
		sprite1.translateX(-speed*screen.width*deltatime);
		sprite2.translateX(-speed*screen.width*deltatime);
		// if sprites out of bounds set their position to the right of the other sprite
		if (sprite1.getX() + sprite1.getWidth() < 0) {
			sprite1.setX(sprite2.getX() + sprite2.getWidth());
		}
		if (sprite2.getX() + sprite2.getWidth() < 0) {
			sprite2.setX(sprite1.getX() + sprite1.getWidth());
		}
		
	}
	
	public void resize(){
		// calculate x translate for position
		float newx = oldwidth/screen.width;
		float newx1 = sprite1.getX()*newx;
		float newx2 = sprite2.getX()*newx;
		// set new width and height values
		sprite1.setSize(screen.width, screen.height);
		sprite2.setSize(screen.width, screen.height);
		// set new x positions
		if(sprite1.getX() < sprite2.getX()){
			sprite1.setX(newx1);
			sprite2.setX(sprite1.getX() + screen.width);
		}else{
			sprite2.setX(newx2);
			sprite1.setX(sprite2.getX() + screen.width);
		}
		// set new y positions
		sprite1.setY(screen.y);
		sprite2.setY(screen.y);
		// set oldwidth
		oldwidth = screen.width;
		
	}
	
	public void setSpeed(float gamespeed){
		speed = gamespeed;
	}

	public void draw(Batch batch){
		this.sprite1.draw(batch);
		this.sprite2.draw(batch);
	}
	
}


