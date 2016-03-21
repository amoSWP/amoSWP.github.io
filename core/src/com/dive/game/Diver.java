package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
public class Diver {
	
	public float[] v;
	public float maxSpeed, maxSpeedOrigin, decay, y1,h1,x2,y2,w2,h2;
	private Rectangle[] shape;
	private Sprite sprite;
	private Touchpad joystick;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	private Stage stage;
	private Air air;
	
	public Diver(Texture texture,int width, int height,int maxSpeed){

		maxSpeedOrigin = this.maxSpeed = maxSpeed;
		
		v = new float[]{0,0};
		air = new Air(500);
		decay = 0.9f;
		
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
		sprite.setPosition(0,960-sprite.getHeight());
		// set shapes for collision
		shape = new Rectangle[2];
		// calculate size for bigger rectangle
		y1 = sprite.getY()+ 0.2f*sprite.getHeight();
		h1 = sprite.getHeight()*0.62f;
		shape[0] = new Rectangle(sprite.getX(), y1, sprite.getWidth(), h1);
		// calculate size for head
		x2 = sprite.getX() + sprite.getWidth()*0.71f;
		y2 = sprite.getY() + sprite.getHeight()*0.82f;
		w2 = sprite.getWidth()*0.18f;
		h2 = sprite.getHeight()*0.17f;
		shape[1] = new Rectangle(x2, y2, w2, h2);
		
	}
	
	public void move(float deltaTime, boolean Android){

		//Bewegungssteuerung
		if(Gdx.input.isKeyPressed(Input.Keys.UP))		{v[1]+=maxSpeed;}		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))		{v[1]-=maxSpeed;}		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))		{v[0]-=maxSpeed*2;}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))	{v[0]+=maxSpeed;}
		
		//Bewegung normieren (durch Maximalgeschwindigkeit begrenzen)
		norm();
		
		//Grenzen am BildschirmRand:
		//x-Grenze
		float xTranslate = v[0]*deltaTime;
		if(xTranslate+sprite.getX()+sprite.getWidth() > 1920){
			xTranslate = 1920 - (sprite.getX()+sprite.getWidth());
		}
		if(xTranslate+sprite.getX() < 0){
			xTranslate = -sprite.getX();
		}
		//y-Grenze
		float yTranslate = v[1]*deltaTime;
		if(yTranslate+sprite.getY()+sprite.getHeight() > 960){
			yTranslate = 960 - (sprite.getY()+sprite.getHeight());
		}
		if(yTranslate+sprite.getY() < 0){
			yTranslate = -sprite.getY();
		}
		
		//Diver bewegen
		sprite.translate(xTranslate, yTranslate);
		y1 = sprite.getY()+ 0.2f*sprite.getHeight();
		x2 = sprite.getX() + sprite.getWidth()*0.71f;
		y2 = sprite.getY() + sprite.getHeight()*0.82f;
		shape[0].setPosition(sprite.getX(), y1);
		shape[1].setPosition(x2, y2);
		
		v[0]*=decay;
		v[1]*=decay;
		
	}

	public void moveonjoystick(float x,float y){
		if( x < 0){
			v[0] += 2*x*maxSpeed;
		}else{
		    v[0] += x*maxSpeed;
		}
		    v[1] += y*maxSpeed;
	}

	public void draw(Batch batch){
		sprite.draw(batch);
		air.draw(batch);
	}

	public Rectangle[] getShape(){
		return this.shape;
	}
	
	public Sprite getSprite(){
		return this.sprite;
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

	public void refresh() {
		maxSpeed = maxSpeedOrigin;
		air.catchBreath();
	}

	public void slow() {
		maxSpeed = maxSpeedOrigin*0.2f;
		air.setBreath(1000);
	}

	public void breathe(float deltaTime) {
		air.breathe(deltaTime);
	}
	
	public void breathe(int k) {
		air.breathe(k);
	}

	public boolean hasAir() {
		return (air.getAir() > 0);
	}

	public void recover() {
		air.setBreath(-2000);
	}
	
	public void setBreath(int breath){
		air.setBreath(breath);
	}
	
	public void reset(){
		air.reset();
		sprite.setPosition(0,960-sprite.getHeight());
		y1 = sprite.getY()+ 0.2f*sprite.getHeight();
		x2 = sprite.getX() + sprite.getWidth()*0.71f;
		y2 = sprite.getY() + sprite.getHeight()*0.82f;
		shape[0].setPosition(sprite.getX(), y1);
		shape[1].setPosition(x2, y2);
	}

}