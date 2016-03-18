package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Joystick{
	private Touchpad joystick;
	private Drawable knob;
	private Drawable background;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	
	public Joystick(){
		this.skin = new Skin();										//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		this.skin.add("background",Assets.getInstance().joystickunder);
    	this.skin.add("knob",Assets.getInstance().joystickup);
		this.background = skin.getDrawable("background");
    	this.knob = skin.getDrawable("knob");
    	this.joystickstyle = new TouchpadStyle(background,knob);		//Joystickstyle wird erstellt bekommt seine Drawables
    	
		this.knob.setMinWidth(Gdx.graphics.getWidth()/8);						//Größe des Joysticks
		this.knob.setMinHeight(Gdx.graphics.getWidth()/8);
		
		this.joystick = new Touchpad(5,joystickstyle);	//Joystick wird erstellt mit Bewegungsradius des Knüppels = 1/10 des Bildschirms
		this.joystick.setBounds(5*Gdx.graphics.getWidth()/6,  0 , Gdx.graphics.getWidth()/6, Gdx.graphics.getWidth()/6);//Größe und Platzierung des Joystickpads
	}
	public Touchpad getJoystick(){
		return joystick;
	}
}
	
