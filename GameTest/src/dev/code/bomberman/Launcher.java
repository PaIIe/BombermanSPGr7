package dev.code.bomberman;

import dev.code.bomberman.display.Display;

public class Launcher {
	public static void main(String[] args){
		Game game = new Game("Title!", 1000, 1000);
		game.start();
		
	}

}
