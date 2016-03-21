package com.dive.game;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Menu implements InputProcessor {
	
	private Sprite start;
	private int score;
	private GameState gameState;
	private World world;
	private BitmapFont font;
	
	
	private Table table;
	
	private Drawable checkboxOff;
	private Drawable checkboxOn;
	
	private Skin Checkboxskin;
	private CheckBoxStyle checkboxstyle;
	private CheckBox checkbox;
	private BitmapFont touch;
	private Color color;
	
	private Stage stage;
	
	
	public Menu(GameState state,World world, BitmapFont font, Stage stage){
		
		gameState = state;
		this.world = world;
		this.font = font;
		score = 0;
		
		start = new Sprite(Assets.getInstance().startButton);
		start.setBounds(500, 200, 920, 178);
		
	}

	
	public void draw(Batch batch){
		start.draw(batch);
		font.draw(batch, Integer.toString(score),1000, 500);
		
	}

	public void setScore(int score) {
		this.score = score;
		System.out.println("set " + score);
		System.out.println("this" + this.score);
	}
	
	public Sprite getRestart(){
		return start;
	}
	
	public void setSprite(Texture tex){
		start = new Sprite(tex);
		start.setBounds(500, 200, 920, 178);
	}

	
	
	
	
	
	
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if(gameState.getState() != State.MENU){return false;}
		
		screenY = Gdx.graphics.getHeight()-screenY;
		float[] p = Coords.getCameraCoords(screenX, screenY);
		
		if(button == Buttons.LEFT && start.getBoundingRectangle().contains(p[0],p[1])){
			gameState.resume();
			world.reset();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
//		screenY = Gdx.graphics.getHeight()-screenY;
//		float[] p = Coords.getCameraCoords(screenX, screenY);
//		
//		if(gameState.getState() == State.ENDSCREEN){
//			if(restart.getBoundingRectangle().contains(p[0],p[1])){
//				restart.setTexture(Assets.getInstance().restartButton_hover);
//			}
//			else{
//				restart.setTexture(Assets.getInstance().restartButton);
//			}
//			return true;
//		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
