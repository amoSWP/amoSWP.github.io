package com.dive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ObjectGenerator {
	private Trash[] listTrash;
	private Shark[] listSharks;
	private Plant[] listPlants;
	private Boat[] listBoats;

	private float countDownTrash;
	private float countDownShark;
	private float countDownPlant;
	private float countDownBoat;

	private float maxCountDown;
	private int pointerShark;
	private int pointerPlant;
	private int pointerTrash;
	private int pointerBoat;

	// maximale Anzahl Haie, Pflanzen, Müll
	private int maxNoTrash;
	private int maxNoShark;
	private int maxNoPlant;
	private int maxNoBoat;

	// zufällige Schwimmhöhe von Haien mit festem min/max Wert, Pflanzen haben
	// feste y
	// Koordinate
	private int minHeightWater = 80;
	private int maxHeightWater = 880;

	// initialisieren von Variablen die Hai/Pflanze neue Größe zuordnet
	private int newSizeShark;
	private int newSizePlant;

	private Random rand;

	// constructor: kreiere Liste mit Haien
	public ObjectGenerator(int maxNoShark, int maxNoPlant, int maxNoTrash, int maxNoBoat,
			float gameSpeed) {
		pointerTrash = 0;
		pointerShark = 0;
		pointerPlant = 0;
		pointerBoat = 0;

		countDownTrash = countDownShark = countDownPlant = maxCountDown = countDownBoat = 1.5f;
		rand = new Random();

		this.maxNoTrash = maxNoTrash;
		this.maxNoShark = maxNoShark;
		this.maxNoPlant = maxNoPlant;
		this.maxNoBoat = maxNoBoat;

		newSizeShark = 70 + rand.nextInt(70);
		newSizePlant = 50 + rand.nextInt(50);

		listTrash = new Trash[maxNoTrash];
		listSharks = new Shark[maxNoShark];
		listPlants = new Plant[maxNoPlant];
		listBoats = new Boat[maxNoBoat];

		// kreiert Liste mit Haien
		for (int i = 0; i < maxNoShark; i++) {
			listSharks[i] = new Shark(1920, minHeightWater
					+ rand.nextInt(maxHeightWater - minHeightWater));
		}

		// kreiert Liste mit Pflanzen
		for (int i = 0; i < maxNoPlant; i++) {
			listPlants[i] = new Plant(1920);
		}
		
		for (int i = 0; i < maxNoBoat; i++) {
			listBoats[i] = new Boat(1920);
		}


		// kreiert Liste mit Müll
		for (int i = 0; i < maxNoTrash; i++) {
			listTrash[i] = new Trash(1920, minHeightWater
					+ rand.nextInt(maxHeightWater - minHeightWater));
		}
		
		

		//

	}

	// gehe Liste der Haie durch und erstelle neue Liste von Haien welche genau
	// so gezeichnet werden soll
	public void nextShark(ArrayList<GameObject> list, float deltaTime) {
		countDownShark -= deltaTime;

		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDownShark < 0 && !listSharks[pointerShark].active) {
			list.add(listSharks[pointerShark]);

			listSharks[pointerShark].active = true;
			pointerShark = (pointerShark + 1) % maxNoShark;
			countDownShark = maxCountDown + 2 * rand.nextFloat();
		}

		// wenn Objekt Bildschirmrand erreicht wird es aus Liste gestrichen, auf
		// Ausgangsposition gesetzt und Status auf nicht aktiv, steht nun wieder
		// zur Verfügung

		for (int i = 0; i < maxNoShark; i++) {
			Shark e = listSharks[i];
			if (e.getActive()
					&& (e.getSprite().getX() < -e.getSprite().getWidth())) {

				e.setActive(false);
				list.remove(e);
				e.reset();
				e.getSprite().setX(1920);
				e.getSprite()
						.setY(minHeightWater
								+ rand.nextInt(maxHeightWater - minHeightWater));
				
			}
		}
	}

	public void nextPlant(ArrayList<GameObject> list, float deltaTime) {
		countDownPlant -= deltaTime;

		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDownPlant < 0 && !listPlants[pointerPlant].active) {
			list.add(listPlants[pointerPlant]);

			listPlants[pointerPlant].active = true;
			pointerPlant = (pointerPlant + 1) % maxNoPlant;
			countDownPlant = maxCountDown + 2 * rand.nextFloat();
		}

		for (int i = 0; i < maxNoPlant; i++) {
			Plant p = listPlants[i];
			if (p.getActive()
					&& (p.getSprite().getX() < -p.getSprite().getWidth())) {

				p.setActive(false);
				list.remove(p);
				p.reset();
				p.getSprite().setX(1920);
			}
		}

	}
	
	public void nextBoat(ArrayList<GameObject> list, float deltaTime) {
		countDownBoat -= deltaTime;

		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden

			list.add(listBoats[pointerBoat]);

			listBoats[pointerBoat].active = true;
			pointerBoat = (pointerBoat + 1) % maxNoBoat;
			countDownBoat = 10 + maxCountDown + 5 * rand.nextFloat();
		

		for (int i = 0; i < maxNoBoat; i++) {
			Boat b = listBoats[i];
			if (b.getActive()
					&& (b.getSprite().getX() < -b.getSprite().getWidth())) {

				b.setActive(false);
				list.remove(b);
				b.reset();
				b.getSprite().setX(1920);
			}
		}

	}

	// gehe Liste der Haie durch und erstelle neue Liste von Haien welche genau
	// so gezeichnet werden soll
	public void nextTrash(ArrayList<GameObject> list, float deltaTime) {
		countDownTrash -= deltaTime;

		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDownTrash < 0 && !listTrash[pointerTrash].active) {

			Trash t = listTrash[pointerTrash];

			for (int k = 0; k < 10; k++) {
				if (!overlap(t.getSprite().getHeight(), t.getSprite().getY())) {
					list.add(listTrash[pointerTrash]);
					listTrash[pointerTrash].active = true;

					pointerTrash = (pointerTrash + 1) % maxNoTrash;
					countDownTrash = maxCountDown + 2 * rand.nextFloat();

					break;
				} else {
					t.getSprite().setY(
							minHeightWater + rand.nextInt(maxHeightWater - minHeightWater));
				}
			}

		}

		// wenn Objekt Bildschirmrand erreicht wird es aus Liste gestrichen, auf
		// Ausgangsposition gesetzt und Status auf nicht aktiv, steht nun wieder
		// zur Verfügung

		for (int i = 0; i < maxNoTrash; i++) {
			Trash e = listTrash[i];
			if (e.getActive()
					&& (e.getSprite().getX() < -e.getSprite().getWidth())) {

				e.setActive(false);
				list.remove(e);
				e.setRandomTexture();
				System.out.println(e.sprite.getHeight());
				e.getSprite().setX(1920);
				e.getSprite()
						.setY(minHeightWater
								+ rand.nextInt(maxHeightWater - minHeightWater));

			}
		}
	}

	// Zufällige Erzeugung von integer Werten zwischen min max

	public boolean overlap(float height, float y) {

		for (GameObject o : listSharks) {
			if (1920 < o.getSprite().getX()
					+ o.getSprite().getWidth()
					&& y < o.getSprite().getY() + o.getSprite().getHeight()
					&& y + height >= o.getSprite().getY()) {
				return (true);
			}
		}
		return (false);
	}

	public void reset() {
		for(Shark s:listSharks){
			s.getSprite().setX(-1000);
			s.setActive(true);
			
		}
		for(Plant p:listPlants){
			p.getSprite().setX(-1000);
			p.setActive(true);
			
		}
		for(Trash t:listTrash){
			t.getSprite().setX(-1000);
			t.setActive(true);
			
		}
		
	}

}
