package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Game2D;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static final int screenWidth = 1536;
	public static final int screenHeight = 960;
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(screenWidth, screenHeight);
		config.setForegroundFPS(60);
		config.setTitle("MyGame2D");
		new Lwjgl3Application(new Game2D(), config);
	}
}
