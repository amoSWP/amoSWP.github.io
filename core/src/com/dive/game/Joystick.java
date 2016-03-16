package com.dive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;



public class Joystick {
	
	private Drawable knob;
	private Drawable background;
	private Touchpad joystick;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	private Stage stage;
	
	
	public Joystick(){
		
		System.out.println("Constructing Joystick..0");
    	skin = new Skin();
    	skin.add("background",Assets.getInstance().joystickunder);
    	skin.add("knob",Assets.getInstance().joystickup);
		
    	System.out.println("Constructing Joystick.. 1");
    	joystickstyle = new TouchpadStyle();
    	background = skin.getDrawable("background");
    	knob = skin.getDrawable("knob");
   
		knob.setMinWidth(20);
		knob.setMinHeight(20);
		
		System.out.println("Constructing Joystick..2");
		joystick = new Touchpad(5,joystickstyle);
		joystick.setBounds(15, 15, 100/3,100/3);
		
		System.out.println("Constructing Joystick..3");
		//stage = new Stage();
		//stage.addActor(joystick);
	}

}
