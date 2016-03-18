package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

	public class Shark extends GameObject{
		
		private float x,y;
		private int sizeShark;
		private Random rand;
		
		
		//kreiere Hai und ihr wird das bild, Größe des Bildes (width,height) und koordinaten übergeben
		public Shark(int xcord, int ycord){
			// initialize random variable, speed in relation to background
			rand = new Random();
			acc = new float[]{0.1f, 0};
			sprite = new Sprite(Assets.getInstance().shark);
			
			// set size of shark
			reset();
			
			// set position of shark
			sprite.setPosition(xcord, ycord);
			
			shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight()*0.5f);
			shape.setPosition(sprite.getX(), sprite.getY());
			
			active = false;
			type = ObjectType.SHARK;
		}
		
		public Rectangle getShape(){
			return shape;
		}
		
		public void moveObject(float deltaTime, float gameSpeed){
			//Bewegung des Hais, Hintergrund + eigene Geschwindigkeit
			sprite.translate(-1920*deltaTime*(gameSpeed+acc[0]), acc[1]*1080*deltaTime);
			// System.out.println("shark: " + -1920*deltaTime*(gameSpeed+acc[0]));
			//bewegt Feld hinter dem Hai für Kollisionserkennung
			shape.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight()*0.18f);
		}

	
	public void reset(){
		sizeShark = 100 + rand.nextInt(120);
		sprite.setSize(sizeShark + 120, sizeShark);
	}
	
	}
