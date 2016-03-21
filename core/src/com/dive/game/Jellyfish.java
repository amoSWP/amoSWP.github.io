package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Jellyfish extends GameObject {
	
		private int sizeJellyfish;
		private Random rand;
		
		public Jellyfish(int xcord, int ycord){
			
			// initialize random variable, speed in relation to background
			acc = new float[]{0, 0.2f};
			rand = new Random();
			sprite = new Sprite(Assets.getInstance().jellyfish);
						
			// set position of jellyfish
			sprite.setPosition(xcord, ycord);
						
			// set shape of jellyfish for collision
			shape = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
			shape.setPosition(sprite.getX(), sprite.getY());
						
			// set size of jellyfish
			reset();
						
			active = false;
			type = ObjectType.JELLYFISH;
		}

		public void moveObject(float deltaTime, float gameSpeed){
			// if jellyfish is at upper or lower border reverse the moving direction
			if(sprite.getY()+ sprite.getHeight() >= 860 && acc[1] > 0){
				acc[1] = -acc[1];
			}
			if(sprite.getY() <= 180 && acc[1] < 0){
				acc[1] = -acc[1];
			}
			//Bewegung der Qualle, Hintergrundgeschwindigkeit
			sprite.translate(-1920*deltaTime*gameSpeed, acc[1]*1080*deltaTime);
			//bewegt Feld auf der Qualle für Kollisionserkennung
			shape.setPosition(sprite.getX(), sprite.getY());
		}
		
		
		
		public void reset(){
			sizeJellyfish = 100;
			sprite.setSize(sizeJellyfish, sizeJellyfish*1.3f);
			shape.setSize(sprite.getWidth(), sprite.getHeight());
			shape.setPosition(sprite.getX(), sprite.getY());
		}

}
