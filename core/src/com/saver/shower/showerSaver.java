package com.saver.shower;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class showerSaver extends Game {
	
	public static final String VERSION = "4.19";
	public static final String LOG = "Hackathon_app";
	public AssetManager loadManager = new AssetManager();
	
	@Override
	public void create() {
		this.setScreen(new splashScreen(this));
	}
			
}
