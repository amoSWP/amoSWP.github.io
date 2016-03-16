package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Air {

	private int air;
	private Sprite sprite;
	private GameScreen screen;
	private int breath;
	
	public Air(GameScreen screen, int breathe){
		air = 10000;
		this.screen = screen;
		this.breath = breathe;
		
		sprite = new Sprite(Assets.getInstance().air);
		sprite.setPosition(0, 0);
		sprite.setSize(screen.width, screen.height*0.02f);
	}
	
	public void breathe(float deltaTime){
		air = (int) Math.max(0, air-deltaTime*breath);
		sprite.setSize(screen.width*(air/10000.0f), screen.height*0.02f);
	}
	
	public void breathe(int k){
		air = Math.max(0, air-k);
	}
	
	public void draw(Batch batch){
		sprite.draw(batch);
	}
	
	public int getAir(){
		return air;
	}
	
	
}
