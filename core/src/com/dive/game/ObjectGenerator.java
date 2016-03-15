package com.dive.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class ObjectGenerator {
	private Shark[] listSharks;
	private Plant[] listPlants;

	private float countDownShark;
	private float countDownPlant;

	private float maxCountDown;
	private int pointerShark;
	private int pointerPlant;

	// maximale Anzahl Haie, Pflanzen
	private int maxNoShark;
	private int maxNoPlant;

	// Höhe von Haien, Pflanzen haben feste y Koordinate
	private int minHeightShark = 120;
	private int maxHeightShark = 500;

	private int newSizeShark;
	private int newSizePlant;

	// constructor: kreiere Liste mit Haien
	public ObjectGenerator(int maxNoShark, int maxNoPlant) {
		pointerShark = 0;
		pointerPlant = 0;
		countDownShark = countDownPlant = maxCountDown = 1;

		this.maxNoShark = maxNoShark;
		this.maxNoPlant = maxNoPlant;

		newSizeShark = randomInteger(70, 150);
		newSizePlant = randomInteger(50, 110);

		listSharks = new Shark[maxNoShark];
		listPlants = new Plant[maxNoPlant];

		// kreiert Liste mit Haien
		for (int i = 0; i < maxNoShark; i++) {
			listSharks[i] = new Shark(Gdx.graphics.getWidth(), randomInteger(
					minHeightShark, maxHeightShark), newSizeShark,
					newSizeShark + 20, -3.0f, Assets.getInstance().shark);
		}

		// kreiert Liste mit Pflanzen
		for (int i = 0; i < maxNoPlant; i++) {
			listPlants[i] = new Plant(Gdx.graphics.getWidth(), newSizePlant,
					newSizePlant, -3.0f, Assets.getInstance().wal);
		}
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
						randomInteger(minHeightShark, maxHeightShark));
				newSizeShark = randomInteger(70, 150);
				e.getSprite().setSize(newSizeShark, newSizeShark + 20);
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
				p.getSprite().setSize(newSizePlant, newSizePlant);
			}
		}
		
		int z = 2;

	}

	// Zufällige Erzeugung von integer Werten zwischen min max
	public int randomInteger(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
