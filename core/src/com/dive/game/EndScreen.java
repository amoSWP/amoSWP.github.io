package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EndScreen {
	
	private Sprite restart;
	private float score;
	
	
	public EndScreen(float score){
		
		restart = new Sprite(Assets.getInstance().restartButton);
		restart.setBounds(500, 200, 920, 178);
		this.score = score;
		
	}
	
	public void draw(Batch batch){
		restart.draw(batch);
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public Sprite getRestart(){
		return restart;
	}
	
	public void setSprite(Texture tex){
		restart = new Sprite(tex);
		restart.setBounds(500, 200, 920, 178);
	}

}
