package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Parallax {
	
	private Background top,middle,bottom;
	private Texture tops,middles,bottoms;
	
	public Parallax(float gamespeed){
		// load Sprites from Assetmanager
		tops = Assets.getInstance().backgroundTop;
		middles = Assets.getInstance().backgroundMiddle;
		bottoms = Assets.getInstance().backgroundBottom;
		// create Backgroundobjects
		top = new Background(tops, (0.7f * gamespeed));
		middle = new Background(middles, 0.4f*gamespeed);
		bottom = new Background(bottoms, (gamespeed));
	}

	public void setSpeed(float gamespeed){
		top.setSpeed(0.7f *gamespeed);
		middle.setSpeed(0.4f*gamespeed);
		bottom.setSpeed(gamespeed);
	}
	
	public void draw(Batch batch){
		middle.draw(batch);
		top.draw(batch);
		bottom.draw(batch);
	}
	
	public void move(float deltatime){
		top.move(deltatime);
		middle.move(deltatime);
		bottom.move(deltatime);
	}
	
	public void setIdle(){
		setSpeed(0.03f);
	}
	
}
