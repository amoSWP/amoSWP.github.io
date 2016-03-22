package com.dive.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GasBottle extends GameObject {

	private int ycord;
	private int sizeGasBottle;

	public GasBottle(int xcord) {
		ycord = 30;
		acc = new float[]{0,0};
		sizeGasBottle = 70;
		
		this.sprite = new Sprite(Assets.getInstance().gasBottle);
		
		sprite.setSize(sizeGasBottle, sizeGasBottle);
		sprite.setPosition(xcord, ycord);
		
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
		shape.setPosition(sprite.getX(), sprite.getY());
		
		active = false;
		type = ObjectType.GASBOTTLE;

	}
	
	
}