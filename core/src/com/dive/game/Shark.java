package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

	public class Shark extends GameObject{
		
		private Sprite sprite;
		private Rectangle shape;
		private float sharkSpeed;
		boolean active;
		
		
		//kreiere Hai und ihr wird das bild, Größe des Bildes (width,height) und koordinaten übergeben
		public Shark(int xcord, int ycord, int width, int height, float sharkSpeed, Texture texture){
			
			sprite = new Sprite(texture);
			sprite.setPosition(xcord, ycord);
			sprite.setSize(width, height);
		
			shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
			
			this.sharkSpeed = sharkSpeed;
			active = false;
			type = ObjectType.SHARK;
		}
		
		public Rectangle getShape(){
			return shape;
		}
		
		public Sprite getSprite(){
			return sprite;
		}
		
		
		public float getSharkSpeed(){
			return sharkSpeed;
		}
		
		public boolean getActive(){
			return active;
		}
		
		public void setActive(boolean status){
			this.active = status;
		}
		
		public void moveShark(){
			sprite.translateX(getSharkSpeed());
			
		
		}

	}
