package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

	public class Ente {
		
		Sprite sprite;
		float enteSpeed;
		boolean active;
		
		//kreiere Ente und ihr wird das bild, Größe des Bildes (width,height) und koordinaten übergeben
		public Ente(int xcord, int ycord, int width, int height, float enteSpeed, Texture texture){
			sprite = new Sprite(texture);
			sprite.setPosition(xcord, ycord);
			sprite.setSize(width, height);
			this.enteSpeed = enteSpeed;
			active = false;

		}
		
		public boolean getActive(){
			return active;
		}
		
		public void setActive(boolean status){
			active = status;
		}
		
		
		public float getEnteSpeed(){
			return enteSpeed;
		}
		
		public void moveEnte(){
			sprite.translateX(getEnteSpeed());
			
//			// gets duck back to original position once its left the screen
//			if(sprite.getX() < -sprite.getWidth()){
//				// sprite.dispose();
//				sprite.setPosition(xcord, ycord);}
			
		}

	}
