package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Parallax {
	
	private Background top,middle,bottom;
	private Texture tops,middles,bottoms;
	
	public Parallax(float gamespeed, GameScreen screen){
		// load Sprites from Assetmanager
		tops = Assets.getInstance().backgroundTop;
		middles = Assets.getInstance().backgroundMiddle;
		bottoms = Assets.getInstance().backgroundBottom;
		// create Backgroundobjects
		top = new Background(tops, (2.5f * gamespeed), screen);
		middle = new Background(middles, gamespeed, screen);
		bottom = new Background(bottoms, (2.5f * gamespeed), screen);
	}

	public void setSpeed(float gamespeed){
		top.setSpeed(gamespeed);
		middle.setSpeed(gamespeed);
		bottom.setSpeed(gamespeed);
	}
	
	public void draw(Batch batch){
		top.draw(batch);
		middle.draw(batch);
		bottom.draw(batch);
	}
	
	public void move(float deltatime){
		top.move(deltatime);
		middle.move(deltatime);
		bottom.move(deltatime);
	}
	
	public void resize(){
		top.resize();
		middle.resize();
		bottom.resize();
	}
	
}
