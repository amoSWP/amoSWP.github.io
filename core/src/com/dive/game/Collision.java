package com.dive.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;

public class Collision {
	
	public static ArrayList<GameObject> checkCollision(Diver diver, ArrayList<GameObject> objects ){
		
		ArrayList<GameObject> collisions = new ArrayList<GameObject>();
		
		for (GameObject o: objects){
			// falls eine Kollision vorliegt wird die Schleife abgebrochen und die Art zurückgegeben
			if(Intersector.overlaps(o.getShape(), diver.getShape()[0]) || Intersector.overlaps(o.getShape(), diver.getShape()[1])){
				collisions.add(o);
			}
		}
		return collisions;
	}
	
	public static boolean checkCollision(GameObject object1, GameObject object2){
		return Intersector.overlaps(object1.getShape(), object2.getShape());
	}
}
