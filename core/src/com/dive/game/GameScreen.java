package com.dive.game;

public class GameScreen {

	public float width, height, x, y;	//Darstellungsbereich angeben
	
	public GameScreen(float width, float height, float x, float y){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		
	}
	
	public float top(){
		return y+height;
	}
	
	public float right(){
		return x+width;
	}
	
}
