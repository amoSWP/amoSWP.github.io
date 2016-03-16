package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Plant extends GameObject {

	private int ycord;
	private float plantSpeed;

	public Plant(int xcord, int width, int height, float plantSpeed, Texture texture) {
		ycord = 40;

		sprite = new Sprite(texture);
		sprite.setPosition(xcord, ycord);
		sprite.setSize(width, height);
		
		this.plantSpeed = plantSpeed;

		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(),
				sprite.getHeight());

		active = false;
		type = ObjectType.PLANT;
	}
	
	public float getPlantSpeed(){
		return plantSpeed;
	}
	
	
	public void moveObject(){
		sprite.translateX(plantSpeed);
		System.out.println("move plant" + sprite.getX() + "  "+ plantSpeed);
}
}