package com.dive.game;

public class GameState {
	
	private State state;
	
	public enum State {
		START, MENU, GAME, ENDSCREEN, HIGHSCORES, PAUSE
	}
	
	public GameState(){
		state = State.START;
	}
	
	public GameState(int i){
		switch(i){
			case 0: state = State.START; break;
			case 1: state = State.GAME; break; 
			default : state = State.START; break;
		}
	}
	
	public State getState(){
		return state;
	}
	
	public void pause(){
		state = State.PAUSE;
	}
	
	public void resume(){
		state = State.GAME;
	}
	
	public void toggle(){
		if(state == State.GAME){state=State.PAUSE;}
		else if(state == State.PAUSE){state=State.GAME;}
	}
	
	public boolean isRunning(){
		return (state == State.GAME);
	}
	
}

