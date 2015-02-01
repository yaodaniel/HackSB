package com.saver.shower;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.saver.shower.splashScreen;

public class showerSaver extends Game {
	
	public static float timer = 360f;
	public static int minute = (int) (showerSaver.timer/60);
	public static int seconds = (int) (showerSaver.timer%60);
	public static final String VERSION = "4.19";
	public static final String LOG = "Hackathon_app";
	public static AssetManager loadManager = new AssetManager();
	public static BitmapFont white;
	public int songNum;
	//public static Skin buttonSkin;
	
	@Override
	public void create() {
		loadManager.load("gamefont_0.png", Texture.class);
		loadManager.load("gamefont_1.png", Texture.class);
		loadManager.load("gamefont.fnt", BitmapFont.class);
		//Set Permissions
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		loadManager.load("button.pack", TextureAtlas.class);
		loadManager.load("button.png", Texture.class);
		loadManager.load("buttons.pack", TextureAtlas.class);
		loadManager.load("buttons.png", Texture.class);
		loadManager.load("swag.pack", TextureAtlas.class);
		loadManager.load("swag.png", Texture.class);
		loadManager.load("SS2.bmp", Texture.class);
		loadManager.load("SS3.bmp", Texture.class);
		loadManager.load("SS4.bmp", Texture.class);
		loadManager.load("SS5.bmp", Texture.class);
		loadManager.load("happyTree.png", Texture.class);
		loadManager.load("sadTree.png", Texture.class);
		loadManager.load("verySadTree.png", Texture.class);
		setScreen(new splashScreen(this));
	}	
	
	public static void updateTime(float Seconds){
		if(Seconds == -30)
			return;
		timer = Seconds;
		minute = (int)(Seconds/60);
		seconds = (int)(Seconds%60);			
	}
}
