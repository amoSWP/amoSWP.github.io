package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;

public class World {
	
	private ArrayList<GameObject> objects;	//Liste aller im Spiel aktiven Objekte
	private float speed;					//Spielgeschwindigkeit in (% der Darstellungsbreite pro Sek.)
	
	private GameScreen screen;				//Darstellungsbereich
	
	
	public void draw(Batch batch){			//alle Spielobjekte zeichnen
		for(GameObject o: objects){o.draw(batch);}
	}
	
	public void move(){
		
	}

}
