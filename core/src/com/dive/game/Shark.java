package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

	public class Shark extends GameObject{
		
		private float sharkSpeed,x,y;
		
		
		//kreiere Hai und ihr wird das bild, Größe des Bildes (width,height) und koordinaten übergeben
		public Shark(int xcord, int ycord, int width, int height, float sharkSpeed, Texture texture){
			
			sprite = new Sprite(texture);
			sprite.setPosition(xcord, ycord);
			sprite.setSize(width, height);
			
			shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight()*0.5f);
			shape.setPosition(sprite.getX(), sprite.getY());
			
			this.sharkSpeed = sharkSpeed;
			active = false;
			type = ObjectType.SHARK;
		}
		
		public Rectangle getShape(){
			return shape;
		}
		
		
		
		public float getSharkSpeed(){
			return sharkSpeed;
		}
		
		public void moveObject(float width, float deltaTime, float gameSpeed){
			sprite.translateX(getSharkSpeed());
			x = sprite.getX();
			y = sprite.getY() + sprite.getHeight()*0.5f;
			shape.setPosition(x, y);
		}

	}
