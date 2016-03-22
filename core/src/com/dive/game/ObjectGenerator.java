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
	private Jellyfish[] listJellyfish;
	private GasBottle[] listGasBottles;

	private float countDownTrash;
	private float countDownShark;
	private float countDownPlant;
	private float countDownBoat;
	private float countDownJellyfish;
	private float countDownGasBottle;

	private float maxCountDown;
	private int pointerShark;
	private int pointerPlant;
	private int pointerTrash;
	private int pointerBoat;
	private int pointerJellyfish;
	private int pointerGasBottle;

	// maximale Anzahl Haie, Pflanzen, Müll
	private int maxNoTrash;
	private int maxNoShark;
	private int maxNoPlant;
	private int maxNoBoat;
	private int maxNoJellyfish;
	private int maxNoGasBottle;

	// zufällige Schwimmhöhe von Haien mit festem min/max Wert, Pflanzen haben
	// feste y
	// Koordinate
	private int minHeightWater = 100;
	private int maxHeightWater = 860;

	private Random rand;

	// constructor: kreiere Liste mit Haien
	public ObjectGenerator(int maxNoShark, int maxNoPlant, int maxNoTrash,
			int maxNoBoat, int maxNoJellyfish, int maxNoGasBottle,
			float gameSpeed) {
		pointerTrash = 0;
		pointerShark = 0;
		pointerPlant = 0;
		pointerBoat = 0;
		pointerJellyfish = 0;
		pointerGasBottle = 0;

		countDownShark = countDownPlant = maxCountDown = countDownJellyfish = 1f;
		countDownTrash = 1.5f;
		countDownBoat = countDownGasBottle = 10;

		rand = new Random();

		this.maxNoTrash = maxNoTrash;
		this.maxNoShark = maxNoShark;
		this.maxNoPlant = maxNoPlant;
		this.maxNoBoat = maxNoBoat;
		this.maxNoJellyfish = maxNoJellyfish;
		this.maxNoGasBottle = maxNoGasBottle;

		listTrash = new Trash[maxNoTrash];
		listSharks = new Shark[maxNoShark];
		listPlants = new Plant[maxNoPlant];
		listBoats = new Boat[maxNoBoat];
		listJellyfish = new Jellyfish[maxNoJellyfish];
		listGasBottles = new GasBottle[maxNoGasBottle];

		// kreiert Liste mit Haien
		for (int i = 0; i < maxNoShark; i++) {
			listSharks[i] = new Shark(1920, minHeightWater
					+ rand.nextInt(maxHeightWater - minHeightWater));
		}

		// kreiert Liste mit Quallen
		for (int i = 0; i < maxNoJellyfish; i++) {
			listJellyfish[i] = new Jellyfish(1920, minHeightWater
					+ rand.nextInt(maxHeightWater - minHeightWater));
		}

		// kreiert Liste mit Pflanzen
		for (int i = 0; i < maxNoPlant; i++) {
			listPlants[i] = new Plant(1920);
		}

		// kreiert Liste mit Booten
		for (int i = 0; i < maxNoBoat; i++) {
			listBoats[i] = new Boat(1920);
		}

		// kreiert Liste mit Müll
		for (int i = 0; i < maxNoTrash; i++) {
			listTrash[i] = new Trash(1920, minHeightWater
					+ rand.nextInt(maxHeightWater - minHeightWater));
		}
		// kreiert Liste mit Gasflaschen
		for (int i = 0; i < maxNoGasBottle; i++) {
			listGasBottles[i] = new GasBottle(1920);
		}

		//

	}

	// gehe Liste der Haie durch und erstelle neue Liste von Haien welche genau
	// so gezeichnet werden soll
	public void nextShark(ArrayList<GameObject> list, float deltaTime, float distance) {
		countDownShark -= deltaTime;

		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDownShark < 0 && !listSharks[pointerShark].active) {
			list.add(listSharks[pointerShark]);

			listSharks[pointerShark].active = true;
			pointerShark = (pointerShark + 1) % maxNoShark;
			if(distance<100){
			countDownShark = maxCountDown + 2 * rand.nextFloat()-(float) 0.01*distance;}
			else{
				countDownShark = 2*rand.nextFloat();
			}
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

	public void nextJellyfish(ArrayList<GameObject> list, float deltaTime) {
		countDownJellyfish -= deltaTime;

		// überprüft ob Zeit abgelaufen und Objekt nicht aktiv, schreibt in
		// Liste um dann gezeichnet zu werden
		if (countDownJellyfish < 0 && !listJellyfish[pointerJellyfish].active) {
			list.add(listJellyfish[pointerJellyfish]);

			listJellyfish[pointerJellyfish].active = true;
			pointerJellyfish = (pointerJellyfish + 1) % maxNoJellyfish;
			countDownJellyfish = maxCountDown + 2 * rand.nextFloat();
		}

		// wenn Objekt Bildschirmrand erreicht wird es aus Liste gestrichen, auf
		// Ausgangsposition gesetzt und Status auf nicht aktiv, steht nun wieder
		// zur Verfügung

		for (int i = 0; i < maxNoJellyfish; i++) {
			Jellyfish e = listJellyfish[i];
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

		if (countDownBoat < 0 && !listBoats[pointerBoat].active) {

			
			list.add(listBoats[pointerBoat]);
			listBoats[pointerBoat].active = true;
			pointerBoat = (pointerBoat + 1) % maxNoBoat;
			countDownBoat = 10 + maxCountDown + 5 * rand.nextFloat();

		}
		

		for (int i = 0; i < maxNoBoat; i++) {
			Boat b = listBoats[i];
			if (b.getActive()
					&& (b.getSprite().getX() < -b.getSprite().getWidth())) {

				b.setActive(false);
				list.remove(b);
				b.reset();
				b.getSprite().setX(1920);
				b.getSprite().setY(920);
				System.out.println(b.sprite.getY());
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
				if (!overlapShark(t.getSprite().getHeight(), t.getSprite().getY())) {
					list.add(listTrash[pointerTrash]);
					listTrash[pointerTrash].active = true;

					pointerTrash = (pointerTrash + 1) % maxNoTrash;
					countDownTrash = 10 + maxCountDown + 2 * rand.nextFloat();

					break;
				} else {
					t.getSprite().setY(
							minHeightWater
									+ rand.nextInt(maxHeightWater
											- minHeightWater));
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
				e.getSprite().setX(1920);
				e.getSprite()
						.setY(minHeightWater
								+ rand.nextInt(maxHeightWater - minHeightWater));

			}
		}
	}

	public void nextGasBottle(ArrayList<GameObject> list, float deltaTime) {
		countDownGasBottle -= deltaTime;

		if (countDownGasBottle < 0 && !listGasBottles[pointerGasBottle].active) {

			list.add(listGasBottles[pointerGasBottle]);
			listGasBottles[pointerGasBottle].active = true;
			pointerGasBottle = (pointerGasBottle + 1) % maxNoGasBottle;
			countDownGasBottle = maxCountDown + 5 * rand.nextFloat();

		}

		for (int i = 0; i < maxNoGasBottle; i++) {
			GasBottle g = listGasBottles[i];
			if (g.getActive()
					&& (g.getSprite().getX() < -g.getSprite().getWidth())) {

				g.setActive(false);
				list.remove(g);
				g.getSprite().setX(1920);
			}
		}

	}

	// Zufällige Erzeugung von integer Werten zwischen min max

	public boolean overlapShark(float height, float y) {

		for (GameObject o : listSharks) {
			if (1920 < o.getSprite().getX() + o.getSprite().getWidth()
					&& y < o.getSprite().getY() + o.getSprite().getHeight()
					&& y + height >= o.getSprite().getY()) {
				return (true);
			}
		}
		return (false);
	}


	public void reset() {
		for (Shark s : listSharks) {
			s.getSprite().setX(-1000);
			s.getShape().setX(-1000);
			s.setActive(true);

		}
		for (Plant p : listPlants) {
			p.getSprite().setX(-1000);
			p.getShape().setX(-1000);
			p.setActive(true);

		}
		for (Trash t : listTrash) {
			t.getSprite().setX(-1000);
			t.getShape().setX(-1000);
			t.setActive(true);

		}
		for (Boat b : listBoats) {
			b.getSprite().setX(-1000);
			b.getShape().setX(-1000);
			b.setActive(true);

		}
		for (Jellyfish j : listJellyfish) {
			j.getSprite().setX(-1000);
			j.getShape().setX(-1000);
			j.setActive(true);

		}

		for (GasBottle g : listGasBottles) {
			g.getSprite().setX(-1000);
			g.getShape().setX(-1000);
			g.setActive(true);
		}

	}

}
