package com.dive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class EndscreenProcessor implements InputProcessor {
	
	private World world;
	private EndScreen endscreen;
	private GameState state;
	
	public EndscreenProcessor(World world, EndScreen screen, GameState state){
		this.world = world;
		this.endscreen = screen;
		this.state = state;
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
		float[] p = Coords.getCameraCoords(screenX, (Gdx.graphics.getHeight()-screenY));
		if(endscreen.getRestart().getBoundingRectangle().contains(p[0],p[1]) && state.isEndscreen()){
        	world.reset();
        	state.resume();
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
		float[] p = Coords.getCameraCoords(screenX, (Gdx.graphics.getHeight()-screenY));
		if(endscreen.getRestart().getBoundingRectangle().contains(p[0],p[1]) && state.isEndscreen()){
        	endscreen.setSprite(Assets.getInstance().restartButton_hover);
        	return true;
        }
		else{
			endscreen.setSprite(Assets.getInstance().restartButton);
		}
		return false;
		
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
