package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;

public class Diver {
	
	private int air;
	private float[] v;
	private float maxSpeed, decay;
	private Rectangle shape;
	private Sprite sprite;
	private GameScreen screen;
	private Touchpad joystick;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	private Stage stage;
	
	public Diver(Texture texture,int width, int height, int startY, int maxSpeed, GameScreen screen){

		this.maxSpeed = maxSpeed;
		this.screen = screen;
		
		air = 100;
		v = new float[]{0,0};
		decay = 0.9f;
		
		sprite = new Sprite(texture);
		sprite.setY(startY);
		sprite.setSize(width, height);
		
		shape = new Rectangle(0f, sprite.getY(), sprite.getWidth(), sprite.getHeight());
		
	}
	
	public void move(float deltaTime){
		
		//Bewegungssteuerung
		if(Gdx.input.isKeyPressed(Input.Keys.UP))		{v[1]+=maxSpeed;}		
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))		{v[1]-=maxSpeed;}		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))		{v[0]-=maxSpeed;}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))	{v[0]+=maxSpeed;}
		
		//Bewegung normieren (durch Maximalgeschwindigkeit begrenzen)
		norm();
		
		//Grenzen am BildschirmRand:
		//x-Grenze
		float xTranslate = v[0]*deltaTime;
		if(xTranslate+sprite.getX()+sprite.getWidth() > screen.right()){
			xTranslate = screen.right() - (sprite.getX()+sprite.getWidth());
		}
		if(xTranslate+sprite.getX() < screen.x){
			xTranslate = -sprite.getX();
		}
		//y-Grenze
		float yTranslate = v[1]*deltaTime;
		if(yTranslate+sprite.getY()+sprite.getHeight() > screen.top()){
			yTranslate = screen.top() - (sprite.getY()+sprite.getHeight());
		}
		if(yTranslate+sprite.getY() < 0){
			yTranslate = -sprite.getY();
		}
		
		//Diver bewegen
		sprite.translate(xTranslate, yTranslate);
		shape.setPosition(sprite.getX(), sprite.getY());
		
		v[0]*=decay;
		v[1]*=decay;
		
	}

	public void moveonjoystick(Touchpad joystick){
		if(joystick.isTouched())		{v[1]+=maxSpeed;}
		v[0] = joystick.getKnobPercentX()*2.0f;

		norm();
		
	}

	public void draw(Batch batch){
		sprite.draw(batch);
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

	public void resize() {
		sprite.setSize(100, 50);
		
	}

}