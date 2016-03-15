package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Diver {
	
	private int air;
	private float[] v;
	private float maxSpeed, decay, windowWidth, windowHeight;
	//private Polygon shape;
	private Rectangle shape;
	private Sprite sprite;
	
	public Diver(Texture texture,int width, int height, int startY, int maxSpeed){

		this.maxSpeed = maxSpeed;
		
		air = 100;
		v = new float[]{0,0};
		decay = 0.9f;
		
		sprite = new Sprite(texture);
		sprite.setY(startY);
		sprite.setSize(width, height);
		
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
		
	}
	
	public void onKeystroke(){
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP) && (sprite.getY() < windowHeight-sprite.getHeight())){
			v[1]+=maxSpeed;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && (sprite.getY()>0)){
			v[1]+=-maxSpeed;
		}
		else{
			v[1] = 0;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && (sprite.getX()>0)){
			v[0]+=-maxSpeed;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (sprite.getX() < windowWidth -sprite.getWidth())){
			v[0]+=maxSpeed;
		}
		
		norm();
		
	}
	
	public void setWindow(float width, float height){
		windowWidth = width;
		windowHeight = height;
	}
	
	public void draw(Batch batch, float deltaTime){
		
		sprite.draw(batch);
		
		float xTranslate = v[0]*deltaTime;
		if(xTranslate+sprite.getX()+sprite.getWidth() > windowWidth){
			xTranslate = windowWidth - (sprite.getX()+sprite.getWidth());
		}
		if(xTranslate+sprite.getX() < 0){
			xTranslate = -sprite.getX();
		}
		
		sprite.translate(xTranslate, v[1]*deltaTime);
		shape.setPosition(sprite.getX(), sprite.getY());
		
		v[0]*=decay;
		
	}

	public Rectangle getShape(){
		return this.shape;
	}
	
	private void norm(){
		float s = this.speed();
		if(s>maxSpeed){
			v[0]*=maxSpeed/s;
			v[1]*=maxSpeed/s;
		}
	}
	
	private float speed(){
		return (float) Math.sqrt(v[0]*v[0]+v[1]*v[1]);
	}

}