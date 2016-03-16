package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	private ObjectGenerator objectGen;		//der Objekt-Generator, welcher die SPielwelt erzeugt (wird im Konstruktor übergeben)
	private GameScreen screen;				//Darstellungsbereich
	private Diver diver;					//der Diver (wird im Konstruktor erstellt)
	private Parallax parallax;				//Der Hintergrund mit Parallax Effekt
	private GameState state;				//setzt den SPielzustand (zB um zu pausieren)
	
	private Touchpad joystick;
	private Drawable knob;
	private Drawable background;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	
	
	
	public World(ObjectGenerator objectGen, GameScreen screen, float iniSpeed, GameState state, boolean Android){
		
		objects = new ArrayList<GameObject>();
		speed = iniSpeed;
		
		this.objectGen = objectGen;
		this.screen = screen;
		this.state = state;
		
		diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 300, screen);
		parallax = new Parallax(speed, screen);
		
		skin = new Skin();										//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		skin.add("background",Assets.getInstance().joystickunder);
    	skin.add("knob",Assets.getInstance().joystickup);
		background = skin.getDrawable("background");
    	knob = skin.getDrawable("knob");
    	joystickstyle = new TouchpadStyle(background,knob);		//Joystickstyle wird erstellt bekommt seine Drawables
    	
		knob.setMinWidth(screen.width/10);						//Größe des Joysticks
		knob.setMinHeight(screen.width/10);
		
		joystick = new Touchpad(screen.width/10,joystickstyle);	//Joystick wird erstellt mit Bewegungsradius des Knüppels = 1/10 des Bildschirms
		joystick.setBounds(4*screen.width/5,  0, screen.width/5, screen.width/5);		//Größe und Platzierung des Joystickpads
		
	}
	
	
	public void draw(Batch batch,boolean android){			//Alle Spielobjekte zeichnen
		parallax.draw(batch);
		for(GameObject o: objects){o.draw(batch);}
		diver.draw(batch);
		if (android){
			joystick.draw(batch, 20);
		}
		
	}
	
	public void move(float deltaTime){
		for(GameObject o: objects){o.moveObject(screen.width,deltaTime, speed);}
		diver.move(deltaTime);
		parallax.move(deltaTime);
		diver.moveonjoystick(joystick);							//wird implementiert
	}
	
	public void resize(){
		diver.resize();
		//joystick.resize()									//muss implementiert werden!!
		//brauchen resize für sharks
	}
	
	public void update(float deltaTime){
		objectGen.nextPlant(objects, deltaTime);
		objectGen.nextShark(objects, deltaTime);
		if(Collision.checkCollision(diver, objects)==ObjectType.SHARK){state=GameState.PAUSE;}
		
	}

}
