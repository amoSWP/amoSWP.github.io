package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	private ObjectGenerator objectGen;		//der Objekt-Generator, welcher die Spielwelt erzeugt (wird im Konstruktor übergeben)
	private Diver diver;					//der Diver (wird im Konstruktor erstellt)
	private Parallax parallax;				//Der Hintergrund mit Parallax Effekt
	private GameState state;				//setzt den SPielzustand (zB um zu pausieren)
	private float score;						//der Score des aktuellen Spiels
	private BitmapFont font;				//Schriftart zum schreiben

	
	private Touchpad joystick;
	private Drawable knob;
	private Drawable background;
	private TouchpadStyle joystickstyle;
	private Skin skin;
	
	
	public World(ObjectGenerator objectGen, float iniSpeed, GameState state, BitmapFont font){
		
		objects = new ArrayList<GameObject>();
		speed = iniSpeed;
		score = 0;
		
		this.objectGen = objectGen;
		this.state = state;
		this.font = font;
		
		diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 300);
		parallax = new Parallax(speed);
		
		skin = new Skin();										//Ein Skin wird erzeugt um aus Texture Dateien Drawables zu machen
		skin.add("background",Assets.getInstance().joystickunder);
    		skin.add("knob",Assets.getInstance().joystickup);
		background = skin.getDrawable("background");
    		knob = skin.getDrawable("knob");
    		joystickstyle = new TouchpadStyle(background,knob);		//Joystickstyle wird erstellt bekommt seine Drawables
    	
		knob.setMinWidth(192);						//Größe des Joysticks
		knob.setMinHeight(192);
		
		joystick = new Touchpad(192,joystickstyle);	//Joystick wird erstellt mit Bewegungsradius des Knüppels = 1/10 des Bildschirms
		joystick.setBounds(1536,  0, 384, 384);		//Größe und Platzierung des Joystickpads
		
	}
	
	
	public void draw(Batch batch,boolean android){			//Alle Spielobjekte zeichnen
		parallax.draw(batch);
		for(GameObject o: objects){o.draw(batch);}
		diver.draw(batch);
		font.draw(batch, Float.toString(score),5, 30);
		if (android){
			joystick.draw(batch, 20);
		}
		
	}
	
	public void move(float deltaTime){
		for(GameObject o: objects){
			o.moveObject(deltaTime, speed);
			}
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
		//Diver auf Standardgeschwindigkeit (nachdem er verlangsamt wurde durch kollision)
		diver.refresh();
		
		//Level aufbauen
		objectGen.nextPlant(objects, deltaTime);
		objectGen.nextShark(objects, deltaTime);
		objectGen.nextTrash(objects, deltaTime);
		
		
		//Kollisionsabfragen
		ObjectType coll = Collision.checkCollision(diver, objects);
		if(coll == ObjectType.SHARK){state.gameOver();}
		else if(coll == ObjectType.PLANT){diver.slow();}
		
		//Luft updaten
		if(diver.getShape().getY()+diver.getShape().getHeight()>=950){diver.recover();}
		diver.breathe(deltaTime);
		if(!diver.hasAir()){state.gameOver();}
		
		//Score verwalten und Spielgeschwindigkeit anpassen
		score += 10*speed*deltaTime;
//		System.out.println("score: " + score + ", speed:" + speed);
		speed = (float) (0.001*score+0.1);
		speed = (float) Math.min(speed, 1);
		parallax.setSpeed(speed);
		
	}


	public float getScore() {
		return score;
	}
	
	public void reset(){
		score = 0;
		speed = 0.1f;
		objects.clear();
		diver.reset();
		
		objectGen.reset();
		parallax.setSpeed(speed);

	}

}
