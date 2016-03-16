package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	private ObjectGenerator objectGen;		//der Objekt-Generator, welcher die SPielwelt erzeugt (wird im Konstruktor übergeben)
	private GameScreen screen;				//Darstellungsbereich
	private Diver diver;					//der Diver (wird im Konstruktor erstellt)
	private Parallax parallax;				//Der Hintergrund mit Parallax Effekt
	private GameState state;				//setzt den SPielzustand (zB um zu pausieren)
	private float score;						//der Score des aktuellen Spiels
	private BitmapFont font;				//Schriftart zum schreiben

	
	
	
	public World(ObjectGenerator objectGen, GameScreen screen, float iniSpeed, GameState state, BitmapFont font){
		
		objects = new ArrayList<GameObject>();
		speed = iniSpeed;
		score = 0;
		
		this.objectGen = objectGen;
		this.screen = screen;
		this.state = state;
		this.font = font;
		
		diver = new Diver(Assets.getInstance().diver, 100, 50, 100, 300, screen);
		parallax = new Parallax(speed, screen);
		
	}
	
	
	
	public void draw(Batch batch){			//Ale Spielobjekte zeichnen
		parallax.draw(batch);
		for(GameObject o: objects){o.draw(batch);}
		diver.draw(batch);	
		font.draw(batch, Float.toString(score),5, 30);
	}
	
	public void move(float deltaTime){
		for(GameObject o: objects){o.moveObject(screen.width,deltaTime, speed);}
		diver.move(deltaTime);
		parallax.move(deltaTime);
	}
	
	public void resize(){
		diver.resize();
	}
	
	public void update(float deltaTime){
		//Diver auf Standardgeschwindigkeit (nachdem er verlangsamt wurde durch kollision)
		diver.refresh();
		
		//Level aufbauen
		objectGen.nextPlant(objects, deltaTime);
		objectGen.nextShark(objects, deltaTime);
		
		//Kollisionsabfragen
		ObjectType coll = Collision.checkCollision(diver, objects);
		if(coll == ObjectType.SHARK){state.pause();}
		else if(coll == ObjectType.PLANT){diver.slow();}
		
		//Luft updaten
		if(diver.getShape().getY()+diver.getShape().getHeight()>=640){diver.recover();}
		diver.breathe(deltaTime);
		if(!diver.hasAir()){state.pause();}
		
		//Score verwalten und Spielgeschwindigkeit anpassen
		score += 10*speed*deltaTime;
		System.out.println("score: " + score + ", speed:" + speed);
		speed = (float) (0.001*score+0.1);
		speed = (float) Math.min(speed, 1);
		parallax.setSpeed(speed);
		
	}

}
