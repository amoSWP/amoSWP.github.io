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

	private float countDownTrash;
	private float countDownShark;
	private float countDownPlant;
	private float gameSpeed;

	private float maxCountDown;
	private int pointerShark;
	private int pointerPlant;
	private int pointerTrash;

	// maximale Anzahl Haie, Pflanzen, Müll
	private int maxNoTrash;
	private int maxNoShark;
	private int maxNoPlant;

	// zufällige Schwimmhöhe von Haien mit festem min/max Wert, Pflanzen haben
	// feste y
	// Koordinate
	private int minHeightWater = 80;
	private int maxHeightWater = 500;

	// initialisieren von Variablen die Hai/Pflanze neue Größe zuordnet
	private int newSizeShark;
	private int newSizePlant;
	private int newSizeTrash;

	// constructor: kreiere Liste mit Haien
	public ObjectGenerator(int maxNoShark, int maxNoPlant, int maxNoTrash,
			float gameSpeed) {
		pointerTrash = 0;
		pointerShark = 0;
		pointerPlant = 0;

		countDownTrash = countDownShark = countDownPlant = maxCountDown = 2;

		this.maxNoTrash = maxNoTrash;
		this.maxNoShark = maxNoShark;
		this.maxNoPlant = maxNoPlant;

		newSizeShark = randomInteger(70, 150);
		newSizePlant = randomInteger(50, 110);
		newSizeTrash = randomInteger(30, 60);

		listTrash = new Trash[maxNoTrash];
		listSharks = new Shark[maxNoShark];
		listPlants = new Plant[maxNoPlant];

		// kreiert Liste mit Haien
		for (int i = 0; i < maxNoShark; i++) {
			listSharks[i] = new Shark(Gdx.graphics.getWidth(), randomInteger(
					minHeightWater, maxHeightWater), newSizeShark + 100,
					newSizeShark + 20, -gameSpeed, Assets.getInstance().shark);
		}

		// kreiert Liste mit Pflanzen
		for (int i = 0; i < maxNoPlant; i++) {
			listPlants[i] = new Plant(Gdx.graphics.getWidth(),
					newSizePlant + 100, newSizePlant,
					Assets.getInstance().plant);
		}

		// kreiert Liste mit Müll
		for (int i = 0; i < maxNoTrash; i++) {
			listTrash[i] = new Trash(Gdx.graphics.getWidth(), randomInteger(
					minHeightWater, maxHeightWater), newSizeTrash,
					newSizeTrash, -gameSpeed, randomGarbage());
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
			countDownShark = maxCountDown + randomInteger(0, 2);
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
				e.getSprite().setX(Gdx.graphics.getWidth());
				e.getSprite().setY(
						randomInteger(minHeightWater, maxHeightWater));
				newSizeShark = randomInteger(70, 150);
				e.getSprite().setSize(newSizeShark + 100, newSizeShark + 20);
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
			countDownPlant = maxCountDown + randomInteger(0, 2);
		}

		for (int i = 0; i < maxNoPlant; i++) {
			Plant p = listPlants[i];
			if (p.getActive()
					&& (p.getSprite().getX() < -p.getSprite().getWidth())) {

				p.setActive(false);
				list.remove(p);
				p.getSprite().setX(Gdx.graphics.getWidth());
				newSizePlant = randomInteger(50, 120);
				p.getSprite().setSize(newSizePlant + 100, newSizePlant);
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
					listTrash[pointerTrash].sprite.setTexture(randomGarbage());
					pointerTrash = (pointerTrash + 1) % maxNoTrash;
					countDownTrash = maxCountDown + randomInteger(0, 2);
					
					break;
				} else {
					t.getSprite().setY(
							randomInteger(minHeightWater, maxHeightWater));
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
				e.getSprite().setX(Gdx.graphics.getWidth());
				e.getSprite().setY(
						randomInteger(minHeightWater, maxHeightWater));

				newSizeTrash = randomInteger(30, 60);
				e.getSprite().setSize(newSizeTrash, newSizeTrash);
			}
		}
	}

	// Zufällige Erzeugung von integer Werten zwischen min max
	public int randomInteger(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public boolean overlap(float height, float y) {

		for (GameObject o : listSharks) {
			if (Gdx.graphics.getWidth()< o.getSprite().getX()+ o.getSprite().getWidth()
					&& y < o.getSprite().getY() + o.getSprite().getHeight() 
					&& y + height >= o.getSprite().getY()) {
				return (true);
			}
		}
		return (false);
	}
	
	public Texture randomGarbage(){
		int i = randomInteger(1,2);
		if(i ==1){
			return (Assets.getInstance().trash1);
		}
		else {return (Assets.getInstance().trash2);
	}
}

}
