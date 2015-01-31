package com.saver.shower;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.saver.shower.splashScreen;

public class showerSaver extends Game {
	
	public static float timer = 420f;
	public static final String VERSION = "4.19";
	public static final String LOG = "Hackathon_app";
	public static AssetManager loadManager = new AssetManager();
	public static BitmapFont white;
	//public static Skin buttonSkin;
	
	@Override
	public void create() {
		loadManager.load("gamefont_0.png",Texture.class);
		loadManager.load("gamefont_1.png",Texture.class);
		loadManager.load("gamefont.fnt",BitmapFont.class);
		setScreen(new splashScreen(this));
	}	
}
