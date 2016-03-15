package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

	public class Ente {
		
		private Sprite sprite;
		private Rectangle shape;
		private float enteSpeed;
		boolean active;
		
		//kreiere Ente und ihr wird das bild, Größe des Bildes (width,height) und koordinaten übergeben
		public Ente(int xcord, int ycord, int width, int height, float enteSpeed, Texture texture){
			
			sprite = new Sprite(texture);
			sprite.setPosition(xcord, ycord);
			sprite.setSize(width, height);
		
			shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
			
			this.enteSpeed = enteSpeed;
			active = false;

		}
		
		public Rectangle getShape(){
			return shape;
		}
		
		public Sprite getSprite(){
			return sprite;
		}
		
		
		public float getEnteSpeed(){
			return enteSpeed;
		}
		
		public boolean getActive(){
			return active;
		}
		
		public void setActive(boolean status){
			this.active = status;
		}
		
		public void moveEnte(){
			sprite.translateX(getEnteSpeed());
			
		
		}

	}
