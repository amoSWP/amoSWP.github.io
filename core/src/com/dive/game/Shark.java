package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shark extends GameObject{
	
	public Shark(Sprite sprite, float[] acc, ObjectType type, float x, float y, float width, float height, GameScreen screen){
		this.sprite = sprite;
		this.acc = acc;
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.screen = screen;
		
		this.sprite.setPosition(x, y);
		this.sprite.setSize(screen.width*width, screen.height*height);
		
		
		
	}

}
