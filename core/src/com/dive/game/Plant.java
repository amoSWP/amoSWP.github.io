package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Plant extends GameObject {

	private int ycord;

	public Plant(int xcord, int width, int height, Texture texture) {
		ycord = 20;

		this.sprite = new Sprite(texture);
		sprite.setPosition(xcord, ycord);
		sprite.setSize(width, height);
		
		acc = new float[]{0,0};

		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
		shape.setPosition(sprite.getX(), sprite.getY());

		active = false;
		type = ObjectType.PLANT;
	}
}