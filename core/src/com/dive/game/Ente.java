package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

	public class Ente {
		
		Sprite sprite;
		float enteSpeed;
		
		public Ente(int xcord, int ycord, int width, int height, float enteSpeed, Texture texture){
			sprite = new Sprite(texture);
			sprite.setPosition(xcord, ycord);
			sprite.setSize(width, height);
			this.enteSpeed = enteSpeed;

		}
		
		public Sprite getSprite(){
			return sprite;
		}
		
		public float getEnteSpeed(){
			return enteSpeed;
		}
		
		public void moveEnte(Ente ente, int xcord, int ycord){
			getSprite().translateX(getEnteSpeed());
			
			// gets duck back to original position once its left the screen
			if(sprite.getX() == -sprite.getWidth()){
				sprite.setPosition(xcord, ycord);}
			
		}

	}
