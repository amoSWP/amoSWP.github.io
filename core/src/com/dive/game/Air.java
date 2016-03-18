package com.dive.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Air {

	private int air;
	private Sprite sprite;
	private int breath, breathOrigin;
	
	public Air(int breathe){
		air = 10000;
		breathOrigin = this.breath = breathe;
		
		sprite = new Sprite(Assets.getInstance().air);
		sprite.setPosition(0, 0);
		sprite.setSize(1920, 20);
	}
	
	public void breathe(float deltaTime){
		air = (int) Math.max(0, air-deltaTime*breath);
		air = Math.min(10000, air);
		sprite.setSize(1920*(air/10000.0f), 20);
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
	
	public void catchBreath(){
		breath = breathOrigin;
	}
	
	public void setBreath(int breath){
		this.breath = breath;
	}

	public void reset() {
		air = 10000;
		
	}
	
	
}
